%% Cargar datos
clc, clearvars
datos_path = "../02_Representar_Datos/Datos_Generados/";
addpath(datos_path)
addpath("funciones/");
load("conjunto_datos_final.mat", 'X', 'Y');
% Esto es debido a que cuando hice el roipoly mis datos RGB
% tenian 4 columnas, la primera indicaba la imagen (1,2,3...)
% y las otras 3 el RGB. Por eso 'ignoro' la primera columna.
%  \
%   \------------+++
%                VVV
X = double (X(:, 2:4));
Y = logical(Y);

%% Calcular datos de esferas y separar agrupaciones
Groups = 4;
[idx, centroides] = funcion_kmeans(X(Y, :), Groups);

%% Calcular el punto mas alejado de cada esfera
%  Magic number; 3 (centro de la esfera) + 3 (radios)
%     \-----------------------+
%                             V
datos_esferas = zeros(Groups, 6);
for i = 1:Groups
    colores_grupo = X(idx == i, :);
    datos_esferas(i, :) = uint8(calcula_datos_esfera(colores_grupo, X(~Y, :)));
end


%% Generar las esferas
for i = 1:Groups
    radios = datos_esferas(i, 4:6);
    esferas = zeros(3, 6);
    [R,G,B] = sphere(100);
    Rc = datos_esferas(i, 1);
    Gc = datos_esferas(i, 2);
    Bc = datos_esferas(i, 3);
    plot3(X(logical(Y), 1), X(logical(Y), 2), X(logical(Y), 3), ".b"), hold on;
    plot3(X(logical(~Y), 1), X(logical(~Y), 2), X(logical(~Y), 3), ".r"), hold on;
    esferas(1,1) = Rc;
    esferas(1,2) = Gc;
    esferas(1,3) = Bc;
    for j = 1:1
        Radio = radios(1);
        x = Radio*R(:)+Rc; 
        y = Radio*G(:)+Gc; 
        z = Radio*B(:)+Bc;
        esferas(1, i+3) = Radio;
        representa_esfera(esferas(1, (1:3)), Radio, [rand rand rand]), hold on;
    end
end

%%
% save("datos_multiple_esferas", "datos_esferas");