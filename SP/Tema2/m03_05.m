% Turbo clasifier

clear
clc
close all

addpath('MaterialFacilitado\ImagenesEjemploLetrasXY');
addpath('MaterialFacilitado\Funciones')

nombreClases = {'X', 'Y'};
codifClases = [2,5];
numClases = length(nombreClases);

X = [];
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
    X = [X; datosImagen];
    Y = [Y; Yimagen];

end

save("DatosGenerados/conjunto_datos", "X", "Y");

%% Crear informacion del problema
clc, clear 

nombresDescriptores = {'Hu1','Hu2','Hu3','Hu4','Hu5','Hu6','Hu7'};
nombreClases        = {'Clase X', 'Clase Y'};
simbolosClases      = {'*r', '*b'};

nombresProblema = [];
nombresProblema.descriptores = nombresDescriptores;
nombresProblema.clases = nombreClases; 
nombresProblema.simbolos = simbolosClases;

save("DatosGenerados/info_problema", "nombresProblema");

%% Funcion representa datos (X,Y, espacioCCas, nombreProblema)
clc, clear

load("DatosGenerados\conjunto_datos.mat")
load("DatosGenerados\info_problema.mat")

%espacioCcas = [1 3];
espacioCcas = [1 3 5];


% Funcion para mostrar datos:

figure(), hold on
codifClases = unique(Y);
numClases = length(codifClases);
numCcas = length(espacioCcas);
for i = 1:numClases 
    Xi = X(Y==codifClases(i), espacioCcas);
    if numCcas == 2
        plot(Xi(:,1), Xi(:,2), nombresProblema.simbolos{i}), hold on;
    else
        plot3(Xi(:,1), Xi(:,2), Xi(:,3), nombresProblema.simbolos{i}), hold on;
    end
end

legend(nombresProblema.clases)
xlabel(nombresProblema.descriptores{espacioCcas(1)})
ylabel(nombresProblema.descriptores{espacioCcas(2)})

if numCcas == 3
    zlabel(nombresProblema.descriptores{espacioCcas(3)})
end

hold off

%% Ejercicio 1 Clase
clc, clear;
datoHu1 = 1.1;
I = imread("MaterialFacilitado\ImagenesEjemploLetrasXY\X.jpg");


Ib = I < 255*graythresh(I);


[IEtiq, N] = bwlabel(Ib);

Ximagen = funcion_calcula_Hu_objetos_imagen(IEtiq, N);


%% Calcular bounding box
clc, clear
I = imread("MaterialFacilitado\ImagenesEjemploLetrasXY\X.jpg");



Ib = I < 255*graythresh(I);
[IEtiq, N] = bwlabel(Ib);

imshow(IEtiq==1), hold on

[f,c] = find(IEtiq==1);

centroide = [mean(f), mean(c)];



plot(centroide(2), centroide(1), '*r');

fmax = max(f)+0.5; fmin = min(f)-0.5;
cmax = max(c)+0.5; cmin = min(c)-0.5;

numPix   = length(f)
numPixBB = (fmax-fmin)*(cmax-cmin);

E = numPix/numPixBB;

stats = regionprops(IEtiq, 'Extent');
E_Ietiq = cat(1, stats.Extent)



%%
rmpath('MaterialFacilitado\ImagenesEjemploLetrasXY');
rmpath('MaterialFacilitado\Funciones')
%%