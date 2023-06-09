#include "Escena.h"


#define MY_PI 3.14159265358979323846

Escena::Escena() {}


Escena::Escena(Circuito c) 
{
    circuito = c;

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
                    figuras[i]->Translate(glm::vec3(CURVA_RADIO_CENTRO,0,0));
                    figuras[i]->Rotate((180.f - datosFiguraActual.Angulo), glm::vec3(0,0,1));
                }
                else
                    figuras[i]->Translate(glm::vec3(-CURVA_RADIO_CENTRO,0,0));


                break;
            }
        }
    }

    suelo = new Recta(100.f,100.f);
    suelo->Translate(glm::vec3(0.f, -50.f, 0.48f));
    suelo->SetMaterial(&(materiales[1]));

    nano = new Car();
    nano->piezaAct=0;
    nano->distanciaRecorridaEnPieza = 0;
    nano->speed = 0.1;
    nano->isRight = true;

    sainz = new Car();
    sainz->piezaAct = 0;
    sainz->distanciaRecorridaEnPieza = 0;
    sainz->speed = 0.1;
    sainz->isRight = false;


    nano->position = std::get<RectaData>(circuito.instrucciones[0]).PosIni;
    nano->position[2] = 0.7;
    nano->rotation = 0;
    nano->Translate(nano->position);
    nano->Rotate(0, glm::vec3(0.0f, 0.0f, 1.0f));

    sainz->position = std::get<RectaData>(circuito.instrucciones[0]).PosIni;
    sainz->position[2] = 0.7;
    sainz->rotation = 0;
    sainz->Translate(sainz->position);
    sainz->Rotate(0, glm::vec3(0.0f, 0.0f, 1.0f));

}

Escena::~Escena()
{
    for(size_t i = 0; i<numFiguras; i++)
        delete figuras[i];

    delete [] figuras;

    delete suelo;
    delete nano;
    delete sainz;
    delete light;

    delete fog;
    delete etsi;
}

//
// FUNCIÓN: CGScene::Draw()
//
// PROPÓSITO: Dibuja la escena
//
void Escena::Draw(ShaderProgram* program, glm::mat4 proj, glm::mat4 view)
{
    light->SetUniforms(program);
 
    fog->SetUniforms(program);
    etsi->Draw(program, proj, view);

    for (size_t i = 0; i < numFiguras; ++i) {
        figuras[i]->Draw(program, proj, view);
    }
    suelo->Draw(program, proj, view);

    nano->Draw(program, proj, view);
    sainz->Draw(program, proj, view);

}

void Escena::CreateTextures()
{
    etsi = new Cubemah();

    fog = new Fog();
    fog->SetMaxDistance(500.0f);
    fog->SetMinDistance(50.0f);
    fog->SetFogColor(glm::vec3(0.8f, 0.8f, 0.8f));

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

void Escena::ActualizaCoche(Car* piloto) {
    /*
        PD  | CD  | Ventaja    
          0 | 0   |   1
          0 | 1   |   0  
          1 | 0   |   0  
          1 | 1   |   1
    */
    int indexActualPiece = piloto->piezaAct;

    piloto->distanciaRecorridaEnPieza += piloto->speed;

    bool correctPiece = false;
    do {
        int distanciaPiezaActual;

        if (!circuito.instrucciones[indexActualPiece].index()) {
            float size = std::get<RectaData>(circuito.instrucciones[indexActualPiece]).Size;
            if (size <= piloto->distanciaRecorridaEnPieza) {
                piloto->distanciaRecorridaEnPieza -= size;
                indexActualPiece = (indexActualPiece+1) % circuito.instrucciones.size();
            }
            else correctPiece = true;
        } else {
            auto curvaActual = std::get<CurvaData>(circuito.instrucciones[indexActualPiece]);
            float angulo = curvaActual.Angulo;
            double radio_offset = (piloto->isRight == curvaActual.isClockwise) 
                ? CURVA_OFFSET_VENTAJA
                : CURVA_OFFSET_DESVENTAJA;
            float circunferencia = 2 * MY_PI * (CURVA_RADIO_CENTRO + radio_offset);
            float distanciaArco = circunferencia / 360 * angulo;

            if (distanciaArco <= piloto->distanciaRecorridaEnPieza) {
                piloto->distanciaRecorridaEnPieza -= distanciaArco;
                indexActualPiece = (indexActualPiece+1) % circuito.instrucciones.size();
            }
            else correctPiece = true;
        }
    }while(!correctPiece);

    piloto->piezaAct = indexActualPiece;
    
    glm::vec3 newPosition;
    GLfloat newRotation = 0;
    
    switch (circuito.instrucciones[indexActualPiece].index()) {
       case 0: {   // RectaData
            RectaData rectaActual = std::get<RectaData>(circuito.instrucciones[indexActualPiece]);
            newPosition = Circuito::ActualizaPosicionRecta(rectaActual.PosIni, rectaActual.Rot.first, piloto->distanciaRecorridaEnPieza);
            newRotation = rectaActual.Rot.first;
            break;
       }
       case 1:  {  // CurvaData
            CurvaData curvaActual = std::get<CurvaData>(circuito.instrucciones[indexActualPiece]);   

            double radio_offset = (piloto->isRight == curvaActual.isClockwise) 
                ? CURVA_OFFSET_VENTAJA
                : CURVA_OFFSET_DESVENTAJA;
            float angulo = curvaActual.Angulo;
            float circunferencia = 2 * MY_PI * (CURVA_RADIO_CENTRO + radio_offset);
            float distanciaArco = circunferencia / 360 * angulo;

            float anguloCoche = angulo * ( piloto->distanciaRecorridaEnPieza / distanciaArco );
            if (curvaActual.isClockwise) anguloCoche *= -1;

            newPosition = Circuito::ActualizaPosicionCurva(curvaActual.PosIni, curvaActual.Rot.first, anguloCoche);
            newRotation = anguloCoche + curvaActual.Rot.first;
            break;
       }
    }

    piloto->ResetLocation();
    piloto->Translate(newPosition);
    piloto->Rotate(newRotation + 180, glm::vec3(0,0,1)); //+180 Dado que el modelo del coche esta al reves
    if(piloto->isRight)
    {
        piloto->Translate(glm::vec3(-1.7,0,0));
    }
    else
    {
        piloto->Translate(glm::vec3(+1.7,0,0));    
    }
    piloto->position = newPosition;
    piloto->rotation = newRotation;
}

void Escena::Update(){
    ActualizaCoche(sainz);
    ActualizaCoche(nano);
}

void Escena::UpdateCamara(CamaraEnum camaraActiva, Camara* camara)
{
    glm::mat4 matriz;
    switch (camaraActiva)
    {
        case CamaraEnum::Circuito: {
            camara->SetPosition(
                0.0f, 5.0f, 160.0f
            );
            camara->SetDirection(
                0.0f, 0.0f, 1.0f,
                0.0f, 1.0f, 0.0f
                );
            return;
            break;
        }
        case CamaraEnum::Nano: {
            
            matriz = nano->GetLocation();
            break;
        }
        case CamaraEnum::Sainz: {
            matriz = sainz->GetLocation();
            break;
        }
    }
    matriz = glm::translate(matriz, glm::vec3(0.0f, 5.f, 2.0f));
    matriz = glm::rotate(matriz, glm::radians(10.0f), glm::vec3(1.0f, 0.0f, 0.0f));
    camara->SetPosition(matriz[3][0], matriz[3][1], matriz[3][2]);
    camara->SetDirection(matriz[1][0], matriz[1][1], matriz[1][2], matriz[2][0], matriz[2][1], matriz[2][2]);
    /*
    matrix[0][0] = Right.x;
    matrix[1][0] = Right.y;
    matrix[2][0] = Right.z;
    matrix[3][0] = 0.0f;
    matrix[0][1] = Up.x;
    matrix[1][1] = Up.y;
    matrix[2][1] = Up.z;
    matrix[3][1] = 0.0f;
    matrix[0][2] = Dir.x;
    matrix[1][2] = Dir.y;
    matrix[2][2] = Dir.z;
    matrix[3][2] = 0.0f;
    matrix[0][3] = 0.0f;
    matrix[1][3] = 0.0f;
    matrix[2][3] = 0.0f;
    matrix[3][3] = 1.0f;
    */

}