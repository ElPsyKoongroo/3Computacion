function [iguales] = funcion_compara_matrices(I1,I2)

if ~isequal( size(I1) , size(I2) )
    disp("Las matrices son de diferentes dimensiones");
    iguales = false;
    return ;
end

dif = I1 - I2;
[ minM, maxM ] = bounds(dif(:));

if maxM == minM && maxM == 0
    iguales = true;
else
    iguales = false;
end

end
