% Turbo clasifier

clear
clc
close all

addpath('C:\Users\usuario\Desktop\MaterialFacilitado\ImagenesEjemploLetrasXY');
addpath('C:\Users\usuario\Desktop\MaterialFacilitado\Funciones')

nombreClases = {'X', 'Y'};
codifClases = [2,5];
numClases = length(nombreClases);

Datos = [];
Y = [];

for i = 1:numClases
    I = imread([nombreClases{i}  '.jpg']);

    % Otsu
    Ib = I < 255*graythresh(I);
    
    [IEtiq, N] = bwlabel(Ib);

    datosImagen = zeros(N, 7);

    for j = 1:N
        ObjetoX = IEtiq == j;
        datosImagen(j, :) = Funcion_Calcula_Hu(ObjetoX)';
    end
    
    Yimagen = codifClases(i)*ones(N,1);
    Datos = [Datos; datosImagen];
    Y = [Y; Yimagen];

end

    
    

rmpath('C:\Users\usuario\Desktop\MaterialFacilitado\ImagenesEjemploLetrasXY');
rmpath('C:\Users\usuario\Desktop\MaterialFacilitado\Funciones')
%%