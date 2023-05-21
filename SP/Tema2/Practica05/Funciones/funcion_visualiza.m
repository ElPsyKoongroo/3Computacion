function [Io] = funcion_visualiza(Ii, Ib, Color, flagRepresenta)
    % Comprobar que Ii es de intensidad o RGB mirando las dimensiones
    % y despues construir la imagen
    [~, ~, dim] = size(Ii);
    if dim == 2
        Io = repmat(Ii, 1, 1, 3);
    else 
        Io = Ii;
    end

    %Io = Ii;
    [n_rows, n_cols] = size(Ib);

    if nargin == 3
        flagRepresenta = false;
    end
    
    % Is this really needed ? 
    R = zeros(n_rows, n_cols);
    G = zeros(n_rows, n_cols);
    B = zeros(n_rows, n_cols);


    R(Ib) = uint8(Color(1));
    G(Ib) = uint8(Color(2));
    B(Ib) = uint8(Color(3));

    Io = double(Io) + cat(3,R,G,B);    

    if flagRepresenta
        imshow(uint8(Io));
    end
end