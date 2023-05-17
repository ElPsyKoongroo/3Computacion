%{


03_FaseTest_CASO1_LDA_clases2a2/AplicacionClasificadorLDA.m:

	1. Añadir paths
	2. Leer imagenes de test
	3. Obtener umbral:
		
		umbral = graythresh(I);
		Ibin = I < 255*ubreal;
		
	4. Eliminar regiones ruidosas
	5. Etiquetar
	6. Calcular descriptores de la imagen (funcion_calcula_descriptores_imagen)
	
	

	Cambiar el nombre a los datos de entrenamiento:
		d12CircTrian = d12;
		coeficientesCircTrian = coeficientes_d12;
		espacioCcasCircTrian = ...;
		...
		...
		
		
	Guardar el nombre de los problemas especificos:
	
		
	   +----------------------+
	   |nombresCircCuad.clases| <-- Para saber a que clase pertenece
	   +----------------------+
	   
	   
	Por cada objeto aplicar las funciones de decision de clada clase
	^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
	                   |
	valor_d12CircCuad  = eval(d12CircCua);
	valor_d12CircTrian = eval(d12CircTrian);
	valor_d12CuadTrian = eval(d12CuadTrian);

	if valor_d12CircCuad > 0 & valor_d12CircTrian > 0
		clase = nombresCircCuad.clase(1);
		reconocimiento = ["Circulo"];
	else if valor_d12CircCuad < 0 & valor_d12CuadTrian > 0
		clase = nombresCuadTrian.clase(1);
		reconocimiento = ["Cuadrado"];
	else if valor_d12CircTrian < 0 & valor_d12CuadTrian < 0
		clase = nombresCuadTrian.clases(2);
		reconocimiento = ["Triangulo"];
	else
		No es ningun tipo de objeto;
	endif

   +------------------------------------------------+
   |Usar la funcion_visualiza para mostrar el objeto|
   |coloreado por pantalla                          |
   +------------------------------------------------+

	% Descriptores que le salen al tito Diego:
		Circulo Triangulo:
		DF4, Hu3, Solidez
		
		Circulo Cuadrado:
		DF5, DF7, Hu1
		
		Cuadrado Triangulo:
		DF6, DF7, DF4
   
   Ib == IEtiq == i;
   figure,
   subplot(2,2,1), funcion_visualiza(I, Ib, color),
   title(reconocimiento);
   
   subplot(2,2,2)
   X = XTrainCircCuad; Y = YTrainCircCuad;
   nombres = nombresCircCuad; coeficientes = coeficientesCircCuad;
   funcion_representa_muestras_clasificacion_binaria_con_frontera(X, Y, nombres, coeficientes);
   XiOI = Xi(1,espacioCcasCircCuad);
   hold on, plot3(XiOI(1),XiOI(2),XiOI(3),"ok");
   
03_FaseTest_CASO2_QDA_3Clases/AplicacionClasificadorQDA.m:

	% QDA Ccas 2,16,17,18
	% probabilidadPriori 0.3333,0.3333,0.3333

	
	% Cargar la informacion y renombrarla 
	espacioCcasCircCuadTrian = espacioCcas;
	XTrainCircTrian  		 = XoI;
	YTrianCircCuadTrian 	 = YoI;
	nombresCircCuadTrian  	 = nombresProblemaOI;
	
	
	for i = 1:numObjetos
		
		YiOI = YP(i)
		
		posClasesOI = find(ismember(codifClases,YiOI));
					       ^^^^^^^^^^^^^^^^^^^^^^^^^^^
						   Devuelve un vector binario con 1 en 
						    las posiciones que sean iguales que el valor de YiOI.
							
							
							codifClases = [1, 2, 3]; YiOI = 3;
							^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
										[0, 0, 1];
		
		
		clasesOI = nombresCircCuadTrian.clases{posClasesOI};
		reconocimiento = ['Reconociiento Objeto:' clasesOI];
		
		
	
03_FaseTest_CASO3_knn_3claes/AplicacionClasificador.m:
	espacioCcas knn 5 dimensiones: 2,16,17,18,20;
	
	Cargar datos y renombrar para no reventarlos
	
	
02_FaseEntrenamiento_CASO4_knn_LDA:	
	LDA: 3,8,16
	
	
03_FaseTest_CASO4_knn_LDA/AplicacionClasificador.m:
	
	%% EVALUAR EN DOS ETAPAS
	%% 1.- knn PARA SABER SI EL OBJETO ES CIRCULO-TRIANGULO O CUADRADO
	%% 2.- MDM PARA, EN CASO DE SER CIRCULO-TRIANGULO
	%%
	
	
	
	if posClaseOI == 2 % Cuadrado
		claseOI = nombresCircTrian_Cuad.clases{posClasesOI};
		reconocimiento = ['Reconociiento Objeto:' claseOI];
		color = [0, 255, 0];
	else
		
		% Apliar aqui clasificador Circulo Triangulo
	
	
		XoIi = XImageneS(i, espacioCcasCircTrian);
		x1 = XoIi(1); x2 = XoIi(2); x3 = XoIi(3);
		valor_d12CircTrian = eval(d12CircTrian);
	
		if valor_d12CircTrian > 0
			% como antes
		else
			% como antes
		end
	
	end
	
	+------------------------------------+
	| representar como en LDA clases 2a2 |
	+------------------------------------+ 
======================================================================

    +------------------------------------------------+
    | Para saber el numero de argumentos             |
    |   que le has pasado a una funcion añadir       |  
    |     como ultimo argumento `varargin`.          |
	+------------------+-----------------------------+
                       |
					   |
	   +---------------^--+
	   | if varargin == 2 |  <---- 2 argumentos
	   +------------------+
%}

