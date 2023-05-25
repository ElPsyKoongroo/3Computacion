#include "Model.h"

//
// FUNCIÓN: Model::initialize(int, int)
//
// PROPÓSITO: Initializa el modelo 3D
//
void Model::initialize(int w, int h)
{
    // Crea el programa
    program = new ShaderProgram();
    if (program->IsLinked() == GL_TRUE) program->Use();
    else std::cout << program->GetLog() << std::endl;

    // Inicializa la posición de las figuras
    figure = 0;
    xAngle = 0.0f;
    yAngle = 0.0f;

    // Crea las figuras
    p = 5;
    m = 20;
    r0 = 1.5f;
    r1 = 25.0f;
    fig0 = new Curva(p, m, r0, r1);

    // Asigna el viewport y el clipping volume
    resize(w, h);

    // Opciones de dibujo
    glEnable(GL_DEPTH_TEST);
    glEnable(GL_CULL_FACE);
    glFrontFace(GL_CCW);
    glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);

}

void Model::changeCurve(int opc , GLint par, GLfloat parf) {
    delete fig0;
    switch (opc)
    {
    case 0:
        p += par;
        fig0 = new Curva(p, m, r0, r1);
        break;
    case 1:
        m += par;
        fig0 = new Curva(p, m, r0, r1);
        break;
    case 2:
        r0 += parf;
        fig0 = new Curva(p, m, r0, r1);
        break;
    case 3:
        r1 += parf;
        fig0 = new Curva(p, m, r0, r1);
        break;
    default:
        break;
    }
}

//
// FUNCIÓN: Model::finalize()
//
// PROPÓSITO: Libera los recursos del modelo 3D
//
void Model::finalize()
{
    delete fig0;
    delete program;
}

//
// FUNCIÓN: Model::resize(int w, int h)
//
// PROPÓSITO: Asigna el viewport y el clipping volume
//
void Model::resize(int w, int h)
{
    double fov = glm::radians(15.0);
    double sin_fov = sin(fov);
    double cos_fov = cos(fov);
    if (h == 0) h = 1;
    GLfloat aspectRatio = (GLfloat)w / (GLfloat)h;
    GLfloat wHeight = (GLfloat)(sin_fov * 0.2 / cos_fov);
    GLfloat wWidth = wHeight * aspectRatio;

    glViewport(0, 0, w, h);
    projection = glm::frustum(-wWidth, wWidth, -wHeight, wHeight, 0.2f, 400.0f);
}

//
// FUNCIÓN: Model::render()
//
// PROPÓSITO: Genera la imagen
//
void Model::render()
{
    // Limpia el framebuffer
    glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

    // Matriz de transformación
    glm::mat4 location = glm::mat4(1.0f);
    location = glm::translate(location, glm::vec3(0.0f, 0.0f, -180.0f));
    location = glm::rotate(location, glm::radians(yAngle), glm::vec3(0.0f, 1.0f, 0.0f));
    location = glm::rotate(location, glm::radians(xAngle), glm::vec3(1.0f, 0.0f, 0.0f));

    glm::mat4 transform = projection * location;

    switch (figure) {
    case 0: fig0->Draw(program, transform); break;
    //case 1: fig1->Draw(program, transform); break;
    //case 2: fig2->Draw(program, transform); break;
    //case 3: fig3->Draw(program, transform); break;
    //case 4: fig4->Draw(program, transform); break;
    //case 5: fig5->Draw(program, transform); break;
    //case 6: fig6->Draw(program, transform); break;
    //case 7: fig7->Draw(program, transform); break;
    }
}

//
// FUNCIÓN: Model::update()
//
// PROPÓSITO: Anima la escena
//
void Model::update()
{
}

//
// FUNCIÓN: Model::key_pressed(int)
//
// PROPÓSITO: Respuesta a acciones de teclado
//
void Model::key_pressed(int key)
{
    static int val = 1;
    switch (key)
    {
        case GLFW_KEY_R:
        glClearColor(1.0f, 0.0f, 0.0f, 1.0f);
        break;
        case GLFW_KEY_G:
        glClearColor(0.0f, 1.0f, 0.0f, 1.0f);
        break;
        case GLFW_KEY_B:
        glClearColor(0.0f, 0.0f, 1.0f, 1.0f);
        break;
        case GLFW_KEY_W:
        glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        break;
        case GLFW_KEY_KP_ADD:
            val *= -1;
            break;
        case GLFW_KEY_0:
            changeCurve(0, val, 0);
            break;
        case GLFW_KEY_1:
            changeCurve(1, val, 0);
            break;
        case GLFW_KEY_2:
            changeCurve(2, 0, val);
            break;
        case GLFW_KEY_3:
            changeCurve(3, 0, val);
            break;

    }
}

//
//  FUNCIÓN: Model:::mouse_button(int button, int action)
//
//  PROPÓSITO: Respuesta del modelo a un click del ratón.
//
void Model::mouse_button(int button, int action)
{
}

//
//  FUNCIÓN: Model::mouse_move(double xpos, double ypos)
//
//  PROPÓSITO: Respuesta del modelo a un movimiento del ratón.
//
void Model::mouse_move(double xpos, double ypos)
{
}