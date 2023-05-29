#pragma once

#include <GL/glew.h>
#include <glm/glm.hpp>
#include "ShaderProgram.h"
#include "Figura.h"
#include "Circuito.h"


class Escena {
public:
    Escena();
    Escena(Circuito c);
    ~Escena();
    void Draw(ShaderProgram* program, glm::mat4 proj, glm::mat4 view);

private:
    Figura** figuras;
    GLfloat anchura;
    size_t numFiguras;
};