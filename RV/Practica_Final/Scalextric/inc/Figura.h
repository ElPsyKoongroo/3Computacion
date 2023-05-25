#pragma once

#include <GL/glew.h>
#include <glm/glm.hpp>
#include "ShaderProgram.h"

#define VERTEX_DATA     0
#define INDEX_DATA      1

//
// CLASE: CGFigure
//
// DESCRIPCIÓN: Clase abstracta que representa un objeto descrito mediante
//              VAO para su renderizado mediante shaders
//
class Figura {
protected:
	GLushort* indexes;  // Array of indexes 
	GLfloat* vertices;  // Array of vertices

	int numFaces;     // Number of faces
	int numVertices;  // Number of vertices
	GLuint VBO[2];
	GLuint VAO;

public:

	~Figura();
	void InitBuffers();
	void Draw(ShaderProgram* program, glm::mat4 transform);

};