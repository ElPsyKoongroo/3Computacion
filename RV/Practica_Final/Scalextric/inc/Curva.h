#pragma once

#include <GL/glew.h>
#include "Figura.h"

//
// CLASE: CGDisk
//
// DESCRIPCIÓN: Representa un disco de radio interior 'r0' (puede ser cero), 
//              radio exterior 'r1', dividido en 'p' capas y 'm' sectores.
//
class Curva : public Figura {
public:
	Curva(double angle);
	Curva(GLint p, GLint m, GLfloat r0, GLfloat r1, GLfloat startAngle, GLfloat endAngle);
};