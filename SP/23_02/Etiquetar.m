function [IEtiq, n] = Etiquetar(Ib)
    [n_rows, n_col] = size(Ib);
    n = 1.0;

    % n_ones = sum(Ib(:)>0)

    IEtiq = zeros(n_rows, n_col);
    for x = 1:n_rows
        for y = 1:n_col
            if Ib(x,y) >= 1
                IEtiq(x,y) = n;
                n = n+1;
            end
        end
    end
    
    % Cambiar esta forma de añadir 0 por la que le 
    % gusta al teacher
    padded_array = padarray(IEtiq,[1 1],0,'both');

    cambiado = true;
    while cambiado 
        cambiado = false;

        % Pasada de arriba a abajo
        for x = 2:n_rows
            for y = 2:n_col
                if padded_array(x,y) ~= 0
                    % Funcion que se podria/ ¿Deberia? poner
                    % inline
                    min_vecino = Min_vecino(padded_array,x,y);

                    if min_vecino ~= padded_array(x,y)
                        cambiado = true;
                        padded_array(x,y) = min_vecino;
                    end
                end
            end
        end
        
        % Pasada de abajo a arriba
        for x = n_rows:-1:2
            for y = n_col:-1:2
                if padded_array(x,y) ~= 0

                    % min_vecino = Min_vecino(padded_array, y, x);
                    
                    numbers = padded_array(x:x+1, y:y+1);
                    numbers = numbers(numbers>0);
                    
                    min_vecino = min( numbers );


                    if min_vecino ~= padded_array(x,y)
                        cambiado = true;
                        padded_array(x,y) = min_vecino;
                    end
                end
            end
        end
    end


    IEtiq = padded_array(2:n_rows+1, 2:n_col+1);

end

