clc, clear
I = imread('ImagenBinaria.tif');

% Comprobar que solo existen 1 en la imagen
% Bug: Si la imagen está formada por 0 y por 
% cualquier otro numero tambien se considera binaria
if size(unique(I(I ~= 0))) > 1
    disp(['No es una imagen binaria'])
end

Ib = I>0;
[i_etiq, N] = Etiquetar(Ib);
etiquetas = unique(i_etiq(i_etiq ~= 0));
[n_rows, n_cols] = size(i_etiq);
new_image = zeros(n_rows, n_cols, 3);

for etiqueta = 1:size(etiquetas)
    Ib = i_etiq==etiquetas(etiqueta);
    Color = [randi([0,255]), randi([0,255]), randi([0,255])];
    new_image = funcion_visualiza(new_image, Ib, Color, true);
end

imshow(uint8(new_image))
%cr = randi([0, 255])