function [YLDA, d] = funcion_aplica_LDA(X, vector_medias, matriz_covarianza, probabilidad_priori, valores_clases)
    

    [num_muestras, num_descriptores] = size(X);
    YLDA = zeros(num_muestras, 1);
    d = zeros(num_muestras, 1);
    num_clases= length(valores_clases);
    
    % Declaracion de p variables simbolicas
    Xsym = sym('Xsym', [num_descriptores 1]);
    
    % Vector auxiliar para obtener el valor de decision de todas las clases
    eval_clases = zeros(num_clases, 1);
    for i = 1:num_muestras
        
        % Cargamos la muestra en Xsym
        for compo = 1:num_descriptores
            Xsym(compo) = X(i, compo);
        end
        
        % Obtenemos las funciones de decision de cada clase
        dClases = [];
        for clase = 1:num_clases
            Mi = vector_medias(clase, :)';
            Probai = probabilidad_priori(clase);
            dClases = [dClases; expand(-0.5*(Xsym - Mi)' * inv(matriz_covarianza)...
                * (Xsym - Mi) + log(Probai) )];
            eval_clases(clase) = eval(dClases(clase));
        end
    
        [eval_ordenado, idxs] = sort(eval_clases, 'descend');
        YLDA(i) = valores_clases(idxs(1));
        d(i) =  eval_ordenado(1);
    end

