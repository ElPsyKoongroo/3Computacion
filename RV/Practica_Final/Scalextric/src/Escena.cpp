#include "Escena.h"
#include <GL/glew.h>
#include <glm/glm.hpp>
#include <glm/gtc/matrix_transform.hpp>
#include "ShaderProgram.h"
#include "Figura.h"
#include "Recta.h"
#include "Curva.h"

/*
    
    Tipo:
        Curva
        Recta

    Posicion:
        Inicio
        Fin
        Angulo

    [(Tipo, Posicion)]


*/


Escena::Escena()
{

    //fig0 = new Recta(10.0f, 20.0f);
    //fig0->Translate(glm::vec3(-25.0f, 5.0f, 25.0f));
    //fig0->Rotate(-0.0f, glm::vec3(0.0f, 0.0f, 1.0f));


    //fig1 = new Curva(5, 50, 30.5f, 50.0f, 70.0);
    //fig1->Translate(glm::vec3(25.0f, 5.0f, 25.0f));
    //fig1->Rotate(-90.0f, glm::vec3(1.0f, 0.0f, 0.0f));

}


Escena::Escena(Circuito c) {
    anchura = 1.0;
    figuras = new Figura*[c.instrucciones.size()];
    numFiguras = c.instrucciones.size();
  
    for(int i = 0; i < numFiguras; i++) {
        FiguraData& variantActual = c.instrucciones[i];

        switch (variantActual.index()) {
            case 0: {   // RectaData
                RectaData datosFiguraActual = std::get<RectaData>(variantActual);
                figuras[i] = new Recta(datosFiguraActual.Size);
                figuras[i]->Translate(datosFiguraActual.PosIni);
                figuras[i]->Rotate(datosFiguraActual.Rot.first, datosFiguraActual.Rot.second);
                break;
            }
            
            case 1:  {  // CurvaData
                CurvaData datosFiguraActual = std::get<CurvaData>(variantActual);
                figuras[i] = new Curva(datosFiguraActual.Angulo);

                figuras[i]->Translate(datosFiguraActual.PosIni);

                figuras[i]->Rotate(datosFiguraActual.Rot.first, datosFiguraActual.Rot.second);
                if (datosFiguraActual.isClockwise) {
                    figuras[i]->Translate(glm::vec3(3.5,0,0));
                    figuras[i]->Rotate((180.f - datosFiguraActual.Angulo), glm::vec3(0,0,1));
                }
                else
                    figuras[i]->Translate(glm::vec3(-3.5,0,0));
                
     
                break;
            }
        }
    }
}

Escena::~Escena()
{
    for(size_t i = 0; i<numFiguras; i++)
        delete figuras[i];

    delete [] figuras;

}

//
// FUNCIÓN: CGScene::Draw()
//
// PROPÓSITO: Dibuja la escena
//
void Escena::Draw(ShaderProgram* program, glm::mat4 proj, glm::mat4 view)
{
    // ground->Draw(program, proj, view);
    for (size_t i = 0; i < numFiguras; ++i) {
        figuras[i]->Draw(program, proj, view);
    }
}