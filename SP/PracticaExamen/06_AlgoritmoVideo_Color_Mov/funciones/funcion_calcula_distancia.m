function [Ib]= funcion_calcula_distancia(I, centro_esfera, radio_esfera)
    
    [rows, cols, ~] = size(I);
    I2 = double(I);
    radio_esfera = radio_esfera.^2;

    distancias = size(rows, cols);

    centros = double(centro_esfera);
    
    for row = 1:rows
        for col = 1:cols
            punto = squeeze(I2(row, col, :))';
            distancias(row, col) = sum((centros-punto).^2);
        end
    end

    Ib = distancias < radio_esfera;
end

