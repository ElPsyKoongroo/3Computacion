%% Cargar datos
    clc, clearvars
    datos_path = "../02_Representar_Datos/Datos_Generados/";
    addpath(datos_path)
    load("conjunto_datos.mat", 'X', 'Y');

%%

% RGB de los datos color (sin la id de la imagen)
datos_color = double(X(logical(Y), 2:4));

media_colores = round(mean(datos_color));

[rows, cols] = size(datos_color)
distancias = zeros(rows, 1);

for i = 1:rows 
    distancia = round(sqrt((datos_color(i, 1).^2 + datos_color(i, 2).^2 + datos_color(i, 3).^2)));
    distancias(i) = distancia;
end

max_distancia = max(distancias);
min_distancia = min(distancias);
mean_distancia = mean(distancias);

%%
    
%%  
    rmpath(datos_path)