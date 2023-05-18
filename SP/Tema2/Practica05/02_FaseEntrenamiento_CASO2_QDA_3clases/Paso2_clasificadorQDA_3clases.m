clear, clc;

addpath("../Funciones/");
load("DatosGenerados/espacio_ccas_circulos_cuadrados_triangulos.mat")

[medias, m_covarianza, prob_priori] = funcion_ajusta_QDA(XoI, YoI);

[YQDA, d] = funcion_aplica_QDA(XoI, medias, m_covarianza,...
    prob_priori, nombres_problema_oi.codif);



rmpath("../../../Funciones/");