function [I] = calcula_deteccion_multiples_esferas(I, I_old, datos_esferas) 

    
    
    UMBRAL_INTENSIDAD = 2;
    [Height, Width, ~] = size(I);

    % Size de la caja que se muestra en la imagen
    BLOCK_SIZE        =  3;
    BLOCK_COLOR       = [255, 0,0];
    
    % Obtener matrices binarias de color y de intensidad
    Ibs = false(Height, Width, 1, 4);
    for esfera = 1:2
        centro_esfera = datos_esferas(esfera, 1:3);
        radio_esfera = datos_esferas(esfera, 4);
        Ibs(:,:,:,esfera) = funcion_calcula_distancia(I, centro_esfera, radio_esfera);
    end    

    Ib = false;

    for i = 1:4
        Ib = Ib | Ibs(i);
    end

    % Ib_intensidad = logical(imabsdiff(rgb2gray(I_old), rgb2gray(I)) > UMBRAL_INTENSIDAD);

    % Aplicar 'and' logica entre las dos para quedarnos solo
    % con los pixeles que cumplan en el color y tambien
    % cumplan con el UMBRAL_INTENSIDAD
    % Ib = and(Ib_intensidad, Ib_color);

    % Etiquetar y quedarnos con las propiedades.
    [IEtiq, n] = bwlabel(Ib);
    properties = regionprops(IEtiq);
    areas = cat(1,properties.Area);
    [~, pos] = sort(areas, 'descend');
    [pos_len, ~] = size(pos);

    % En caso de que no se haya detectado nada
    % siguiente frame.
    if pos_len == 0
        return
    end

    centroides = cat(1,properties.Centroid);
    [c_rows, ~] = size(centroides);
    
    % Para visualizar todos los objetos detectados
    % cambiar el rango del bucle de 1:1 a 1:p
    for c = 1:c_rows
        x = int32(centroides(c, 1));
        y = int32(centroides(c, 2));

        % Bounds checking, con esto comprobamos que
        % la caja que dibujamos este dentro de la pantalla.
        x_range = x-BLOCK_SIZE:x+BLOCK_SIZE;
        y_range = y-BLOCK_SIZE:y+BLOCK_SIZE;
        if  ~ (all(y_range > 0) && all(y_range < Height))
            continue
        end
        if  ~(all(x_range > 0) && all(x_range < Width))
            continue
        end

        % Si la caja esta dentro de la imagen entonces dibujarla

        for componente = 1:3
            I(y_range, x_range, componente) = BLOCK_COLOR(componente);
        end
    end
end