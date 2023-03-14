%% Selecionar areas de interes, region del color que me interesa
% clearvars; Por motivos de seguridad lo dejo comentado

clc;
addpath("C:\Users\usuario\Desktop\3Computacion\SP\PracticaExamen\01_GeneracionMaterial")

load("DatosDeEjemplo.mat", "Fotitos")

figure(), hold on;

n_imagenes = 15;

SeleccionColor = zeros(240, 320, n_imagenes);
%% Roi colores
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

%% 
for i = 1:15
    I = Fotitos(:,:,:,i);
    Ib = logical (DatosFondo(:,:,i));
    I(Ib) = 255;
    imshow(I);
    pause(2);
end


close all;