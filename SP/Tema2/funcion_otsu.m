function [outputArg1,outputArg2] = funcion_otsu(inputArg1,inputArg2)
I = imread('imagen.jpg');
Igray = rgb2gray(I);

% Calcular el histograma de la imagen
[counts, ~] = imhist(Igray);

% Normalizar el histograma
p = counts / sum(counts);

% Inicializar variables
var_max = 0;
thres = 0;

% Iterar sobre todos los valores de umbral posibles
for k = 1:length(p)
    % Dividir el histograma en dos partes
    q1 = sum(p(1:k));
    q2 = sum(p(k+1:end));
    
    % Calcular la media de cada parte
    mu1 = sum(p(1:k) .* (1:k)') / q1;
    mu2 = sum(p(k+1:end) .* (k+1:length(p))') / q2;
    
    % Calcular la varianza intra-clase y actualizar el umbral si es mayor
    var_intra = q1 * q2 * (mu1 - mu2)^2;
    if var_intra > var_max
        var_max = var_intra;
        thres = k;
    end
end

% Binarizar la imagen utilizando el umbral óptimo
Ibw = imbinarize(Igray, thres/255);
end

