%%

%{
    Si la STD == 0 cambiar STD por epsilon (valor minimo de MATLAB)

    No estandarizar EULER

    Importante estandarizar los datos despues de leer las imagenes de los
    test.

    Guardar las medias y las desviaciones de las imagenes de entrenamiento
    para aplicarla en los tests.

    Hierarchy del proyecto:

    Implementacion
        |
        |---> 01_GeneracionDatos
        |       |
        |       |--> DatosGenerados
        |       |       |
        |       |       |--> conjunto_datos.mat
        |       |       |--> conjunto_datos_estandarizados.mat
        |       |       |--> datos_estandarizacion.mat
        |       |       +--> nombres_problema.mat
        |       | 
        |       |--> Funciones
        |       |--> Paso1_GeneracionConjuntoDatosInformacion
        |       |--> Paso2_RepresentacionDatos
        |       +--> Paso3_EstandarizacionDatos
        |
        |
        |
        |---> 02_FaseEntrenamiento
        |---> 03_FaseTest
        +---> Imagenes



    XoI = X(:, [5,6])
               ^^^^^
     ____________/
    /
   V
 De esta forma se pueden seleccionar columnas de la matriz que 
 no sean consecutivas, ej:

    XoI = X(:, [5,10,17])
               ^^^^^^^^^
       ___________/                
      /
     V
Todas las filas, columas 5, 10 y 17 de la matriz X

================================================================

Calcular solo la separabilidad del espacio de ccas 1,4 u 10 
de Circulos y Triangulos.

YCuad = Y == 2;
XoI = X(:, espacioCCas);
YoI = Y;

XoI(YCuad, :) = []
YoI(YCuad) = []

separabilidad = indiceJ(XoI, YoI);

clases_oi = [1,3];
nombres_problemas_oi = nombres_problema.clases(clases_oi);
^^^^^^^^^^^^^^^^^^^^
        \_____
              \
               V
   {'Circulos', 'Triangulos'}


nombres_problema_oi  = nombres_problema.descriptores(espacioCCas);
simbolos_problema_oi = nombres_problema.simbolos(clases_oi);
%}
