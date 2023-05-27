#pragma once

#include <GL/glew.h>
#include <glm/glm.hpp>
#include "ShaderProgram.h"
#include "Figura.h"

class Escena {
public:
    Escena();
    ~Escena();
    void Draw(ShaderProgram* program, glm::mat4 proj, glm::mat4 view);

private:
    Figura* fig0;
    Figura* fig1;
};