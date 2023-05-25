#include "Figura.h"
#include <GL/glew.h>
#include <glm/glm.hpp>
#include <glm/gtc/matrix_transform.hpp>

Figura::~Figura()
{
	if (vertices != NULL) delete[] vertices;
	if (indexes != NULL) delete[] indexes;

	// Delete vertex buffer objects
	glDeleteBuffers(2, VBO);

	// Delete vertex array object
	glDeleteVertexArrays(1, &VAO);
}

void Figura::InitBuffers()
{
	// Create the Vertex Array Object
	glGenVertexArrays(1, &VAO);
	glBindVertexArray(VAO);

	// Create the Vertex Buffer Objects
	glGenBuffers(2, VBO);

	// Copy data to video memory
	// Vertex data
	int buffersize = sizeof(GLfloat) * numVertices * 3;
	glBindBuffer(GL_ARRAY_BUFFER, VBO[VERTEX_DATA]);
	glBufferData(GL_ARRAY_BUFFER, buffersize, vertices, GL_STATIC_DRAW);

	// Indexes
	buffersize = sizeof(GLushort) * numFaces * 3;
	glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, VBO[INDEX_DATA]);
	glBufferData(GL_ELEMENT_ARRAY_BUFFER, buffersize, indexes, GL_STATIC_DRAW);

	delete[] vertices;
	delete[] indexes;

	vertices = NULL;
	indexes = NULL;

	glEnableVertexAttribArray(0); // Vertex position
	glBindBuffer(GL_ARRAY_BUFFER, VBO[VERTEX_DATA]);
	glVertexAttribPointer(0, 3, GL_FLOAT, GL_FALSE, 0, 0);
}

void Figura::Draw(ShaderProgram* program, glm::mat4 transform)
{
	glm::mat4 matrix = transform;
	program->SetUniformMatrix4("Transform", transform);

	glBindVertexArray(VAO);
	glDrawElements(GL_TRIANGLES, numFaces * 3, GL_UNSIGNED_SHORT, NULL);
}
