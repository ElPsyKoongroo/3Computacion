clear, close all, clc;

addpath("Datos/ej5/")
load("datos_MDE_2dimensiones.mat")

numDatos = size(X,1);
porcentajeTrain = 0.7;
numDatosTrain = round(porcentajeTrain*numDatos);
numerosMuestrasTrain = randi(numDatos, [1,numDatosTrain]);
numerosMuestrasTest = find(not(ismember(1:numDatos,numerosMuestrasTrain)));
% Conjunto de Train
XTrain = X(numerosMuestrasTrain,:);
YTrain = Y(numerosMuestrasTrain);
% Conjunto de Test
XTest = X(numerosMuestrasTest,:);
YTest = Y(numerosMuestrasTest);

figure(), subplot(2,1,1), hold on

colores = {'*r', '*b'};
for i = 1:numDatos*porcentajeTrain
    color = colores{YTrain(i)};
    plot(XTrain(i,1), XTrain(i,2), color), hold on;
end

subplot(2,1,2), hold on
for i = 1:numDatos-numDatos*porcentajeTrain
    color = colores{YTest(i)};
    plot(XTest(i,1), XTest(i,2), color), hold on;
end

%%

C1 = XTrain(YTrain == 1, :);
C2 = XTrain(YTrain == 2, :);

C1_media = mean(C1)';
C2_media = mean(C2)';

% Las matrices de covarianza son muy parecidas,
% asumimos que son la misma
C1_cov = cov(C1);
C2_cov = cov(C2);

nc1 = sum(YTrain == 1);
nc2 = sum(YTrain == 2); 

Cov_media = ((nc1-1)*C1_cov+(nc2-1)*(C2_cov))/(nc1+nc2-2);

x1 = sym('x1', 'real');
x2 = sym('x2', 'real');
nc1  = sym('n', 'real');
nc2  = sym('n', 'real');
X = [x1; x2];

% Clasificador LDA
C1_dE = expand(-(1/2)*(X-C1_media)'*inv(Cov_media)*(X - C1_media)+log10(nc1));
C2_dE = expand(-(1/2)*(X-C2_media)'*inv(Cov_media)*(X - C2_media)+log10(nc2));

funcion_discriminante = (sqrt(C1_dE) - sqrt(C2_dE));


x1 = 1; x2 = 0; n = 702; a1 = eval(C1_dE - C2_dE);
x1 = 0; x2 = 1; n = 702; b1 = eval(C1_dE - C2_dE);


subplot(2,1,1), fplot(@(x) ((a1*x)/-b1)), hold on
subplot(2,1,2), fplot(@(x) ((a1*x)/-b1)), hold on

% numero de elementos de la clase 1

aciertos = 0;
for i = 1:numDatos-numDatos*porcentajeTrain
    x1 = XTest(i,1); x2 = XTest(i,2);
    clase =  eval(funcion_discriminante);
    if clase > 0
        clase = 1;
    else
        clase = 2;
    end

    if YTest(i) == clase
        aciertos = aciertos + 1;
    end
end

TASAL = aciertos/(numDatos-numDatos*porcentajeTrain)

%Clasificador QDA

C1_dEQ = expand(-(1/2)*(X-C1_media)'*inv(C1_cov)*(X - C1_media)-(1/2)*log10(det(C1_cov))+log10(nc1));
C2_dEQ = expand(-(1/2)*(X-C2_media)'*inv(C2_cov)*(X - C2_media)-(1/2)*log10(det(C2_cov))+log10(nc2));

funcion_discriminanteQ = (C1_dEQ-C2_dEQ);

aciertos = 0;
for i = 1:numDatos-numDatos*porcentajeTrain
    x1 = XTest(i,1); x2 = XTest(i,2);
    clase =  eval(funcion_discriminanteQ);
    if clase > 0
        clase = 1;
    else
        clase = 2;
    end

    if YTest(i) == clase
        aciertos = aciertos + 1;
    end
end

TASAQ = aciertos/(numDatos-numDatos*porcentajeTrain)

x1 = 0; x2 = 0;  c = eval(funcion_discriminanteQ);
x1 = 1; x2 = 0; a1 = eval(funcion_discriminanteQ)-c;
x1 = 0; x2 = 1; b1 = eval(funcion_discriminanteQ)-c;


% Evaluar la función cuadrática en cada punto de x

% Graficar la función cuadrática
subplot(2,1,1), fplot(@(x) (a1*x^2 + b1*x + c)), hold on

% La representacion esta mal
% subplot(2,1,1), fplot(@(x) (((a1*x^2)+c)/-b1)), hold on
% subplot(2,1,2), fplot(@(x) ((a1*x^2)/b1));



rmpath("Datos/ej5/")