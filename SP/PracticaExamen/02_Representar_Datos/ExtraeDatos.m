%% Selecionar areas de interes, region del color que me interesa
clc, clearvars;

%% Cargar los datos
% load("SeleccionColor.mat", "SeleccionColor");
% load("SeleccionFondo.mat", "SeleccionFondo");
load("Datos_Generados/conjunto_datos.mat", 'X', 'Y');
load("Datos_Generados/conjunto_datos_original.mat", "DatosColor", "DatosFondo");
%% Añade los paths
addpath("../01_GeneracionMaterial")
addpath("funciones/")
%%
%   ESTRUCTURA DE X
%  
%   <n_imagen> | <R>  | <G> |  <B>
%   <n_imagen> | <R>  | <G> |  <B>
%   <n_imagen> | <R>  | <G> |  <B>
%   ...
%
%  Mas tarde va a ser necesario ignorar la primera columna
%  ya que solo usaremos los valores RGB 

HEIGHT = 320;
WIDTH  = 240;
load("DatosDeEjemplo.mat", "Fotitos")
N_IMAGENES = 15;
SeleccionColor = zeros(WIDTH, HEIGHT, N_IMAGENES);

%% Roi colores
figure(), hold on;
for i = 1:N_IMAGENES
    I = uint8 (Fotitos(:,:,:,i));
    imshow(I);
    roi = roipoly(I);
    SeleccionColor(:,:,i) = roi;
    pause(1);
end
save('SeleccionColor', 'SeleccionColor');

%% Genera Datos Color
DatosColor = [];
for i = 1:N_IMAGENES 
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
SeleccionFondo = zeros(WIDTH, HEIGHT, N_IMAGENES);
for i = 1:N_IMAGENES
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
for i = 1:N_IMAGENES
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
save("Datos_Generados/conjunto_datos_original", "DatosColor", "DatosFondo")
save("Datos_Generados/conjunto_datos", "X", "Y")

%% Eliminar outliers y visualizar
POS_CLASE_INTERES = 2;
visualiza_datos(X,Y,POS_CLASE_INTERES);
[X,Y] = funcion_elimina_outliers(X,Y,POS_CLASE_INTERES);
visualiza_datos(X_i,Y_i,POS_CLASE_INTERES);
save("Datos_Generados/conjunto_datos_final", "X", "Y")


%% 
rmpath(".\PracticaExamen\01_GeneracionMaterial")
rmpath("funciones/")
close all;


%{ 
    Para sacar imagen intensidad god mode: uint8((R+G+B)/3) 
%}