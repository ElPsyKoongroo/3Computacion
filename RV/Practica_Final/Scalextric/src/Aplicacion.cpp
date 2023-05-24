#include "Aplicacion.h"

//
// FUNCIÓN: Aplicacion::run()
//
// PROPÓSITO: Ejecuta la aplicación
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
// FUNCIÓN: Aplicacion::initWindow()
//
// PROPÓSITO: Inicializa la ventana
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
// FUNCIÓN: Aplicacion::initOpenGL()
//
// PROPÓSITO: Inicializa el entorno gráfico
//
void Aplicacion::initOpenGL()
{
    glewInit();
}

//
// FUNCIÓN: Aplicacion::initModel()
//
// PROPÓSITO: Inicializa el modelo
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
// FUNCIÓN: Aplicacion::mainLoop()
//
// PROPÓSITO: Bucle principal que procesa los eventos de la aplicación
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
// FUNCIÓN: Aplicacion::timing()
//
// PROPÓSITO: Renderizado
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
// FUNCIÓN: Aplicacion::cleanup()
//
// PROPÓSITO: Libera los recursos y finaliza la aplicación
//
void Aplicacion::cleanup()
{
    model.finalize();
    glfwDestroyWindow(window);
    glfwTerminate();
}

//
// FUNCIÓN: Aplicacion::keyCallback(GLFWwindow* window, int key, int scancode, 
//                                                         int action, int mods)
//
// PROPÓSITO: Respuesta a un evento de teclado sobre la aplicación
//
void Aplicacion::keyCallback(GLFWwindow* window, int key, int scancode,
    int action, int mods)
{
    auto app = reinterpret_cast<Aplicacion*>(glfwGetWindowUserPointer(window));
    if (action == GLFW_PRESS || action == GLFW_REPEAT) app->model.key_pressed(key);
}

//
// FUNCIÓN: CAApplication::mouseButtonCallback(GLFWwindow* window, int button, 
//                                                         int action, int mods)
//
// PROPÓSITO: Respuesta a un evento de ratón sobre la aplicación
//
void Aplicacion::mouseButtonCallback(GLFWwindow* window, int button,
    int action, int mods)
{
    auto app = reinterpret_cast<Aplicacion*>(glfwGetWindowUserPointer(window));
    app->model.mouse_button(button, action);
}

//
// FUNCIÓN: Aplicacion::cursorPositionCallback(GLFWwindow* window, double xpos, 
//                                                                    double ypos)
//
// PROPÓSITO: Respuesta a un evento de movimiento del cursor sobre la aplicación
//
void Aplicacion::cursorPositionCallback(GLFWwindow* window, double xpos,
    double ypos)
{
    auto app = reinterpret_cast<Aplicacion*>(glfwGetWindowUserPointer(window));
    app->model.mouse_move(xpos, ypos);
}

//
// FUNCIÓN: Aplicacion::framebufferResizeCallback(GLFWwindow* window, int width, 
//                                                                       int height)
//
// PROPÓSITO: Respuesta a un evento de redimensionamiento de la ventana principal
//
void Aplicacion::framebufferResizeCallback(GLFWwindow* window, int width,
    int height)
{
    auto app = reinterpret_cast<Aplicacion*>(glfwGetWindowUserPointer(window));
    if (height != 0) app->model.resize(width, height);
}