#pragma once

#include <GL/glew.h>
#include "Figura.h"
#include "Constantes.h"

//
// CLASE: CGDisk
//
// DESCRIPCI�N: Representa un disco de radio interior 'r0' (puede ser cero), 
//              radio exterior 'r1', dividido en 'p' capas y 'm' sectores.
//
class Curva : public Figura {
public:
	Curva(double angle);
};