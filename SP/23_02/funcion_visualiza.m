function [Io] = funcion_visualiza(Ii, Ib, Color, flagRepresenta)
    % Comprobar que Ii es de intensidad o RGB mirando las dimensiones
    % y despues construir la imagen
    Io = Ii;
    [n_rows, n_cols] = size(Ib);

    if nargin == 3
        flagRepresenta = false;
    end
    
    % Is this really needed ? 
    R = zeros(n_rows, n_cols);
    G = zeros(n_rows, n_cols);
    B = zeros(n_rows, n_cols);


    R(Ib) = Color(1);
    G(Ib) = Color(2);
    B(Ib) = Color(3);

    Io = Io + cat(3,R,G,B);    

    if flagRepresenta
        imshow(uint8(Io));
    end
end