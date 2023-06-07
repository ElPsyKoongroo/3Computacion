#pragma once

#include <GL/glew.h>
#include "Object.h"
#include "Piece.h"

/*
Para mover el coche:
	Tener el circuito completo (todas las piezas)
		- Recta -> Ang = 0, Long = X
		- Curva -> Ang = x, Long = daiwa

	Una vez se crea cada curva
		- Asignarle la longitud correspondiente (formulita segun el angulo)

	Guardas toeso en el circuito

	int xp = 0// pieza actual
	double x = 0 //pos coche relativa a la pieza en la que está
	double xv = 1; //vel coche

	Por cada frame:
		- Pilla el tiempo que ha pasado
		- Actualiza la posicion del coche (pos += vel * tiempo)
			- Sigue en la misma pieza ?
				- No -> pasar a la siguiente
				- Si -> 
					LongitudPieza = X
					DistanciaRec = Y

					Si es curva-> 
						curva mide x y angulo A
						recorrido y y angulo B
*/

class Car : public Object {
private:
	Piece* pieces[69];

public:
	Car();
	double speed;
	int piezaAct;
	double distanciaRecorridaEnPieza;

	glm::vec3 position;
	GLfloat rotation;
	bool isRight;

	~Car();
	virtual int GetNumPieces();
	virtual Piece* GetPiece(int i);
};

