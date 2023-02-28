function [Io] = funcion_visualiza(Ii, Ib, Color, flagRepresenta)
%UNTITLED Summary of this function goes here
%   Detailed explanation goes here
    Io = Ii;
    [n_rows, n_cols] = size(Ib);
    
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