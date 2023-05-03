clear; clc;

X1 = []
X2 = []

X1 = [1,2,2,2,2,3,3,4,5,1;
      3,1,2,3,4,2,3,3,2,2  ];

X2 = [4,5,5,4,6,6,6,7,4,8;
      5,5,6,7,5,6,7,6,6,7]

figure(), hold on,

for i = 1:10
    plot(X1(1,i), X1(2,i), "*b"), hold on
    plot(X2(1,i), X2(2,i), "*r"), hold on
end

%fplot(@(x) ((-x+11.5)+(-x+5))/2)

X1Means = mean(X1')';
X2Means = mean(X2')';


x1 = sym('x1', 'real');
x2 = sym('x2', 'real');
X = [x1; x2];



dE2_1 = expand((X-X1Means)'*(X - X1Means))
dE2_2 = expand((X-X2Means)'*(X - X2Means))

funcion_discriminante = (sqrt(dE2_1) - sqrt(dE2_2));

for i = 1:10
    x1 = X1(1: i); x2 = X1(2, i);
    distancia_c1 = sqrt(eval(dE2_1));
    distancia_c2 = sqrt(eval(dE2_2));
    dd =  eval(funcion_discriminante)
    discriminante = distancia_c1 - distancia_c2
end


x1 = 2; x2 = 1; dE_OA = sqrt(eval(funcion_discriminante));


%% CÓDIGO MATLAB:
clc, clear
% Datos X:
datos = [1 ,1 ; 2 , 2.5 ; 3 , 3.5 ; 4,5 ; 5 , 6];
% Vector de medias (Punto O):
O = mean(datos)';
% Definición de X=[x1;x2] simbólico
x1 = sym('x1','real');
x2 = sym('x2','real');
X = [x1 ; x2];
% DE y MD al cuadrado de X al punto O
dE2 = expand((X-O)'*(X - O))

x1 = 3; x2 = 5; dE_OA = sqrt(eval(dE2));