#pragma once

#include <GL/glew.h>
#include <glm/glm.hpp>
#include "ShaderProgram.h"

// Que si Adri, que hace falta un cubemah, no una foto.

class Cubemah {
public:
	Cubemah();
	~Cubemah();
	void Draw(ShaderProgram* program, glm::mat4 projection, glm::mat4 view);

private:
	GLuint cubemap;
	GLuint VBO[4];
	GLuint VAO;

	void InitCube();
	void InitCubemap();
	void InitTexture(GLuint target, const char* filename);
};