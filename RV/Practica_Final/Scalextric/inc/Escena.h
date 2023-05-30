#pragma once

#include <GL/glew.h>
#include <glm/glm.hpp>
#include "ShaderProgram.h"
#include "Figura.h"
#include "Circuito.h"
#include "Material.h"
#include "Light.h"
#include "Car.h"
#include "Constantes.h"


class Escena {
public:
    Escena();
    Escena(Circuito c);
    ~Escena();
    void Draw(ShaderProgram* program, glm::mat4 proj, glm::mat4 view);
    void CreateTextures();

private:
    Figura** figuras;
    Figura* suelo;
    Car* nano;
    std::vector<Material> materiales;
    Light* light;
    size_t numFiguras;
};