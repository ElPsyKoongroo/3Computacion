#pragma once

#include <GL/glew.h>
#include <vector>
#include <utility>
#include "Figura.h"

enum class TipoFigura {
	Curva = 0,
	Recta = 1
};

using Rotacion = std::pair<GLfloat, glm::vec3>;

struct Figurita {
	TipoFigura Tipo;
	glm::vec3 PosIni;
	glm::vec3 PosFin;
	Rotacion Rot;
};
// PosIni, PosFin, Rotacion

class Circuito {

public:
	std::vector<Figurita> instrucciones;
	glm::vec3 posInicial;

	Circuito();
};