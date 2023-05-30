#include <GL/glew.h>
#include <cmath>
#include "Circuito.h"

/*
numPistas -> numero total de (rectas+curvas) en el circuito

Vector<DataPiezaCircuito>
    DataPiezaCircuito{
        Posicion
        Rotacion
        DataPieza
    }
    DataPieza{
        Longitud
        Angulo
    }



*/

Circuito::Circuito()
{
    /*       
             ____
            /    \
    Recta   |    |
      1     |    |
            \____/
    */


    /*
       0,30
        |
        |
        |
       0,0
    */

    const float posZ = 0.5f;

    float izq = -2.5;

    posInicial = glm::vec3(izq, 0.0f, posZ);
    FiguraData r1p1 = RectaData {
        posInicial, 
        8, 
        std::make_pair<GLfloat,glm::vec3>(0.0, glm::vec3(0,0,1))
    };

    FiguraData r1p2 = RectaData {
        glm::vec3(izq, 8.00f, posZ), 
        8,
        std::make_pair<GLfloat,glm::vec3>(0.0, glm::vec3(0,0,1))
    };


    instrucciones.push_back(r1p1);
    instrucciones.push_back(r1p2);


    FiguraData c1 = CurvaData {
        glm::vec3(izq, 16.00f, posZ),
        180.0f,
        true,
        std::make_pair<GLfloat,glm::vec3>(0.0f, glm::vec3(0,0,1))
    };


    instrucciones.push_back(c1);


    FiguraData r2p1 = RectaData{
        glm::vec3(izq + CURVA_RADIO_CENTRO * 2, 16.00f, posZ),
        8,
        std::make_pair<GLfloat,glm::vec3>(180.f, glm::vec3(0,0,1))
    };

    FiguraData r2p2 = RectaData{
        glm::vec3(izq + CURVA_RADIO_CENTRO * 2, 8.00f, posZ),
        8,
        std::make_pair<GLfloat,glm::vec3>(180.f, glm::vec3(0,0,1))
    };

    instrucciones.push_back(r2p1);
    instrucciones.push_back(r2p2);


    FiguraData c2 = CurvaData {
        glm::vec3(izq + CURVA_RADIO_CENTRO * 2, 0.00f, posZ),
        180.0f,
        true,
        std::make_pair<GLfloat,glm::vec3>(180.0, glm::vec3(0,0,1))
    };


    instrucciones.push_back(c2);

}

Circuito::Circuito(int config) {
    std::vector<DataPieza> piezas;

    switch (config) {
        case 0: {
            piezas.push_back(DataPieza {16, 0});
            piezas.push_back(DataPieza {0, -180});
            piezas.push_back(DataPieza {16, 0});
            piezas.push_back(DataPieza {0, -180});
            break;
        }
        case 1: {
            piezas.push_back(DataPieza {16, 0});
            piezas.push_back(DataPieza {0, -90});
            piezas.push_back(DataPieza {0, 90});
            piezas.push_back(DataPieza {0, -180});
            piezas.push_back(DataPieza {16 + 9*2, 0});
            piezas.push_back(DataPieza {0, -90});
            piezas.push_back(DataPieza {18, 0});
            piezas.push_back(DataPieza {0, -90});
            break;
        }
        case 2 :{ 
            piezas.push_back(DataPieza {30, 0});
            piezas.push_back(DataPieza {0, -90});
            piezas.push_back(DataPieza {0, 80});
            piezas.push_back(DataPieza {0, -20});
            piezas.push_back(DataPieza {7, 0});
            piezas.push_back(DataPieza {0, -30});
            piezas.push_back(DataPieza {7, 0});
            piezas.push_back(DataPieza {0, -30});
            piezas.push_back(DataPieza {7, 0});
            piezas.push_back(DataPieza {0, -30});
            piezas.push_back(DataPieza {7, 0});
            piezas.push_back(DataPieza {0, -30});
            piezas.push_back(DataPieza {7, 0});
            piezas.push_back(DataPieza {0, -30});
            piezas.push_back(DataPieza {10, 0});
            piezas.push_back(DataPieza {0, -180});
            piezas.push_back(DataPieza {5, 0});
            piezas.push_back(DataPieza {0, 160});
            piezas.push_back(DataPieza {5, 0});
            piezas.push_back(DataPieza {0, 20});
            piezas.push_back(DataPieza {2, 0});
            piezas.push_back(DataPieza {0, 90});
            piezas.push_back(DataPieza {0, -20});
            piezas.push_back(DataPieza {6, 0});
            piezas.push_back(DataPieza {0, -90});
            piezas.push_back(DataPieza {16, 0});
            //
            piezas.push_back(DataPieza {0, -70});
            piezas.push_back(DataPieza {24.3, 0});
            piezas.push_back(DataPieza {0, -90});
            piezas.push_back(DataPieza {2, 0});
            break;
        }
    }
    

    
    CrearConPistas(piezas);
}


int Sector(float rotacion) {
    if (rotacion < 90) return 1;
    if (rotacion < 180) return 2;
    if (rotacion < 270) return 3;
    if (rotacion < 360) return 4; 
}

glm::vec3 Circuito::CalculaCentro(glm::vec3 posActual, float rotacion, float longitud){

    float rotacion_radianes = glm::radians(rotacion);

    float new_x = posActual.x + longitud*sin(rotacion_radianes);
    float new_y = posActual.y - longitud*cos(rotacion_radianes);
    
    return glm::vec3(new_x, new_y, posActual.z);
}

glm::vec3 Circuito::ActualizaPosicion(glm::vec3 posActual, float rotacion, float longitud) {
    /*
        Xx + R*sin(alfa) = Ax     =>    Xx = Ax - R*sin(alfa)
        Xy + R*cos(alfa) = Ay     =>    Xy = Ay - R*cos(alfa)
    */

    float rotacion_radianes = glm::radians(rotacion);

    float new_x = posActual.x - longitud*sin(rotacion_radianes);
    float new_y = posActual.y + longitud*cos(rotacion_radianes);

    return glm::vec3(new_x, new_y, posActual.z);
}

GLfloat CorrigeAngulo(GLfloat angulo) {
    while ( angulo > 360 || angulo < 0 ) {
        if(angulo > 360) angulo-=360;
        else if(angulo < 0) angulo+=360;
    }

    return angulo;
}

void Circuito::CrearConPistas(std::vector<DataPieza> piezas) {
    constexpr GLfloat CIRCUITO_Z = 0.5;
    //glm::vec3 posActual = glm::vec3(0, 0.0f, CIRCUITO_Z);
    glm::vec3 posActual = glm::vec3(-30, -30.0f, CIRCUITO_Z);
    GLfloat rotacionActual = 0.f;

    for(int i = 0; i < piezas.size(); ++i) {
        if(!piezas[i].EsCurva()) {
            instrucciones.push_back(
                RectaData {
                    posActual,
                    piezas[i].Longitud,
                    std::make_pair<GLfloat,glm::vec3>((GLfloat)rotacionActual, glm::vec3(0,0,1))
            });
            posActual = ActualizaPosicion(posActual, rotacionActual, piezas[i].Longitud);
        } else {          
            instrucciones.push_back(
                CurvaData {
                    posActual,
                    std::abs(piezas[i].Angulo),
                    piezas[i].Angulo < 0,
                    std::make_pair<GLfloat,glm::vec3>((GLfloat)rotacionActual, glm::vec3(0,0,1))
            });
            GLfloat nuevaRotacion = rotacionActual + piezas[i].Angulo;
            
            nuevaRotacion = CorrigeAngulo(nuevaRotacion);

            GLfloat anguloCircunferencia1 = rotacionActual + (piezas[i].Angulo < 0 ? 90 : -90);
            
            anguloCircunferencia1 = CorrigeAngulo(anguloCircunferencia1);

            GLfloat anguloCircunferencia2 = anguloCircunferencia1 + (piezas[i].Angulo < 0 
                                                                    ? -std::abs(piezas[i].Angulo)
                                                                    : std::abs(piezas[i].Angulo));
            
            anguloCircunferencia2 = CorrigeAngulo(anguloCircunferencia2);

            glm::vec3 centroCircunferencia = CalculaCentro(posActual, anguloCircunferencia1, CURVA_RADIO_CENTRO);
            posActual = ActualizaPosicion(centroCircunferencia, anguloCircunferencia2, CURVA_RADIO_CENTRO);
            rotacionActual = nuevaRotacion;
        }
    }

}