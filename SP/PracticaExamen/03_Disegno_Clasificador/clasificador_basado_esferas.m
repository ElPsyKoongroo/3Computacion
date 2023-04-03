%% Cargar datos
clc, clearvars
datos_path = "../02_Representar_Datos/Datos_Generados/";
addpath(datos_path)
addpath("funciones/");
load("conjunto_datos_final.mat", 'X', 'Y');
X = double (X(:, 2:4));

%%
datos_esfera = calcula_datos_esfera(X(logical(Y), :), X(logical(~Y), :));
%%
datos_color = double(X(:, :));

media_colores = double(round(median(X(logical(Y), :))));

[rows, ~] = size(datos_color);
distancias = zeros(rows, 1);

for i = 1:rows 
    distancia = round(sqrt((datos_color(i, 1).^2 + datos_color(i, 2).^2 + datos_color(i, 3).^2)));
    distancias(i) = distancia;
end

distancias_color = distancias(logical(Y));
distancias_fondo = distancias(logical(~Y));
distancia_media = mean(distancias(logical(Y)));

[color_rows, ~] = size(distancias_color);
radios = zeros(1, 3);
sin_ruido = 0;
compromiso = 0;

%% Radio sin ruido
for i = 1:color_rows 
    distancia_act = distancias_color(i);
    distancia_aux =  abs(distancia_media-distancia_act);
    if ( distancia_aux > sin_ruido ) 
        sin_ruido = distancia_aux;
    end
end

%% Radio max pixeles
[fondo_rows, ~] = size(distancias_fondo);
colores_fondo = double(X(logical(~Y), :));

distancias_fondo = zeros(fondo_rows, 1); 
for i = 1:fondo_rows
    distancia_act = double(colores_fondo(i, :));
    distancia_aux = sqrt(sum((media_colores-distancia_act).^2));
    distancias_fondo(i) = distancia_aux;
end
max_pixeles = min(distancias_fondo);
%%
radio_media = (max_pixeles+sin_ruido)/2;
%%
radios(1) = sin_ruido;
radios(2) = radio_media;
radios(3) = max_pixeles;

%% Generar las esferas
esferas = zeros(3, 6);
[R,G,B] = sphere(100);
colores_esferas = [".k", ".g", ".y"];
Rc = media_colores(1);
Gc = media_colores(2);
Bc = media_colores(3);
plot3(X(logical(Y), 1), X(logical(Y), 2), X(logical(Y), 3), ".b"), hold on;
plot3(X(logical(~Y), 1), X(logical(~Y), 2), X(logical(~Y), 3), ".r"), hold on;
esferas(1,1) = Rc;
esferas(1,2) = Gc;
esferas(1,3) = Bc;
for i = 1:3
    Radio = radios(i);
    x = Radio*R(:)+Rc; 
    y = Radio*G(:)+Gc; 
    z = Radio*B(:)+Bc;
    esferas(1, i+3) = Radio;
    representa_esfera(esferas(i, (1:3)), Radio, colores_esferas(i)), hold on;
end
%%
save("datos_multiple_esferas", "esferas");

%%