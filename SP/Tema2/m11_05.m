%{

    Hacer '01_GeneracionDatos'.


 +------------------------------------------------+
 |   [num_muestras, num_descriptores] = size(X)   |
 +------------------------------------------------+  
                         \________
                                  \
         Obtener el numero de instancias/muestras
              y el numero de descriptores/propiedades.


  title('String')¯
  ^^^^^^^^^^^^^^^
        \______
               \
          Poner en la figure lo que ponga
            en la String.


 combnk(rango, numero_elementos_por_fila)
 ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯\/¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯
     Obtener todas las posibles combinaciones de N elemetos
       del rango.
                       

Ejemplo:
 
    combinaciones = combnk(1:num_descriptores, 3);
                                            
    [rows, cols] = size(combinaciones);
    
    separabilidades = zeros(rows, 1);

    for i = 1:rows
        XoI = X(:, combinaciones(i,:));
        separabilidad = indicesJ(XoI, Y);
        separabilidades(i) = separabilidad;
    end

    [sep_ordenada, indices] = sort(separabilidades, 'descend');
                   
===========================================================================

 Quedarnos con los descriptores con separabilidad individual mayor
 a 0.05;

    
    mejores_desc = find(valoresJ > 0.05);
    
    combinaciones = combnk(mejores_desc, 3);

    // Calcular como antes

===========================================================================

    
 [espacio_ccas, sep_espacio_ccas] = funcion_selecciona_vector_ccs(X, Y, dim)
                                    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯/¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯
                                                      /
  ___________________________________________________/_______
 | Calcula la combinacion de 'dim' caracteristicas que mayor |
 |  separabilidad tienen entre ellas.                        |
  ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯  

===========================================================================

    Circulos VS Cuadrados

    XoI, YoI;
    
    codif_clases            = unique(Y);
    pos_clases_oi           = [1 2];
    valores_codif_clases_oi = codifClases(posClasesOI);

    ismember(codif_clases, valores_codif_clases_oi); ------+
                                                           |
 +----------------------------------------------------+    |
 |  Devuelve un vector columna binario donde los 1    |    |
 |   indican los que si pertenecen y los 0 los que no |<---+   
 +----------------------------------------------------+


 find(not(ismember(codif_clases, valores_codif_clase_oi)));
 ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
       |
       V
     Posiciones de la codificacion de las clases 
     que no son de interes.


===========================================================================    


    XoI = X(:, 1:22);
    YoI = Y(:);

    XoI(Y == valores_codif_clases_no_oi, :) = [];
    YoI(Y == valores_codif_clases_no_oi)    = [];
    

    Eliminar mas de una clase con un for que vaya
    eliminando cada columna individualmente.


    dim = 3;

    [espacio_ccas, j_espacio_ccas] = funcion_selecciona_vector_ccas(XoI,
    YoI, dim);

    // Crear una struct 'nombres_problemas_oi' que sea una 
    // copia de 'nombres_problema' pero con solo los elementos
    // del 'espacio_ccas'/'pos_clase_oi'.


    [rows, cols] = size(XoI)
    funcion_representa_datos(XoI, YoI, 1:cols, nombresProblemasOI);


===========================================================================


    





%} 


