function Ib = calcula_deteccion_1esfera_en_imagen(I, centro_radio)
    centro_esfera = centro_radio(1:3);
    radio_esfera = centro_radio(4);
    Ib = funcion_calcula_distancia(I, centro_esfera, radio_esfera);
end

