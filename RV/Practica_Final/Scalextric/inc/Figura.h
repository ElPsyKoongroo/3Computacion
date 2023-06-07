#pragma once

#include <GL/glew.h>
#include <glm/glm.hpp>
#include <glm/gtc/matrix_transform.hpp>
#include "ShaderProgram.h"
#include "Material.h"

#define VERTEX_DATA 0
#define INDEX_DATA 1
#define NORMAL_DATA 2
#define TEXTURE_DATA 3
//
// CLASE: CGFigure
//
// DESCRIPCI�N: Clase abstracta que representa un objeto descrito mediante
//              VAO para su renderizado mediante shaders
//
class Figura {
protected:
	GLushort* indexes;  // Array of indexes 
	GLfloat* vertices;  // Array of vertices
	GLfloat* normals; // Array of normals
	GLfloat* textures; // Array of texture coordinates
	Material* material;


	int numFaces;     // Number of faces
	int numVertices;  // Number of vertices
	GLuint VBO[4];
	GLuint VAO;

	glm::mat4 location; // Model matrix

public:

	~Figura();
	void InitBuffers();
	void SetMaterial(Material* mat);
	void ResetLocation();
	void Translate(glm::vec3 t);
	void SetLocation(glm::vec3 t);
	void Rotate(GLfloat angle, glm::vec3 axis);
	void Draw(ShaderProgram* program, glm::mat4 projection, glm::mat4 view);


};