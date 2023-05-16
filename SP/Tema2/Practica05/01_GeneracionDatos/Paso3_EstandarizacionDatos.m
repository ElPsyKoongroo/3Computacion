clear all, clc

addpath("Funciones/");
load("DatosGenerados/conjunto_datos.mat"); 
load("DatosGenerados/nombres_problema.mat")


medias = mean(X);
desviaciones = std(X);

%Cambiar Euler
desviaciones(23) = eps;

[rows, num_descriptores] = size(X);

%% Estandarizacion de X

Z = zeros(size(X));
for i = 1:(num_descriptores-1)
    mu_i = medias(i);
    sigma_i = desviaciones(i);
    Z(:, i) = (X(:, i) - mu_i) / sigma_i;
end
Z(:, num_descriptores) = X(:, num_descriptores);
dim = 3;
[espacio_ccas, j_espacio_ccas] = funcion_selecciona_vector_ccas(Z, Y, dim);

%funcion_representa_datos(Z,Y,espacio_ccas, nombresProblema);
save("DatosGenerados/conjunto_datos_estandarizados.mat", "Z", "Y");
save("DatosGenerados/datos_estandarizacion.mat", "medias", "desviaciones");
save("DatosGenerados/mejor_espacio_ccas.mat", "espacio_ccas", "j_espacio_ccas");

rmpath("Funciones/");