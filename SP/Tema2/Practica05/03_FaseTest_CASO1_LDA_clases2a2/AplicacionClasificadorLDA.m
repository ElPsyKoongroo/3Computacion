clear, clc;
addpath("../ImagenesPractica5/Test/");
addpath("../Funciones/")
load("../01_GeneracionDatos/DatosGenerados/datos_estandarizacion.mat")
load ("../02_FaseEntrenamiento_CASO1_LDA_clases2a2/01_CirculoCuadrado/DatosGenerados/espacio_ccas_circulos_cuadrados.mat");
load ("../02_FaseEntrenamiento_CASO1_LDA_clases2a2/01_CirculoCuadrado/DatosGenerados/coeficientes_LDA.mat");

espacio_ccas_circulos_cuadrados = espacio_ccas_j;
nombres_problema_circulos_cuadrados = nombres_problema_oi;
d12_circulo_cuadrado = d12;

load ("../02_FaseEntrenamiento_CASO1_LDA_clases2a2/02_CirculoTriangulo/DatosGenerados/espacio_ccas_circulos_triangulos.mat");
load ("../02_FaseEntrenamiento_CASO1_LDA_clases2a2/02_CirculoTriangulo/DatosGenerados/coeficientes_LDA.mat");

espacio_ccas_circulos_triangulo = espacio_ccas_j;
nombres_problema_circulos_triangulo = nombres_problema_oi;
d12_circulo_triangulo = d12;

load ("../02_FaseEntrenamiento_CASO1_LDA_clases2a2/03_CuadradoTriangulo/DatosGenerados/espacio_ccas_cuadrados_triangulos.mat");
load ("../02_FaseEntrenamiento_CASO1_LDA_clases2a2/03_CuadradoTriangulo/DatosGenerados/coeficientes_LDA.mat");

espacio_ccas_cuadrado_triangulo = espacio_ccas_j;
nombres_problema_cuadrado_triangulo = nombres_problema_oi;
d12_cuadrado_triangulo = d12;

num_imagenes = 2;
num_descriptores = 23;

%% Cargar los datos de las imagenes
XTest = [];
for imagen = 1:num_imagenes
    nombre_imagen = "Test" + num2str(imagen) + ".JPG";
    I = imread(nombre_imagen);

    Ib = I < 255*graythresh(I);
    Ib_filtrada = funcion_elimina_regiones_ruidosas(Ib);
    [IEtiq, N] = bwlabel(Ib);
    
    XImagen = funcion_calcula_descriptores_objetos(IEtiq, N);

    XTest = XImagen;
    %% Estandarizar datos
    ZTest = zeros(size(XTest));
    for i = 1:(num_descriptores-1)
        mu_i = medias(i);
        sigma_i = desviaciones(i);
        ZTest(:, i) = (XTest(:, i) - mu_i) / sigma_i;
    end
    
    %% Clasificador
    [num_muestras, ~] = size(ZTest);
    XTest = ZTest;
    
    for muestra = 1:num_muestras
        Xi = XTest(muestra, :);
    
        Xi_circuclo_cuadrado = Xi(:, espacio_ccas_circulos_cuadrados);
        x1 = Xi_circuclo_cuadrado(1);
        x2 = Xi_circuclo_cuadrado(2);
        x3 = Xi_circuclo_cuadrado(3);
        valor_d12_circulo_cuadrado = eval(d12_circulo_cuadrado);
    
        Xi_circulo_triangulo = Xi(:, espacio_ccas_circulos_triangulo);
        x1 = Xi_circulo_triangulo(1);
        x2 = Xi_circulo_triangulo(2);
        x3 = Xi_circulo_triangulo(3);
        valor_d12_circulo_triangulo = eval(d12_circulo_triangulo);
    
        Xi_cuadrado_triangulo = Xi(:, espacio_ccas_cuadrado_triangulo);
        x1 = Xi_cuadrado_triangulo(1);
        x2 = Xi_cuadrado_triangulo(2);
        x3 = Xi_cuadrado_triangulo(3);
        valor_d12_cuadrado_triangulo = eval(d12_cuadrado_triangulo);
        
        
        if valor_d12_circulo_cuadrado > 0 && valor_d12_circulo_triangulo > 0
            clase = "Pertenece a la clase circulo"; 
            color = [255,0,0];
        elseif valor_d12_circulo_cuadrado < 0 && valor_d12_cuadrado_triangulo > 0 
            clase = "Pertenece a la clase cuadrado"; 
            color = [0,255,0];
        elseif valor_d12_circulo_triangulo < 0 && valor_d12_cuadrado_triangulo < 0 
            clase = "Pertenece a la clase triangulo";
            color = [0,0,255];
        else
            clase = "No pertenece a ninguna clase";
            color = [0,0,0];
        end
    
        title(clase), hold on;
        funcion_visualiza(I, IEtiq==muestra, color, true);
        pause
    end
    close all;

end
