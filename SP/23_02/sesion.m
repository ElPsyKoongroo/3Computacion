clc
I = imread('ImagenBinaria.tif');
Ib = I>0;
[i_etiq, N] = Etiquetar(Ib);
etiquetas = unique(i_etiq(i_etiq ~= 0));
[n_rows, n_cols] = size(i_etiq);
new_image = zeros(n_rows, n_cols, 3);

for etiqueta = 1:6
    Ib = i_etiq==etiquetas(etiqueta);
    Color = [randi([0,255]), randi([0,255]), randi([0,255])]
    new_image = funcion_visualiza(new_image, Ib, Color);
end

imshow(uint8(new_image))
%cr = randi([0, 255])