function [y_qda, d] = funcion_aplica_QDA(X, vectorMedias, ...
    matricesCovarianzas, probabilidadPriori, valores_clases)

    [num_muestras, num_descriptores] = size(X);
    y_qda = zeros(num_muestras, 1);
    num_clases = length(valores_clases);
    d = zeros(num_muestras, 1);

    Xsym = sym('Xsym', [num_descriptores, 1]);

    eval_clases = zeros(num_clases, 1);

    for i = 1:num_muestras
        for compo = 1:num_descriptores
            Xsym(compo) = X(i, compo);
        end

        decision_clases = [];
        for clase = 1:num_clases
            Mi = vectorMedias(clase, :)';
            MCovi = matricesCovarianzas(:, :, clase);
            Probai = probabilidadPriori(clase);
            decision_clases = [decision_clases; expand(-0.5*(Xsym - Mi)' * inv(MCovi) * (Xsym - Mi) -0.5*log(det(MCovi))...
            + log(Probai) )];
    
            eval_clases(clase) = eval(decision_clases(clase));
        end

        [eval_clases_ord, idxs] = sort(eval_clases, 'descend');
        y_qda(i) = valores_clases(idxs(1));
        d(i) = eval_clases_ord(1);
    end

end

