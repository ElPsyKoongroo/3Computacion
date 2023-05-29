#pragma once

#include <GL/glew.h>
#include <vector>
#include <utility>
#include "Figura.h"
#include <variant>

using Rotacion = std::pair<GLfloat, glm::vec3>;


struct RectaData {
	glm::vec3 PosIni;
	GLfloat Size;
	Rotacion Rot;
};

struct CurvaData {
	glm::vec3 PosIni;
	double   Angulo;
	bool isClockwise;
	Rotacion  Rot;
};

using FiguraData = std::variant<RectaData, CurvaData>;


class Circuito {

public:
	std::vector<FiguraData> instrucciones;
	glm::vec3 posInicial;

	Circuito();
};