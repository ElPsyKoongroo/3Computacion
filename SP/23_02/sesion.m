clc, clear
I = imread('ImagenBinaria.tif');

% Comprobar que solo existen 1 en la imagen
% Bug: Si la imagen está formada por 0 y por 
% cualquier otro numero tambien se considera binaria
if size(unique(I(I ~= 0))) > 1
    disp(['No es una imagen binaria'])
end

Ib = I==255;
[i_etiq, N] = Etiquetar(Ib);
n_etiquetas = unique(i_etiq(i_etiq ~= 0));
[n_rows, n_cols] = size(i_etiq);
new_image = zeros(n_rows, n_cols, 3);

for etiqueta = 1:N
    Ib = i_etiq==etiqueta;
    Color =  round(255*rand(1,3)); %[randi([0,255]), randi([0,255]), randi([0,255])];
    new_image = funcion_visualiza(new_image, Ib, Color);
end

imshow(uint8(new_image))
%cr = randi([0, 255])
%% Ejercicio 4

%{

    !TODO
    
    1. Hacer la funcion calcula areas
    2. Hacer la funcion calcula centroides
    3. Usar funcion sort para que devuelva 2 variables, el vector ordenado
        y otro vector con los indices en las posiciones correspondiente
        (mas pequeño, segundo mas pequeño, tercero mas pequeño, ..., mas grande)
    
    4. Funcion para filtrar objetos.
        Tiene que usar funcion_etiquetar y calcula_areas;
        Mantener solo aquellas etiquetas cuyas areas 
        cumplan la condicion del enunciado
    
}%
