#include "Aplicacion.h"

//
// FUNCI�N: Aplicacion::run()
//
// PROP�SITO: Ejecuta la aplicaci�n
//
void Aplicacion::Run()
{
    initWindow();
    initOpenGL();
    initModel();
    mainLoop();
    cleanup();
}

//
// FUNCI�N: Aplicacion::initWindow()
//
// PROP�SITO: Inicializa la ventana
//
void Aplicacion::initWindow()
{
    glfwInit();

    window = glfwCreateWindow(WIDTH, HEIGHT, "Computer Graphics", nullptr, nullptr);
    glfwSetWindowUserPointer(window, this);
    glfwSetFramebufferSizeCallback(window, framebufferResizeCallback);
    glfwSetKeyCallback(window, keyCallback);
    glfwSetCursorPosCallback(window, cursorPositionCallback);
    glfwSetMouseButtonCallback(window, mouseButtonCallback);
    glfwMakeContextCurrent(window);
}

//
// FUNCI�N: Aplicacion::initOpenGL()
//
// PROP�SITO: Inicializa el entorno gr�fico
//
void Aplicacion::initOpenGL()
{
    glewInit();
}

//
// FUNCI�N: Aplicacion::initModel()
//
// PROP�SITO: Inicializa el modelo
//
void Aplicacion::initModel()
{
    limitFPS = 1.0 / 60.0;
    lastTime = glfwGetTime();
    deltaTime = 0;

    int width, height;
    glfwGetFramebufferSize(window, &width, &height);
    model.initialize(width, height);
}

//
// FUNCI�N: Aplicacion::mainLoop()
//
// PROP�SITO: Bucle principal que procesa los eventos de la aplicaci�n
//
void Aplicacion::mainLoop()
{
    while (!glfwWindowShouldClose(window))
    {
        glfwPollEvents();
        timing();
        glfwSwapBuffers(window);
    }
}

//
// FUNCI�N: Aplicacion::timing()
//
// PROP�SITO: Renderizado
//
void Aplicacion::timing()
{
    double nowTime = glfwGetTime();
    deltaTime += (nowTime - lastTime) / limitFPS;
    lastTime = nowTime;

    while (deltaTime >= 1.0)
    {
        model.update();
        deltaTime--;
    }
    model.render();
}

//
// FUNCI�N: Aplicacion::cleanup()
//
// PROP�SITO: Libera los recursos y finaliza la aplicaci�n
//
void Aplicacion::cleanup()
{
    model.finalize();
    glfwDestroyWindow(window);
    glfwTerminate();
}

//
// FUNCI�N: Aplicacion::keyCallback(GLFWwindow* window, int key, int scancode, 
//                                                         int action, int mods)
//
// PROP�SITO: Respuesta a un evento de teclado sobre la aplicaci�n
//
void Aplicacion::keyCallback(GLFWwindow* window, int key, int scancode,
    int action, int mods)
{
    auto app = reinterpret_cast<Aplicacion*>(glfwGetWindowUserPointer(window));
    if (action == GLFW_PRESS || action == GLFW_REPEAT) app->model.key_pressed(key);
}

//
// FUNCI�N: CAApplication::mouseButtonCallback(GLFWwindow* window, int button, 
//                                                         int action, int mods)
//
// PROP�SITO: Respuesta a un evento de rat�n sobre la aplicaci�n
//
void Aplicacion::mouseButtonCallback(GLFWwindow* window, int button,
    int action, int mods)
{
    auto app = reinterpret_cast<Aplicacion*>(glfwGetWindowUserPointer(window));
    app->model.mouse_button(button, action);
}

//
// FUNCI�N: Aplicacion::cursorPositionCallback(GLFWwindow* window, double xpos, 
//                                                                    double ypos)
//
// PROP�SITO: Respuesta a un evento de movimiento del cursor sobre la aplicaci�n
//
void Aplicacion::cursorPositionCallback(GLFWwindow* window, double xpos,
    double ypos)
{
    auto app = reinterpret_cast<Aplicacion*>(glfwGetWindowUserPointer(window));
    app->model.mouse_move(xpos, ypos);
}

//
// FUNCI�N: Aplicacion::framebufferResizeCallback(GLFWwindow* window, int width, 
//                                                                       int height)
//
// PROP�SITO: Respuesta a un evento de redimensionamiento de la ventana principal
//
void Aplicacion::framebufferResizeCallback(GLFWwindow* window, int width,
    int height)
{
    auto app = reinterpret_cast<Aplicacion*>(glfwGetWindowUserPointer(window));
    if (height != 0) app->model.resize(width, height);
}