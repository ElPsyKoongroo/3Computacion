%% Selecionar areas de interes, region del color que me interesa
% clearvars; Por motivos de seguridad lo dejo comentado

clc;
addpath("C:\Users\usuario\Desktop\3Computacion\SP\PracticaExamen\01_GeneracionMaterial")

load("DatosDeEjemplo.mat", "Fotitos")

figure(), hold on;

n_imagenes = 15;

DatosColor = PoI;
% 9
for i = 1:15
    I = uint8 (Fotitos(:,:,:,i));
    imshow(I);
    roi = roipoly(I);
    DatosColor(:,:,i) = roi;
    pause(1);
end

DatosColor2 = zeros(n_imagenes, 4);

DatosColor3 = [1, DatosColor3(:,:,1)]

for i = 1:n_imagenes 
    I = Fotitos(:,:,:,i);
    Ib = logical (DatosColor(:,:,i));
    R = I(:,:,1);
    G = I(:,:,2);
    B = I(:,:,3);

    R = reshape(R,[],1);
    G = reshape(G,[],1);
    B = reshape(B,[],1);

    DatosColor2 = [DatosColor2; ones(size(a))*i, R, G, B];
end

DatosFondo = zeros(240, 320, n_imagenes);
for i = 1:15
    I = uint8 (Fotitos(:,:,:,i));
    imshow(I);
    roi = roipoly(I);
    DatosFondo(:,:,i) = roi;
    pause(1);
end


for i = 1:15
    I = Fotitos(:,:,:,i);
    Ib = logical (DatosFondo(:,:,i));
    I(Ib) = 255;
    imshow(I);
    pause(2);
end


close all;