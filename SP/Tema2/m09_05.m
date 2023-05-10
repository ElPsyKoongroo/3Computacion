%%
clear, clc

addpath("MaterialFacilitado/ImagenesPractica5/Entrenamiento/")
addpath("MaterialFacilitado/Funciones/")

nombreClases        = {'Circulo', 'Cuadrado', 'Triangulo'};
codifClases         = [360, 420, 69];
numClases           = length(codifClases);
simbolosClases      = {'*r', '*b', '*g'};

X = [];
Y = [];

numImagenesPorClase = 2;

for i = 1:numClases
    for imagen = 1:numImagenesPorClase
        nombre_imagen = [nombreClases{i} num2str(imagen,'%02d') '.jpg'];
        I = imread(nombre_imagen);
    
        Ib = I < 255*graythresh(I);
        [IEtiq, N] = bwlabel(Ib);
        
        XImagen = funcion_calcula_descriptores_objetos(IEtiq, N);
        YImagen = codifClases(i)*ones(N,1);
    
        X = [X; XImagen];
        Y = [Y; YImagen];
    end
end

%%

nombresProblema.clases = nombreClases;
nombresProblema.descriptores = {'Compacticidad', 'Excentricidad', 'Solidez', 'Extension', 'ExtensionInvRot','Hu1','Hu2','Hu3','Hu4','Hu5','Hu6','Hu7', 'DF1', 'DF2', 'DF3', 'DF4', 'DF5', 'DF6', 'DF7', 'DF8', 'DF9', 'DF10', 'Euler'};
nombresProblema.simbolos = {'*b', '+r', '*g'};
nombresProblema.numDatos = numClases;

funcion_representa_datos(X, Y, [2 3], nombresProblema)

%%
%{ 

Hacer: funcion_calcula_extensio_en_objetos() (Como la de hu)
===============================================================

funcion_calcula_descriptores() [23 descriptores]

Compacticidad    -> manual        [perimetro.^2 ./ area]
Excentricidad    -> regionprops
Solidez          -> regionprops
Extension        -> regionprops
ExtensionInvRot  -> manual
Hu1-7            -> manual
DF1-10           -> manual
Euler            -> regionprops

==========================================================
Para filtrar segun el numero de pixeles de las agrupaciones
                            
   +-------------------------------------------------------+
   | Tiene que ser un numero entero, no olvidar el round() |
   +-------------------------------------------------------+
                                \
                                 \ 
                             VVVVVVVVVVV
Ib_filtrada = bwareaopen(Ib, min_pixeles)

==========================================================

%}
%%


    clear
    %I_centrada = funcion_centra_objeto();
    ang = [0:5:355];

    for i = 1:length(ang)
        Ib_rot = imrotate(I_centrada, ang(i))
    end

    % Calcular la extension manualmente como en 
    % m03_05.m
