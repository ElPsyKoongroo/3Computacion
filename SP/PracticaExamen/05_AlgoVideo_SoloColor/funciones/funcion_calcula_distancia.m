function [Ib]= funcion_calcula_distancia(I, centro_esfera, radio_esfera)
    
    [rows, cols, ~] = size(I);
    
    % Ib = false(rows, cols);
    radio_esfera = radio_esfera.^2;

    % Parece que aplicando una lambda de esta forma es mas eficiente que
    % haciendo el bucle for.
    distancias = arrayfun(@(r,g,b) sum((centro_esfera-double([r,g,b])).^2), I(:,:,1),I(:,:,2),I(:,:,3));

    %distancias = I(:,:,1),I(:,:,2),I(:,:,3)

    Ib = distancias < radio_esfera;

    
    %%
%     for row = 1:rows-1
%         for col = 1:cols-1
%             punto = double(reshape(I(row, col, :), 1,3));
%             distancia = sum(((centro_esfera-punto).^2));
%             if distancia < radio_esfera
%                 Ib(row, col) = true;
%             end
%         end
%     end
end

