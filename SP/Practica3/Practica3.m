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
    [IEtiq, N] = bwlabel(Ib);
    

    stats = regionprops(IEtiq, 'Area', 'Centroid');
    new_image = zeros(n_rows, n_cols, 3);
    % areas = funcion_calcula_areas(IEtiq, N);
    centroides = cat(1, stats.Centroid);
    [sorted_areas, indexes] = sort(cat(1, stats.Area), 'descend');

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

%% Segunda parte

%% Ejercicio 3
%{
    3.- La escena inicialmente oscurecida y aclarándose progresivamente (utilizar la instrucción
        imadjust y valores de gamma entre 0 y 4 con pasos de 0.05).
%}

clc, clear;
imaqhwinfo('linuxvideo');
video = videoinput('linuxvideo',1,'RGB24_320x240');

video.TriggerRepeat = inf;
video.FrameGrabInterval = 2;
start(video);

TIEMPO=[];
figure(), hold on;
Gamma = 4;
while (Gamma > 0)
    [I, TIME, METADATA]=getdata(video,1);
    TIEMPO = [TIEMPO ; TIME METADATA.AbsTime];
    J = imadjust(I, [], [], Gamma);
    imshow(J);
    Gamma = Gamma - 0.05;
end
stop(video)
close all;

%% Ejercicio 4
%{
    4.- Todos los píxeles que tengan una intensidad mayor que un determinado umbral. Asignar
        inicialmente el valor 0 a este umbral e ir aumentándolo progresivamente con pasos de unidad
        hasta el 255.
%}

clc, clear;
imaqhwinfo('linuxvideo');
video = videoinput('linuxvideo',1,'RGB24_320x240');

video.TriggerRepeat = inf;
video.FrameGrabInterval = 2;
start(video);

Umbral = 0;

figure(), hold on;
while (Umbral < 255)
    I = getdata(video,1);
    I(I < Umbral) = 0;
    imshow(I);
    Umbral = Umbral + 1;
    % I(1:50, 1:50, :)
end
stop(video)
close all;

%% EJERCICIO 5
%{
    5.1.- Las diferencias que se producen entre los distintos frames de intensidad que captura la
    webcam (utilizar la instrucción imabsdiff para hacer la diferencia entre el frame actual y el
    adquirido previamente).
%}

clc, clear;

video = videoinput('linuxvideo',1,'RGB24_320x240');
video.ReturnedColorSpace = 'grayscale';
video.TriggerRepeat = inf;
video.FrameGrabInterval = 1;
start(video);

figure(), hold on;
I1 = getdata(video, 1);
while ( video.FramesAcquired < 150 )
    I2 = getdata(video,1);
    imshow(imabsdiff(I1, I2));
    I1 = getdata(video,1);
end
stop(video)
close all;

%% Ejercicio 5.2
%{
    Los píxeles cuyas diferencias de intensidad son significativas (considerar un umbral de
    100 para establecer de diferencias de intensidad significativas).
%}
    clc, clear;
    datos = imaqhwinfo('linuxvideo');
    video = videoinput('linuxvideo',1,'RGB24_320x240');
    video.TriggerRepeat = inf;
    video.ReturnedColorSpace = 'grayscale';

    video.FrameGrabInterval = 2;
    start(video);
    
    figure(), hold on;
    I1 = getdata(video, 1);
    
    figure(), hold on;
    while ( video.FramesAcquired < 100 )
        I2 = getdata(video,1);
        i_intensidad = imabsdiff(I1, I2);
        I1(i_intensidad < 100) = 0;
        imshow(I1);
        I1 = getdata(video,1);
    end
    stop(video)
    close all;

    %% EJERCICIO 5.3
    %{ 
        El seguimiento de la agrupación mayor de píxeles que presenta 
        una diferencia de intensidad significativa. El seguimiento debe 
        visualizarse a través de un punto rojo situado en el centroide 
        de la agrupación. 
    %}

    clc, clear;
    video = videoinput('linuxvideo',1,'RGB24_320x240');
    video.TriggerRepeat = inf;
    video.ReturnedColorSpace = 'grayscale';

    video.FrameGrabInterval = 2;
    start(video);
    
    figure(), hold on;
    I1 = getdata(video, 1);

    while ( video.FramesAcquired < 150 )
        I2 = getdata(video,1);
        Ib = imabsdiff(I1, I2) > 100;
        [IEtiq, N] = bwlabel(Ib);
        stats = regionprops(IEtiq, 'Area', 'Centroid');
        [sorted_areas, indexes] = sort(cat(1,stats.Area), 'descend');
        centroides = cat(1, stats.Centroid);
        imshow(I1), hold on, 
        len = size(indexes);
        if ( len > 0 ) & (indexes(1) ~= 0)
            plot(centroides(indexes(1),1), centroides(indexes(1),2), '*r');
        end
        I1 = getdata(video, 1);
    end

    stop(video);
    close all; 
    
%% Guardar fotitos

stop(video);
clc, clear;
video = videoinput('linuxvideo',1,'RGB24_320x240');
video.TriggerRepeat = inf;

% preview(video)
start(video)
preview(video)
pause(2);
for i = 1:15
    I = getsnapshot(video);
    imwrite(I, ("ejemplos"+i+".jpg"));
    pause(2);
end
stop(video)
close all;
closepreview(video);

%% Guardar video
stop(video)

clc, clear;
video = videoinput('linuxvideo',1,'RGB24_320x240');
aviobj = VideoWriter("Videito.avi");
aviobj.FrameRate = 15;
video.TriggerRepeat = inf;
video.FrameGrabInterval = 2;

set(video, 'LoggingMode', 'memory');
open(aviobj)
start(video)
preview(video);
pause(2);
while (video.FramesAcquired < 150)
    I = getdata(video, 1);
    writeVideo(aviobj, I);
end
stop(video)
close(aviobj)
closepreview(video);

%% Visualizar fotitos
clc, clear;

figure(), hold on;
for i = 1:10
    I = imread("whisky"+i+".jpg");
    imshow(I);
    pause(1);
end

close all;








