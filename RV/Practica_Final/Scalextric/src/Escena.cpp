#include "Escena.h"
#include <GL/glew.h>
#include <glm/glm.hpp>
#include <glm/gtc/matrix_transform.hpp>
#include "ShaderProgram.h"
#include "Figura.h"
#include "Recta.h"
#include "Curva.h"

Escena::Escena() {}


Escena::Escena(Circuito c) {
    CreateTextures();
    figuras = new Figura*[c.instrucciones.size()];
    numFiguras = c.instrucciones.size();
  
    for(int i = 0; i < numFiguras; i++) {
        FiguraData& variantActual = c.instrucciones[i];
        switch (variantActual.index()) {
            case 0: {   // RectaData
                RectaData datosFiguraActual = std::get<RectaData>(variantActual);
                figuras[i] = new Recta(datosFiguraActual.Size);
                figuras[i]->SetMaterial(&(materiales[0]));
                figuras[i]->Translate(datosFiguraActual.PosIni);
                figuras[i]->Rotate(datosFiguraActual.Rot.first, datosFiguraActual.Rot.second);
                break;
            }
            
            case 1:  {  // CurvaData
                CurvaData datosFiguraActual = std::get<CurvaData>(variantActual);
                figuras[i] = new Curva(datosFiguraActual.Angulo);
                figuras[i]->SetMaterial(&(materiales[0]));

                figuras[i]->Translate(datosFiguraActual.PosIni);

                figuras[i]->Rotate(datosFiguraActual.Rot.first, datosFiguraActual.Rot.second);

                
                if (datosFiguraActual.isClockwise) {
                    figuras[i]->Translate(glm::vec3(DESPLAZAMIENTO_CURVA,0,0));
                    figuras[i]->Rotate((180.f - datosFiguraActual.Angulo), glm::vec3(0,0,1));
                }
                else
                    figuras[i]->Translate(glm::vec3(-DESPLAZAMIENTO_CURVA,0,0));


                break;
            }
        }
    }

    suelo = new Recta(100.f,100.f);
    suelo->Translate(glm::vec3(0.f, -50.f, 0.f));
    suelo->SetMaterial(&(materiales[1]));

    nano = new Car();
    nano->Translate(glm::vec3(-0.8f, 4.0f, 0.7f));
    nano->Rotate(180, glm::vec3(0.0f, 0.0f, 1.0f));

}

Escena::~Escena()
{
    for(size_t i = 0; i<numFiguras; i++)
        delete figuras[i];

    delete [] figuras;
    delete nano;
    delete suelo;


}

//
// FUNCIÓN: CGScene::Draw()
//
// PROPÓSITO: Dibuja la escena
//
void Escena::Draw(ShaderProgram* program, glm::mat4 proj, glm::mat4 view)
{
    // ground->Draw(program, proj, view);
    light->SetUniforms(program);

    for (size_t i = 0; i < numFiguras; ++i) {
        figuras[i]->Draw(program, proj, view);
    }
    suelo->Draw(program, proj, view);
    nano->Draw(program, proj, view);
}

void Escena::CreateTextures()
{
    glm::vec3 Ldir = glm::vec3(0.7f, 0.3f, -1.0f);
    Ldir = glm::normalize(Ldir);
    light = new Light();
    light->SetLightDirection(Ldir);
    light->SetAmbientLight(glm::vec3(0.2f, 0.2f, 0.2f));
    light->SetDifusseLight(glm::vec3(0.8f, 0.8f, 0.8f));
    light->SetSpecularLight(glm::vec3(1.0f, 1.0f, 1.0f));

    Material m0;
    m0.SetAmbientReflect(0.5f, 0.5f, 0.5f);
    m0.SetDifusseReflect(0.5f, 0.5f, 0.5f);
    m0.SetSpecularReflect(0.8f, 0.8f, 0.8f);
    m0.SetShininess(16.0f);
    m0.InitTexture("textures/TexturaCarretera2.png");

    Material m1;
    m1.SetAmbientReflect(0.5f, 0.5f, 0.5f);
    m1.SetDifusseReflect(0.5f, 0.5f, 0.5f);
    m1.SetSpecularReflect(0.8f, 0.8f, 0.8f);
    m1.SetShininess(16.0f);
    m1.InitTexture("textures/SueloTextura2.jpg");

    materiales.push_back(m0);
    materiales.push_back(m1);


}
