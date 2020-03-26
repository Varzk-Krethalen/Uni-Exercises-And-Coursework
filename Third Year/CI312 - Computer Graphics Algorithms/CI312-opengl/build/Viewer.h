#pragma once

// Include standard headers
#include <stdio.h>
#include <stdlib.h>

// Include GLEW
#include <GL/glew.h>

// Include GLFW
#include <glfw3.h>

// Include GLM
#include <glm/glm.hpp>
using namespace glm;

#include <iostream>
#include <vector>
#include "common/shader.hpp"
#include "common/objloader.hpp"
#include "common/vboindexer.hpp"
#include "common/controls.hpp"
#include <glm/gtx/transform.hpp>

class Viewer
{
private:
	void RunScene(const GLfloat  ag_vertex_buffer_data[], int vertexBufferSize, const GLfloat  ag_color_buffer_data[], int colourBufferSize, int width, int height);
	void CreateViewport(int viewportX, int viewportY, int viewportWidth, int viewportHeight, const GLuint& vertexbuffer, const GLuint& colorbuffer);
	int InitialiseWindow(int width, int height, bool& retflag);

public:
	int CreateScene(int width, int height, const GLfloat  g_vertex_buffer_data[], int vertexBufferSize, const GLfloat  g_color_buffer_data[], int colourBufferSize);
};

