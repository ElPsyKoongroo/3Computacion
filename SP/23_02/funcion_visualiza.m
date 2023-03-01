function [Io] = funcion_visualiza(Ii, Ib, Color, flagRepresenta)
%UNTITLED Summary of this function goes here
%   Detailed explanation goes here

% Comprobar que Ii es de intensidad o RGB mirando las dimensiones
% y despues construir la imagen
    Io = Ii;
    [n_rows, n_cols] = size(Ib);

    if nargin == 3
        flagRepresenta = false;
    end
    
    %{
    R(Ib) = Color(1);
    G(Ib) = Color(2);
    B(Ib) = Color(3);

    Io = cat(3,(R,G,B));
    %}
    
    
    for x = 1:n_rows
        for y = 1:n_cols
            if Ib(x,y) == 1
                Io(x,y,1) = Color(1);
                Io(x,y,2) = Color(2);
                Io(x,y,3) = Color(3);
            end
        end
    end
    

    if flagRepresenta
        imshow(uint8(Io));
    end
end