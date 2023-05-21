function [d1, d2, d12, coeficientes_d12] = funcion_calcula_hiperplanoLDA_2Clases_2_3_Dim(X, Y)


    x1 = sym("x1", "real");
    x2 = sym("x2", "real");

    Xsym = [x1; x2];

    num_attr = size(X,2);

    if num_attr == 3
        x3 = sym("x3", "real");
        Xsym = [Xsym; x3];
    end

    [medias, m_cov, prob_priori] = funcion_ajusta_LDA(X, Y);

%     m_cov = ((num_datos_clase1-1)*m_cov1 + ((num_datos_clase2-1)*m_cov2 / (num_datos_clase1+num_datos_clase1-2));
% 
%     clases = unique(YoI);
%     for i = 1:length(clases)
%     end
    

    M1 = medias(1,:)';
    pi1 = prob_priori(1);

    M2 = medias(2,:)';
    pi2 = prob_priori(2);


    d1 = expand( -0.5*(Xsym-M1)' * inv(m_cov)*(Xsym-M1) + log(pi1));
    d2 = expand( -0.5*(Xsym-M2)' * inv(m_cov)*(Xsym-M2) + log(pi2));
    

	
	
	d12 = d1-d2;
    
	if num_attr == 2
		x1 = 0; x2 = 0; C = eval(d12);
		x1 = 1; x2 = 0; A = eval(d12);
		x1 = 0; x2 = 1; B = eval(d12);
		coeficientes_d12 = [A, B, C];
	else
		x1 = 0; x2 = 0; x3 = 0; D = eval(d12);
		x1 = 0; x2 = 0; x3 = 1; C = eval(d12);
		x1 = 0; x2 = 1; x3 = 0; B = eval(d12);
		x1 = 1; x2 = 0; x3 = 0; A = eval(d12);		
		coeficientes_d12 = [A, B, C, D];
	end

end

