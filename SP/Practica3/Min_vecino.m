function [min_vecino] = Min_vecino(I,x,y)
    %MIN_VECINO Summary of this function goes here
    %   Detailed explanation goes here
    numbers = I(x-1:x, y-1:y);
    numbers = numbers(numbers>0);
    
    min_vecino = min( numbers );
    %min_vecino = min_vecino-1;

end

