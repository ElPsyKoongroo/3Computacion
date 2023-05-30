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
#include "Cubemah.h"
#include "Fog.h"


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
    Cubemah* etsi;

    Fog* fog;
    Light* light;

    std::vector<Material> materiales;
    size_t numFiguras;
};