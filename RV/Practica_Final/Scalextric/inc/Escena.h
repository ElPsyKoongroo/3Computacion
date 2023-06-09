#pragma once

#include <GL/glew.h>
#include <glm/glm.hpp>
#include <glm/gtc/matrix_transform.hpp>
#include "ShaderProgram.h"
#include "Figura.h"
#include "Circuito.h"
#include "Material.h"
#include "Light.h"
#include "Car.h"
#include "Constantes.h"
#include "Cubemah.h"
#include "Fog.h"
#include "Camara.h"
#include "Recta.h"
#include "Curva.h"
#include <cmath>

enum class CamaraEnum {
    Nano,
    Sainz,
    Circuito
};

class Escena {
public:
    Escena();
    Escena(Circuito c);
    ~Escena();
    void Draw(ShaderProgram* program, glm::mat4 proj, glm::mat4 view);
    void CreateTextures();
    void Update();
    void ActualizaCoche(Car* piloto);
    void UpdateCamara(CamaraEnum camaraActiva, Camara* camara);
    
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