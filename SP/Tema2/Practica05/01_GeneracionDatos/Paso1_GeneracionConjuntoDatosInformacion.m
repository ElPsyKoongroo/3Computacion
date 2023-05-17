clear all, clc

addpath("../../Funciones/");
addpath("../ImagenesPractica5/Entrenamiento/")

nombre_clases        = {'Circulo', 'Cuadrado', 'Triangulo'};
nombre_descriptores  = {'Compacticidad', 'Excentricidad', 'Solidez', 'Extension', 'ExtensionInvRot','Hu1','Hu2','Hu3','Hu4','Hu5','Hu6','Hu7', 'DF1', 'DF2', 'DF3', 'DF4', 'DF5', 'DF6', 'DF7', 'DF8', 'DF9', 'DF10', 'Euler'};
codif_clases         = [69, 360, 420];
num_clases           = length(codif_clases);
simbolos_clases      = {'*r', '*b', '*g'};

numImagenesPorClase = 2;

X = [];
Y = [];

for i = 1:num_clases
    for imagen = 1:numImagenesPorClase
        nombre_imagen = [nombre_clases{i} num2str(imagen,'%02d') '.jpg'];
        I = imread(nombre_imagen);
    
        Ib = I < 255*graythresh(I);
        Ib_filtrada = funcion_elimina_regiones_ruidosas(Ib);
        [IEtiq, N] = bwlabel(Ib_filtrada);
        
        XImagen = funcion_calcula_descriptores_objetos(IEtiq, N);
        YImagen = codif_clases(i)*ones(N,1);
    
        X = [X; XImagen];
        Y = [Y; YImagen];
    end
end


nombresProblema.clases = nombre_clases;
nombresProblema.descriptores = nombre_descriptores;
nombresProblema.simbolos = simbolos_clases;
nombresProblema.num_clases = num_clases;
nombresProblema.codif_clases = codif_clases;

% Comentado para que no me sobreescriba los datos
save("DatosGenerados/conjunto_datos", "X", "Y");
save("DatosGenerados/nombres_problema", "nombresProblema");