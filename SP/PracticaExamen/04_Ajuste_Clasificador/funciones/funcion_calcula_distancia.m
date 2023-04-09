function [Ib, distancias]= funcion_calcula_distancia(I, centro_esfera, radio_esfera)
    
    [rows, cols, ~] = size(I);
    
    Ib = false(rows, cols);
    distancias = zeros(rows, cols);

    for row = 1:rows-1
        for col = 1:cols-1
            punto = double(reshape((I(row, col, :)), [1 3]));
            distancia = sqrt(sum(((centro_esfera-punto).^2)));
            distancias(row, col) = distancia;
            if distancia < radio_esfera
                Ib(row, col) = true;
            end
        end
    end
end

