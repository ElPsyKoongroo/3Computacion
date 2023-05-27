#pragma once

#include <GL/glew.h>
#include "ShaderProgram.h"
#include "Figura.h"

//
// CLASE: Recta
//
// DESCRIPCIÓN: Clase que representa una recta descrito mediante
//              VAO para su renderizado mediante shaders
// 

class Recta : public Figura {
private:
	GLuint VBO;
	GLuint VAO;

public:
	Recta(GLfloat width, GLfloat heigth);
	~Recta();
	void Draw(ShaderProgram* program, GLfloat posX, GLfloat posY, GLfloat size);
};