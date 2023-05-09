%% Load data Y constantes
clearvars, close all;

SIN_RUIDO = 4;
MEDIA = 5;
MAX_ALCANCE = 6;

esferas_path = "../03_Disegno_Clasificador/datos_multiple_esferas";
load(esferas_path, "datos_esferas");
addpath("funciones")

%% Carga los datos de las esferas
esfera = 3;
centro_esfera = datos_esferas(esfera, 1:3);
radio_esfera = datos_esferas(esfera, MEDIA); 

save("ajustes", "radio_esfera","centro_esfera");

%% Guardar el video en una array
close all;
video = VideoReader("videito.avi");
nFrames = video.NumFrames;
factor = 1;
Width = video.Width * factor;
Height = video.Height * factor; 
Frames = uint8(zeros(Height, Width, 3, nFrames));
i = 0;
while hasFrame(video)
    I_Original = readFrame(video);
    I = uint8 (imresize(I_Original, factor));
    Frames(:,:,:,i+1) = I;
    i = i+1;
end
close all;

%% Aqui empieza la magia
% Detectar los objetos que pertenezcan al color deseado
% y ademas que tengan una diferencia de intensidad.
i = 1;

UMBRAL_AREA       = 10*factor;
UMBRAL_INTENSIDAD = 2;

% Size de la caja que se muestra en la imagen
BLOCK_SIZE        =  3;  
BLOCK_COLOR       = [255,0,0];
% Cantidad de elementos que tiene que haber en la interseccion
INTERSECT_LEN     = BLOCK_SIZE*2+1;

ACIERTO = 0;

figure(), hold on;
I_old = uint8(Frames(:,:,:,i));
for i = 2:nFrames-1
    I = uint8(Frames(:,:,:,i+1));
    
    % Obtener matrices binarias de color y de intensidad
    Ib_color = calcula_deteccion_1esfera_en_imagen(I, [centro_esfera, radio_esfera]);
    for esfera = 2:4
        centro_esfera = datos_esferas(esfera, 1:3);
        radio_esfera = datos_esferas(esfera, MAX_ALCANCE);
        temp = calcula_deteccion_1esfera_en_imagen(I, [centro_esfera, radio_esfera]);
        Ib_color = or(Ib_color, temp);
    end
    
    Ib_intensidad = logical(imabsdiff(rgb2gray(I_old), rgb2gray(I)) > UMBRAL_INTENSIDAD);

    % Aplicar 'and' logica entre las dos para quedarnos solo
    % con los pixeles que cumplan en el color y tambien
    % cumplan con el UMBRAL_INTENSIDAD
    Ib = and(Ib_intensidad, Ib_color);

    % Etiquetar y quedarnos con las propiedades.
    [IEtiq, n] = bwlabel(Ib);
    properties = regionprops(IEtiq);
    areas = cat(1,properties.Area);
    [~, pos] = sort(areas, 'descend');
    [pos_len, ~] = size(pos);

    % En caso de que no se haya detectado nada
    % siguiente frame.
    if pos_len == 0
        imshow(Ib);
        continue
    end

    Ib = and(Ib, IbEtiqs);

    % Si no ha detectado ningun objeto entonces
    % todo false para que no muestre nada por pantalla.
    if sum(sum(Ib)) == 0
        Ib = false(Height, Width);
    end

    centroides = cat(1,properties.Centroid);

    % Para visualizar todos los objetos detectados
    % cambiar el rango del bucle de 1:1 a 1:p
    for c = 1:1
        x = int32(centroides(c, 1));
        y = int32(centroides(c, 2));

        % Bounds checking, con esto comprobamos que
        % la caja que dibujamos este dentro de la pantalla.
        [~, width_inter] = size(intersect(x-BLOCK_SIZE:x+BLOCK_SIZE, 1:Width));
        [~, height_inter] = size(intersect(y-BLOCK_SIZE:y+BLOCK_SIZE, 1:Height));
        if  width_inter ~= INTERSECT_LEN  || height_inter ~= INTERSECT_LEN
            imshow(I);
            continue
        end

        % Si la caja esta dentro de la imagen entonces dibujarla
        for componente = 1:3
            I(y-BLOCK_SIZE:y+BLOCK_SIZE, x-BLOCK_SIZE:x+BLOCK_SIZE, componente) = BLOCK_COLOR(componente);
        end
    end
    
    ACIERTO = ACIERTO + 1;
    imshow(Ib); 
    I_old = I;
end

fprintf("%% de acierto: %f%%\n", ACIERTO*100/nFrames)
close all;