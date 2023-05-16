%{



[d1, d2, d12, coef_d12] = ...
              ^^^^^^^^ 
                  |
           Lenguaje simbolico

+---------------------------+
|2.1 Diseño del clasificador|
+---------------------------+
|
|--> 02_FaseEntrenamiento_CASO1_LDA_clases2a2
|     |-->   01_CirculoCuadrado
|     |         |--> DatosGenerados
|	  |	     	|       |--> espacio__ccas_circ_cuad.mat
|	  |	     	|       +--> LDA_cur_cuad.mat
|	  |	     	|
|	  |	     	|--> Funciones
|	  |	     	|--> Paso1_SeleccionDescriptores.m
|	  |	     	+--> Paso2_clasificadorLDA_2clases.m
|	  |	     	
|	  |      		
|     |-->  02_CirculoTriangulo
|     +-->  03_CuadradoTriangulo
|
|--> 02_FaseEntrenamiento_CASO2_QDA_3clases
|     |
|     |--> DatosGenerados
|     |--> Funciones
|     |
|     |--> Paso1_SeleccionDescriptores.m
|     +--> Paso2_clasificadorQDA_3clases.m
|     
|
|--> 02_FaseEntrenamiento_CASO3_knn_3clases
|     |
|     |--> DatosGenerados
|     |--> Funciones
|	  |
|     +--> Paso1_2_SeleccionDescriptores_GeneracionDatosClasificador.m
|
|--> 02_FaseEntrenamiento_CASO4_knn_LDA
|     |
|     |
|     |--> 01_knn_CirculosTringulos_Cuadrados
|     |     |
|     |     |--> DatosGenerados
|     |     |--> Funciones
|     |     +--> Paso1_2_SeleccionDescriptores_GeneracionDatosClasificador.m
|     |     
|     |
|     | 
|     +--> 02_LDA_Circulos_Triangulos
|
|--> 03_FaseTest_CASO1_LDA_clases2a2
|--> 03_FaseTest_CASO2_QDA_3clases
|--> 03_FaseTest_CASO3_knn_3clases
+--> 03_FaseTest_CASO4_knn_LDA

    

Paso1_SeleccionDescriptores.m:

	Cargar datos estandarizados (Z)
	Workspace -> [nombresProblema, Z, Y]
	clases_oi = [1 2];
	codif_clases = unique(Y);
	
	num_descriptores = size(X,2);
	CoI = X(filas_oi, 1:num_descriptores-1);
						^^^^^^^^^^^^^^^^^^
								|
							No contamos Euler
							
	YoI = Y(filas_oi)
	
	dim = 3;
	[espacio_ccas, j_espacio_ccas] = funcion_seleccion_vectar_ccas(XoI, YoI, dim);
	
	XoI = XoI(:, espacio_ccas);
	
	nombres_problema_oi 			 = [];
	nombres_problema_oi.descriptores = nombres_problema.descriptores(espacio_ccas);
	nombres_problema_oi.clases 		 = nombres_problema.clases(clases_oi);
	nombres_problema_oi.simboles     = nombres_problema.simbolos(clases_oi);
	
	funcion_representa_datos(XoI, YoI, 1:dim, nombres_problema_oi);
	
	save("ruta");
	rmpath("ruta);
	
=============================================================================================

Paso2_clasificadorLDA_2clases.m

	Cargar datos del paso1 [	
		espacio_ccas,
		nombres_problema_oi,
		XoI,
		YoI
	];
	
	
	[d1, d2, d12, coeficientes_d12] = funcion_calcula_hiperplanoLDA_2_clases_2_3_dim(XoI, YoI);

funcion_calcula_hiperplanoLDA_2_clases_2_3_dim

	datos = X;
	x1 = sym('x1', 'real');
	x2 = sym('x2', 'real');
	
	X = [x1, x2];
	
	num_atributos = size(datos,2)
	
	if num_atributos == 3
		x3 = sym('x3', 'real');
		X = [X; x3];
	end
	
	valores_clases = unique(Y);
	num_clases = 2;
	
	M = zeros(num_clases, num_atributos);
	m_cov = zeros(num_atributos, num_clases);
	p = zeros(num_clases,1);
	
	m_cov1 = m_cov(:,:,1);
	m_cov2 = m_cov(:,:,2);
	
	m_cov = ((num_datos_clase1-1)*m_cov1 + ((num_datos_clase2-1)*m_cov2 / (num_datos_clase1+num_datos_clase1-2));
	
	M1 = M(1,:)';
	d1 = expand( -0.5*(X-M1)' * pinv(m_cov)*(X-M1) + log(p(1)));
	
	M2 = M(2,:)';
	d2 = expand( -0.5*(X-M2)' * pinv(m_cov)*(X-M2) + log(p(2)));
	
	
	if num_atributos == 2
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
	
	d12 = d1-d2;
	
	+------------------------------------+
	| representar | A*x1+B*x2+C*x3+D = 0 |
	+------------------------------------+
		
========================================================================================================

02_FaseEntrenamiento_CASO2_QDA_3clases/Paso1_SeleccionDescriptores.m


	dim = 4;
	[espacio_ccas, j_espacio_ccas] = funcion_seleccion_vectar_ccas(XoI, YoI, dim);
	
	
	XoI = XoI(:, espacio_ccas);
	
	nombres_problema_oi = [];
	nombres_problema_oi.descriptores = nombres_problema.descriptores(espacio_ccas);
	nombres_problema_oi.clases = nombres_problema.clases;
	nombres_problema_oi.simbolos = nombres_problema.simbolos;
	
	
	funcion_representa_datos(XoI, YoI, [1,3,4], nombres_problema_oi);
	
	YoI = "a saber..."
	
02_FaseEntrenamiento_CASO2_QDA_3clases/Paso2_clasificadorLDA_2clases.m



	[vector_medias, matrices_covarianzas, probabilidades_priori] = funcion_ajusta_QDA(XoI, YoI);
	
		
	save("QDA_circ_cuad_trian", "espacio_ccas", 
		"XoI", "YoI", "nombres_problema_oi", 
		"vector_medias", "matrices_covarianzas", 
		"probabilidades_priori");

		
02_FaseEntrenamiento_CASO4_knn_LDA/Paso1_2_SeleccionDescriptores_GeneracionDatosClasificador.m

	Cargar datos [
		nombres_problema,
		X,
		Y
	];

	
	clases = [1, 3];
	filas_oi = false(num_datos, 1);
	
	%Clase 1
	for i = 1:length(clases)
		filas_oi = or(filas_oi, Y==clases(i));
	end
	filas_oi_c1 = filas_oi;
	
	%Clase 2
	clases = [2];
	filas_oi = false(num_datos, 1);
	for i = 1:length(clases)
		filas_oi = or(filas_oi, Y==clases(i));
	end
	filas_oi_c2 = filas_oi;
	filas_oi_c1 = filas_oi;
	
	% Recodificar
	XoI = X(:, 1:num_descriptores-1);
	YoI = Y;
	YoI(filas_oi_c1, :) = nombres_problema.clases(1);
	

%}