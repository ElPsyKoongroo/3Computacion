
%%
i = 1;

UMBRAL_CONECTIVIDAD  = 30;
UMBRAL_INTENSIDAD    = 20;

% Size de la caja que se muestra en la imagen
BLOCK_SIZE        =  3;  

% Cantidad de elementos que tiene que haber en la interseccion
INTERSECT_LEN     = BLOCK_SIZE*2+1;

ACIERTO = 0;

figure(), hold on;
I_old = uint8(Frames(:,:,:,i));
for i = 2:nFrames-1
    I = uint8(Frames(:,:,:,i+1));
    
    % Obtener matrices binarias de color y de intensidad
    Ib_color = calcula_deteccion_1esfera_en_imagen(I, [centro_esfera, radio_esfera]);
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
        imshow(I);
        continue
    end

    % Nos quedamos con el/los objeto/s que cumplan
    % com el UMBRAL_CONECTIVIDAD
    p = 1;
    while areas(pos(p)) > UMBRAL_CONECTIVIDAD && p < pos_len
        Ib = and(Ib, IEtiq == pos(p));
        p = p+1;
    end
    
    % Si no ha detectado ningun objeto entonces
    % todo false para que no muestre nada por pantalla.
    if sum(sum(Ib)) == 0
        Ib = false(Height, Width);
        imshow(I);
    end

    % Esto era para mostrar una imagen negra 
    % excepto los pixeles del objeto en azul
     R = I(:,:,1);
     G = I(:,:,2);
     B = I(:,:,3);
     R(~Ib) = 0;
     G(~Ib) = 0;
     B(~Ib) = 0;
     compose = uint8(cat(3,R,G,B));

    centroides = cat(1,properties.Centroid);
    colores = [255, 0,0];


    
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
            I(y-BLOCK_SIZE:y+BLOCK_SIZE, x-BLOCK_SIZE:x+BLOCK_SIZE, componente) = colores(componente);
        end
    end
    
    ACIERTO = ACIERTO + 1;
    imshow(I); 
    I_old = I;
end

fprintf("%% de acierto: %f%%\n", ACIERTO*100/nFrames)
close all;