function vector_salida = calcula_datos_esfera(XColor, XFondo)
    
    %% Obtener las distancias de los colores desde 0,0
    X = double([XColor; XFondo]);
    Y = logical([ones(size(XColor,1),1); zeros(size(XFondo, 1),1) ]);
    
    [rows, ~] = size(X);
    distancias = zeros(rows, 1);
    
    for i = 1:rows 
        distancias(i) = round(sqrt((X(i, 1).^2 + X(i, 2).^2 + X(i, 3).^2)));
      
    end
    
    %% Media de los colores de interes

    media_colores = double(round(mean(X(Y, :))));


    distancias_color = distancias(logical(Y));
    distancias_fondo = distancias(~Y);
    distancia_media = mean(distancias(logical(Y)));

    %% Sin ruido
    sin_ruido = 0;
    [color_rows, ~] = size(distancias_color);
    distancias_color = distancias(Y, :);
    for i = 1:color_rows 
        distancia_aux =  abs(distancia_media-distancias_color(i));
        if ( distancia_aux > sin_ruido ) 
            sin_ruido = distancia_aux;
        end
    end

    %% Max pixeles
    [fondo_rows, ~] = size(distancias_fondo);
    colores_fondo = double(X(logical(~Y), :));
    
    distancias_fondo = zeros(fondo_rows, 1); 
    for i = 1:fondo_rows
        distancia_act = double(colores_fondo(i, :));
        distancia_aux = sqrt(sum((media_colores-distancia_act).^2));
        distancias_fondo(i) = distancia_aux;
    end
    max_pixeles = min(distancias_fondo);
    
    %% Sin compromiso
    sin_compromiso = (max_pixeles+sin_ruido)/2;

    %%
    vector_salida = zeros(1,6);
    vector_salida(1) = media_colores(1);
    vector_salida(2) = media_colores(2);
    vector_salida(3) = media_colores(3);
    vector_salida(4) = sin_ruido;
    vector_salida(5) = sin_compromiso;
    vector_salida(6) = max_pixeles;

end

