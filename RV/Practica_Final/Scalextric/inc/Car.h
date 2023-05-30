#pragma once

#include <GL/glew.h>
#include "Object.h"
#include "Piece.h"

class Car : public Object {
private:
	Piece* pieces[69];

public:
	Car();
	~Car();
	virtual int GetNumPieces();
	virtual Piece* GetPiece(int i);
};

