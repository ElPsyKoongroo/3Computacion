clc, clear
I = imread('ImagenBinaria.tif');

% Comprobar que solo existen 1 en la imagen
% Bug: Si la imagen está formada por 0 y por 
% cualquier otro numero tambien se considera binaria
if size(unique(I(I ~= 0))) > 1
    disp(['No es una imagen binaria'])
end

Ib = I==255;
[IEtiq, N] = Etiquetar(Ib);
[n_rows, n_cols] = size(IEtiq);
new_image = zeros(n_rows, n_cols, 3);

for etiqueta = 1:N
    Ib = IEtiq==etiqueta;
    Color =  round(255*rand(1,3)); 
    new_image = funcion_visualiza(new_image, Ib, Color, false);
end
imshow(uint8(new_image))


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
    
%}
    clc
    areas = funcion_calcula_areas(IEtiq, N);
    centroides = funcion_calcula_centroides(IEtiq, N);

    [sorted_areas, indexes] = sort(areas, 'descend');
    imshow(I);
    hold on, plot(centroides(indexes(1),1), centroides(indexes(1),2), '*r');
    hold on, plot(centroides(indexes(N-1),1), centroides(indexes(N-1),2), '*r');
    
    
    %{
    % Mostrar todos los centroides

    for i = 1:N-1
        hold on, plot(centroides(indexes(i),1), centroides(indexes(i),2), '*r');
    end
    %}
    
    clc;






