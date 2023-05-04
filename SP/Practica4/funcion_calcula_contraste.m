function C = funcion_calcula_contraste(I)
    B = funcion_calcula_brillo(I);
    [m,n,d] = size(I);
    if d == 2
        C = sqrt((1/(m*n)) * sum(sum(double(I-B).^2)));
    else
        C = sqrt((1/(m*n)) * sum(sum(sum(double(I-B).^2))));
    end
end

