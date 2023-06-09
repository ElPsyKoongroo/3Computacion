#pragma once

#include <GL/glew.h>
#include "ShaderProgram.h"
#include "Figura.h"
#include "Constantes.h"

//
// CLASE: Recta
//
// DESCRIPCIÓN: Clase que representa una recta descrito mediante
//              VAO para su renderizado mediante shaders
// 

class Recta : public Figura {
public:
	Recta(GLfloat heigth, GLfloat w = -1.0f);
	~Recta();
};