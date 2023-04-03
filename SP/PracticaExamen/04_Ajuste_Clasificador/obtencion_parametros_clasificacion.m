%% Load data

clc, clearvars, close all;
esferas_path = "../03_Disegno_Clasificador/datos_multiple_esferas";
load(esferas_path, "esferas");

%% 

centro_esfera = esferas(1, 1:3);
radio_esferas = esferas(1,4);

%%
clc, clear, clearvars;
video = VideoReader("videito.mp4");
I = readFrame(video);
