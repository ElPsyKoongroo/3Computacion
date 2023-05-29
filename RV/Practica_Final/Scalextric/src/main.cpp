#include <iostream>
#include <Aplicacion.h>
#include <stdexcept>

/*
Clases:
	Figura		-> rectas, curvas, noseke
	Coche		-> lo que viene a ser un coche
	Camara		-> las camaras
	Aplicacion	-> el loop completo de la aplicacion
	Model		-> cosos graficos
	Shader		-> los shaders
	Scene		-> la escena con todas las cosas -> coches, circuito, luz, camaras
	Luz			-> no se ticher no he estudiao todavia
	Material	-> eso
	Circuito	-> circuito + suelo	

TODOS:


	*/

auto WinMain() -> int {
	Aplicacion app;

	try {
		app.Run();
	}
	catch (const std::exception& e) {
		std::cerr << e.what() << std::endl;
		return EXIT_FAILURE;
	}

	return EXIT_SUCCESS;
}
