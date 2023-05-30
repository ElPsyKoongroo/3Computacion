#pragma once

#include <GL/glew.h>
#include <vector>
#include <utility>
#include "Figura.h"
#include <variant>
#include "Constantes.h"

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

struct DataPieza {
	float Longitud;
	float Angulo;
	bool EsCurva() { return Angulo != 0; }
};

struct DataPiezaCircuito {
	glm::vec3 Posicion;
	float Rotacion;
	DataPieza dataPieza;

	bool EsCurva() { return dataPieza.EsCurva(); }
};

using FiguraData = std::variant<RectaData, CurvaData>;


class Circuito {

public:
	static const int DEFAULT = 0;
	static const int BARCELONA = 1;
	std::vector<FiguraData> instrucciones;
	std::vector<DataPiezaCircuito> pistas;
	glm::vec3 posInicial;

	Circuito();
	Circuito(int config);
	glm::vec3 CalculaCentro(glm::vec3 posActual, float rotacion, float longitud);
	glm::vec3 ActualizaPosicion(glm::vec3 posActual, float rotacion, float longitud);
	void CrearConPistas(std::vector<DataPieza>);
};