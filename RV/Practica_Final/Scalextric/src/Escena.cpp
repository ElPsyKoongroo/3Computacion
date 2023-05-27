#include "Escena.h"
#include <GL/glew.h>
#include <glm/glm.hpp>
#include <glm/gtc/matrix_transform.hpp>
#include "ShaderProgram.h"
#include "Figura.h"
#include "Recta.h"
#include "Curva.h"

//
// FUNCIÓN: CGScene::CGScene()
//
// PROPÓSITO: Construye el objeto que representa la escena
//
Escena::Escena()
{

    fig0 = new Recta(10.0f, 20.0f);
    fig0->Translate(glm::vec3(-25.0f, 5.0f, 25.0f));
    //fig0->Rotate(-90.0f, glm::vec3(0.0f, 0.0f, 1.0f));


    fig1 = new Curva(5, 20, 12.5f, 25.0f);
    fig1->Translate(glm::vec3(25.0f, 5.0f, 25.0f));
    //fig1->Rotate(-90.0f, glm::vec3(1.0f, 0.0f, 0.0f));


}

//
// FUNCIÓN: CGScene3:~CGScene()
//
// PROPÓSITO: Destruye el objeto que representa la escena
//
Escena::~Escena()
{
    delete fig0;
    delete fig1;
}

//
// FUNCIÓN: CGScene::Draw()
//
// PROPÓSITO: Dibuja la escena
//
void Escena::Draw(ShaderProgram* program, glm::mat4 proj, glm::mat4 view)
{
    // ground->Draw(program, proj, view);
    fig0->Draw(program, proj, view);
    fig1->Draw(program, proj, view);
}