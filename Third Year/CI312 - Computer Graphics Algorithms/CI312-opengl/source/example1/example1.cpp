// Include standard headers
#include <stdio.h>
#include <stdlib.h>

#include "build/Viewer.h"

int main(void)
{
    static const GLfloat g_vertex_buffer_data[] = {
        -1.0f, -1.0f, 1.0f,  //bottom front left
        1.0f, -1.0f, 1.0f,   //bottom front right
        0.0f,  1.0f, 0.0f,   //top middle middle
        
        0.0f,  -1.0f, -1.0f,  //bottom back middle
        -1.0f, -1.0f, 1.0f,  //bottom front left
        1.0f, -1.0f, 1.0f,   //bottom front right
        
        0.0f,  -1.0f, -1.0f,  //bottom back middle
        -1.0f, -1.0f, 1.0f,  //bottom front left
        0.0f,  1.0f, 0.0f,   //top middle middle
        
        0.0f,  -1.0f, -1.0f,  //bottom back middle
        1.0f, -1.0f, 1.0f,   //bottom front right
        0.0f,  1.0f, 0.0f,   //top middle middle
    };

    static const GLfloat g_color_buffer_data[] = {
        0.0,  1.0,  0.0f,
        0.0,  1.0,  0.0f,
        0.0,  1.0,  0.0f,
        1.0,  0.0,  0.0f,
        1.0,  0.0,  0.0f,
        1.0,  0.0,  0.0f,
        0.0,  0.0,  1.0f,
        0.0,  0.0,  1.0f,
        0.0,  0.0,  1.0f,
        0.0,  1.0,  1.0f,
        0.0,  1.0,  1.0f,
        0.0,  1.0,  1.0f,

    };

    int width = 1000;
    int height = 750;

    Viewer viewer = *(new Viewer());
    int a = viewer.CreateScene(width, height, g_vertex_buffer_data, sizeof(g_vertex_buffer_data), g_color_buffer_data, sizeof(g_color_buffer_data)); //talk about sizeof issues?
    return 0;//
}
