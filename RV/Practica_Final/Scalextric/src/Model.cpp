#include <Model.h>

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


    // Crea la cámara
    camera = new Camara();
    camera->SetPosition(0.0f, 5.0f, 15.0f);

    // Crea la escena
    Circuito Monaco;
    scene = new Escena(Monaco);


    // Asigna el viewport y el clipping volume
    resize(w, h);

    // Opciones de dibujo
    glEnable(GL_DEPTH_TEST);
    glEnable(GL_CULL_FACE);
    glFrontFace(GL_CCW);
    glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);

}


//
// FUNCIÓN: Model::finalize()
//
// PROPÓSITO: Libera los recursos del modelo 3D
//
void Model::finalize()
{
    delete camera;
    delete scene;
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
    glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

    glm::mat4 view = camera->ViewMatrix();
    scene->Draw(program, projection, view);
}

//
// FUNCIÓN: Model::update()
//
// PROPÓSITO: Anima la escena
//
void Model::update()
{
    //camera->MoveFront();
}

//
// FUNCIÓN: Model::key_pressed(int)
//
// PROPÓSITO: Respuesta a acciones de teclado
//
void Model::key_pressed(int key)
{
    switch (key)
    {
        case GLFW_KEY_UP:
            camera->TurnDown();
            break;
        case GLFW_KEY_DOWN:
            camera->TurnUp();
            break;
        case GLFW_KEY_LEFT:
            camera->TurnCCW();
            break;
        case GLFW_KEY_RIGHT:
            camera->TurnCW();
            break;
        case GLFW_KEY_S:
            camera->SetMoveStep(0.0f);
            break;
        case GLFW_KEY_KP_ADD:
            camera->SetMoveStep(camera->GetMoveStep() + 0.5f);
            break;
        case GLFW_KEY_B:
            camera->MoveBack();
            break;
        case GLFW_KEY_KP_SUBTRACT:
            camera->SetMoveStep(camera->GetMoveStep() - 0.5f);
            break;
        case GLFW_KEY_F:
            camera->MoveFront();
            break;
        case GLFW_KEY_Q:
            camera->MoveUp();
            break;
        case GLFW_KEY_A:
            camera->MoveDown();
            break;
        case GLFW_KEY_O:
            camera->MoveLeft();
            break;
        case GLFW_KEY_P:
            camera->MoveRight();
            break;
        case GLFW_KEY_K:
            camera->TurnLeft();
            break;
        case GLFW_KEY_L:
            camera->TurnRight();
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