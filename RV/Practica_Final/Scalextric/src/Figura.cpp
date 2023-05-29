#include "Figura.h"

//
// FUNCIÓN: Figura::~Figura()
//
// PROPÓSITO: Destructor de la figura
//
Figura::~Figura()
{
	if (vertices != NULL) delete[] vertices;
	if (indexes != NULL) delete[] indexes;
	if (normals != NULL) delete[] normals;
	if (textures != NULL) delete[] textures;

	// Delete vertex buffer objects
	glDeleteBuffers(4, VBO);
	glDeleteVertexArrays(1, &VAO);
}

//
// FUNCIÓN: Figura::InitBuffers()
//
// PROPÓSITO: Crea el VAO y los VBO y almacena todos los datos
//            en la GPU.
//
void Figura::InitBuffers()
{
	// Create the Vertex Array Object
	glGenVertexArrays(1, &VAO);
	glBindVertexArray(VAO);

	// Create the Vertex Buffer Objects
	glGenBuffers(4, VBO);

	// Copy data to video memory
	// Vertex data
	int buffsize = sizeof(GLfloat) * numVertices * 3;
	glBindBuffer(GL_ARRAY_BUFFER, VBO[VERTEX_DATA]);
	glBufferData(GL_ARRAY_BUFFER, buffsize, vertices, GL_STATIC_DRAW);

	// Normal data
	glBindBuffer(GL_ARRAY_BUFFER, VBO[NORMAL_DATA]);
	glBufferData(GL_ARRAY_BUFFER, buffsize, normals, GL_STATIC_DRAW);

	// Texture coordinates
	buffsize = sizeof(GLfloat) * numVertices * 2;
	glBindBuffer(GL_ARRAY_BUFFER, VBO[TEXTURE_DATA]);
	glBufferData(GL_ARRAY_BUFFER, buffsize, textures, GL_STATIC_DRAW);

	// Indexes
	buffsize = sizeof(GLushort) * numFaces * 3;
	glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, VBO[INDEX_DATA]);
	glBufferData(GL_ELEMENT_ARRAY_BUFFER, buffsize, indexes, GL_STATIC_DRAW);

	delete[] vertices;
	delete[] indexes;
	delete[] normals;
	delete[] textures;

	vertices = NULL;
	indexes = NULL;
	normals = NULL;
	textures = NULL;

	glEnableVertexAttribArray(0); // Vertex position
	glBindBuffer(GL_ARRAY_BUFFER, VBO[VERTEX_DATA]);
	glVertexAttribPointer(0, 3, GL_FLOAT, GL_FALSE, 0, 0);

	glEnableVertexAttribArray(1); // Vertex normals
	glBindBuffer(GL_ARRAY_BUFFER, VBO[NORMAL_DATA]);
	glVertexAttribPointer(1, 3, GL_FLOAT, GL_FALSE, 0, 0);

	glEnableVertexAttribArray(2); // Vertex textures
	glBindBuffer(GL_ARRAY_BUFFER, VBO[TEXTURE_DATA]);
	glVertexAttribPointer(2, 2, GL_FLOAT, GL_FALSE, 0, 0);
}

//
// FUNCIÓN: Figura::SetMaterial(Material* m)
//
// PROPÓSITO: Asigna el material de la figura
//
void Figura::SetMaterial(Material* mat)
{
	material = mat;
}

//
// FUNCIÓN: Figura::ResetLocation()
//
// PROPÓSITO: Asigna la posición inicial de la figura 
//
void Figura::ResetLocation()
{
	location = glm::mat4(1.0f);
}

//
// FUNCIÓN: Figura::Translate(glm::vec3 t)
//
// PROPÓSITO: Añade un desplazamiento a la matriz de posición de la figura 
//
void Figura::Translate(glm::vec3 t)
{
	location = glm::translate(location, t);
}

//
// FUNCIÓN: Figura::Rotate(GLfloat angle, glm::vec3 axis)
//
// PROPÓSITO: Añade una rotación a la matriz de posición de la figura 
//
void Figura::Rotate(GLfloat angle, glm::vec3 axis)
{
	location = glm::rotate(location, glm::radians(angle), axis);
}

//
// FUNCIÓN: Figura::Draw(ShaderProgram * program, glm::mat4 projection, 
//                                                             glm::mat4 view)
//
// PROPÓSITO: Dibuja la figura
//
void Figura::Draw(ShaderProgram* program, glm::mat4 projection, glm::mat4 view)
{
	glm::mat4 mvp = projection * view * location;
	program->SetUniformMatrix4("MVP", mvp);
	program->SetUniformMatrix4("ViewMatrix", view);
	program->SetUniformMatrix4("ModelViewMatrix", view * location);
	material->SetUniforms(program);

	glBindVertexArray(VAO);
	glDrawElements(GL_TRIANGLES, numFaces * 3, GL_UNSIGNED_SHORT, NULL);
}