#include "Viewer.h"

GLFWwindow* window;

int Viewer::CreateScene(int width, int height, const GLfloat  g_vertex_buffer_data[9], int vertexBufferSize, const GLfloat  g_color_buffer_data[9], int colourBufferSize)
{
    bool retflag;
    int retval = InitialiseWindow(width, height, retflag);
    if (retflag) return retval;
    RunScene(g_vertex_buffer_data, vertexBufferSize, g_color_buffer_data, colourBufferSize, width, height);
    return 0;
}

int Viewer::InitialiseWindow(int width, int height, bool& retflag)
{
    retflag = true;

    if (!glfwInit())
    {
        fprintf(stderr, "Failed to initialize GLFW\n");
        getchar();
        return -1;
    }

    glfwWindowHint(GLFW_SAMPLES, 4);
    glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
    glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
    glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE); // To make MacOS happy; should not be needed
    glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);


    // Open a window and create its OpenGL context
    window = glfwCreateWindow(width, height, "Basic Example", NULL, NULL);
    if (window == NULL) {
        fprintf(stderr, "Failed to open GLFW window. If you have an Intel GPU, they are not 3.3 compatible. Try the 2.1 version of the tutorials.\n");
        getchar();
        glfwTerminate();
        return -1;
    }
    glfwMakeContextCurrent(window);

    // Ensure we can capture the escape key being pressed below
    glfwSetInputMode(window, GLFW_STICKY_KEYS, GL_TRUE);
    // Hide the mouse and enable unlimited mouvement
    glfwSetInputMode(window, GLFW_CURSOR, GLFW_CURSOR_DISABLED);

    // Set the mouse at the center of the screen
    glfwPollEvents();
    //we set the cursor at the centre so that it always start on our origin (0,0,0)
    glfwSetCursorPos(window, width / 2, height / 2);


    // Dark blue background
    glClearColor(0.0f, 0.0f, 0.4f, 0.0f);

    // Initialize GLEW
    glewExperimental = true; // Needed for core profile


                             // Initialize GLEW
    if (glewInit() != GLEW_OK) {
        fprintf(stderr, "Failed to initialize GLEW\n");
        getchar();
        glfwTerminate();
        return -1;
    }

    glEnable(GL_DEPTH_TEST);
    // Accept fragment if it closer to the camera than the former one
    glDepthFunc(GL_LESS);

    // Cull triangles which normal is not towards the camera
    //glEnable(GL_CULL_FACE);
    retflag = false;
    return {};
}

void Viewer::RunScene(const GLfloat g_vertex_buffer_data[], int vertexBufferSize, const GLfloat g_color_buffer_data[], int colourBufferSize, int width, int height)
{
    GLuint VertexArrayID;
    glGenVertexArrays(1, &VertexArrayID);
    glBindVertexArray(VertexArrayID);

    GLuint vertexbuffer;
    glGenBuffers(1, &vertexbuffer);
    glBindBuffer(GL_ARRAY_BUFFER, vertexbuffer);
    glBufferData(GL_ARRAY_BUFFER, vertexBufferSize, g_vertex_buffer_data, GL_STATIC_DRAW);

    GLuint colorbuffer;
    glGenBuffers(1, &colorbuffer);
    glBindBuffer(GL_ARRAY_BUFFER, colorbuffer);
    glBufferData(GL_ARRAY_BUFFER, colourBufferSize, g_color_buffer_data, GL_STATIC_DRAW);

    GLuint programID = LoadShaders("SimpleVertexShader.hlsl", "SimpleFragmentShader.hlsl");

    do {
        // Clear the screen. It's not mentioned before Tutorial 02, but it can cause flickering, so it's there nonetheless.
        glClear(GL_DEPTH_BUFFER_BIT | GL_COLOR_BUFFER_BIT);

        glUseProgram(programID);
        computeMatricesFromInputs(window, width, height);

        //add tuple array for as many triangles as needed?
        CreateViewport(10, 10, width - 20, height - 20, vertexbuffer, colorbuffer); //in folio talk about height difference from studentcentral example
        //CreateViewport(width/2, 0, width/2, height, vertexbuffer, colorbuffer);

        // Swap buffers
        glfwSwapBuffers(window);
        glfwPollEvents();

    } // Check if the ESC key was pressed or the window was closed
    while (glfwGetKey(window, GLFW_KEY_ESCAPE) != GLFW_PRESS &&
        glfwWindowShouldClose(window) == 0);

    // Cleanup VBO and shader
    glDeleteBuffers(1, &vertexbuffer);
    glDeleteProgram(programID);
    glDeleteVertexArrays(1, &VertexArrayID);


    // Close OpenGL window and terminate GLFW
    glfwTerminate();

}

void Viewer::CreateViewport(int viewportX, int viewportY, int viewportWidth, int viewportHeight, const GLuint& vertexbuffer, const GLuint& colorbuffer)
{

    glViewport(viewportX, viewportY, viewportWidth, viewportHeight);

    //********Add the Geometry
    // 1rst attribute buffer : vertices
    glEnableVertexAttribArray(0);
    glBindBuffer(GL_ARRAY_BUFFER, vertexbuffer);
    glVertexAttribPointer(
        0,                  // attribute 0. No particular reason for 0, but must match the layout in the shader.
        3,                  // size
        GL_FLOAT,           // type
        GL_FALSE,           // normalized?
        0,                  // stride
        (void*)0            // array buffer offset
    );

    // 2nd attribute buffer : colors
    glEnableVertexAttribArray(1);
    glBindBuffer(GL_ARRAY_BUFFER, colorbuffer);
    glVertexAttribPointer(
        1,                  // attribute 0. No particular reason for 0, but must match the layout in the shader.
        3,                  // size
        GL_FLOAT,           // type
        GL_FALSE,           // normalized?
        0,                  // stride
        (void*)0            // array buffer offset
    );



    // Draw the geometry !
    glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
    glDrawArrays(GL_TRIANGLES, 0, 12); // 3 indices starting at 0 -> 1

    glDisableVertexAttribArray(0);
    glDisableVertexAttribArray(1);
}


