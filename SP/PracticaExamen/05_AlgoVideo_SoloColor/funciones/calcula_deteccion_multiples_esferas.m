function [I] = calcula_deteccion_multiples_esferas(I) 

    load("../04_Ajuste_Clasificador/ajustes.mat", ...
        "centro_esfera", ...
        "radio_esfera" ...
    );

    load("../03_Disegno_Clasificador/datos_multiple_esferas.mat", "datos_esferas");

    [Height, Width, ~] = size(I);

    % Size de la caja que se muestra en la imagen
    BLOCK_SIZE        =  3;
    BLOCK_COLOR       = [255, 0,0];
    
    % Obtener matrices binarias de color y de intensidad
    Ib_color = calcula_deteccion_1esfera_en_imagen(I, [centro_esfera, radio_esfera]);
    for esfera = 2:4
        centro_esfera = datos_esferas(esfera, 1:3);
        radio_esfera = datos_esferas(esfera, 4);
        Ib_color = or(Ib_color, calcula_deteccion_1esfera_en_imagen(I, [centro_esfera, radio_esfera]));
    end    

    % Etiquetar y quedarnos con las propiedades.
    [IEtiq, n] = bwlabel(Ib_color);
    properties = regionprops(IEtiq, "Centroid");

    % En caso de que no se haya detectado nada
    % siguiente frame.
    if n == 0
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