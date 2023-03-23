%% Selecionar areas de interes, region del color que me interesa
clearvars; %Por motivos de seguridad lo dejo comentado

%% Cargar los datos
load("SeleccionColor.mat", "SeleccionColor");
load("SeleccionFondo.mat", "SeleccionFondo");
load("DatosGenerados.mat", 'X', 'Y');
%% Añade los paths
addpath("../01_GeneracionMaterial")
addpath("funciones/")
%%

clc;


load("DatosDeEjemplo.mat", "Fotitos")

n_imagenes = 15;

SeleccionColor = zeros(240, 320, n_imagenes);

%% Roi colores
figure(), hold on;
for i = 1:15
    I = uint8 (Fotitos(:,:,:,i));
    imshow(I);
    roi = roipoly(I);
    SeleccionColor(:,:,i) = roi;
    pause(1);
end
save('SeleccionColor', 'SeleccionColor');


%% Genera Datos Color
DatosColor = [];
for i = 1:n_imagenes 
    I = Fotitos(:,:,:,i);
    Ib = logical(SeleccionColor(:,:,i));
    R = I(:,:,1);
    G = I(:,:,2);
    B = I(:,:,3);
    R = R(Ib);
    G = G(Ib);
    B = B(Ib);
    DatosColor = [DatosColor; ones(size(R))*i, R, G, B];
end
save('DatosColor.mat', 'DatosColor');

%% Roi fondo
SeleccionFondo = zeros(240, 320, n_imagenes);
for i = 1:15
    I = uint8 (Fotitos(:,:,:,i));
    imshow(I);
    roi = roipoly(I);
    SeleccionFondo(:,:,i) = roi;
    pause(1);
end
close all;
save('SeleccionFondo', 'SeleccionFondo');

%% Genera datos fondo
DatosFondo = [];
for i = 1:15
    I = Fotitos(:,:,:,i);
    Ib = logical (SeleccionFondo(:,:,i));
    R = I(:,:,1);
    G = I(:,:,2);
    B = I(:,:,3);
    R = R(Ib);
    G = G(Ib);
    B = B(Ib);
    DatosFondo = [DatosFondo; ones(size(R))*i, R, G, B];
end

%% Generar X, Y, y guardar
X = [DatosColor; DatosFondo];
Y = [ones(size(DatosColor, 1),1); zeros(size(DatosFondo,1),1)];
% save('DatosGenerados', 'X', 'Y');

%% Eliminar outliers y visualizar
visualiza_datos(X,Y,2);
[X_i,Y_i] = funcion_elimina_outliers(X,Y,2);
visualiza_datos(X_i,Y_i,2);


%% 
rmpath(".\PracticaExamen\01_GeneracionMaterial")
rmpath("funciones/")
close all;