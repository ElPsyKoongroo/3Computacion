#pragma once

#include <GL/glew.h>
#include <glm/glm.hpp>
#include "ShaderProgram.h"
#include "Figura.h"
#include "Circuito.h"
#include "Material.h"
#include "Light.h"


class Escena {
public:
    Escena();
    Escena(Circuito c);
    ~Escena();
    void Draw(ShaderProgram* program, glm::mat4 proj, glm::mat4 view);
    void CreateTextures();

private:
    Figura** figuras;
    std::vector<Material> materiales;
    Light* light;
    GLfloat anchura;
    size_t numFiguras;
};