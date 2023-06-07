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
    void Update();
    void ActualizaCoche(Car* piloto);
    
    Car* nano;
    Car* sainz;
    Figura** figuras;

private:
    Figura* suelo;
    Cubemah* etsi;
    Circuito circuito;

    Fog* fog;
    Light* light;

    std::vector<Material> materiales;
    size_t numFiguras;
};