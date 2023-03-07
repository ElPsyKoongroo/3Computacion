%% Ejercicio 1

clear, clc;
I_Original = imread('Sergiosito.jpeg');
I = double (imresize(I_Original, 0.33));
[n_rows, n_cols, rgb] = size(I);
%{
1.- Utilizando la función de Matlab subplot, muestre en una 
    misma ventana tipo figure la imagen capturada y 3 imágenes 
    que resalten, sobre la imagen original, aquellos píxeles cuya
    intensidad sea mayor que un determinado umbral (asigne para 
    esas 3 imágenes, valores de umbral: 70, 140 y 210, 
    respectivamente). La intensidad de un píxel se calculará 
    como la media de los niveles de gris de las componentes 
    roja, verde y azul.

%}
I_media2 = (I(:,:,1)+I(:,:,2)+I(:,:,3)) / 3;





umbrales = [70,140,210];
I_Umbrales = uint8(zeros([n_rows, n_cols, 3, 3]));

figure(), 
subplot(2,2,1), imshow(uint8(I)), hold on;

for i = 1:3 
    I_Umbrales(:, :, 1, i) = (I_media2>umbrales(i)) * 255;
    I_Umbrales(:, :, 2, i) = (I_media2>umbrales(i)) * 255; 
    I_Umbrales(:, :, 3, i) = (I_media2>umbrales(i)) * 255;
    subplot(2,2,i+1), imshow((I_Umbrales(:,:,:, i) + uint8(I))), hold on;
end
close all;
%% Ejercicio 2

%{
    2.- Para cada una de las imágenes generadas en el apartado anterior:
   Visualice sobre la imagen original las 5 agrupaciones mayores de píxeles conectados.
   Localice a través de su centroide las agrupaciones anteriores y, en otro color, el
    centroide de la mayor agrupación para distinguirla.

%}
% imshow(I_Umbrales(:,:,:,1));
figure();
for i = 1:3 
    temp = I_Umbrales(:,:,:,i);
    Ib = temp(:,:,1) == 255;
    [IEtiq, N] = Etiquetar(Ib);
    
    new_image = zeros(n_rows, n_cols, 3);
    areas = funcion_calcula_areas(IEtiq, N);
    centroides = funcion_calcula_centroides(IEtiq, N);
    [sorted_areas, indexes] = sort(areas, 'descend');

    for j = 1:5
        Color = round(255*rand(1,3)); 
        Ib = IEtiq == indexes(j);
        new_image = funcion_visualiza(new_image, Ib, Color);
    end
    subplot(2,2,i), imshow(uint8(new_image)), hold on,

    for j = 2:5
        plot(centroides(indexes(j),1), centroides(indexes(j),2), '*r'), hold on
    end
    plot(centroides(indexes(1),1), centroides(indexes(1),2), '*g');
    
end

