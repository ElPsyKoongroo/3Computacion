package si2023.sergiogarcia1alu.p06;

import si2023.sergiogarcia1alu.strips.*;
import si2023.sergiogarcia1alu.strips.Strips.TipoRecorrido;

import java.util.ArrayList;


/*
 * 
 * Primero que nada, ¿Como estan los maquinas?
 * 
 * Implementacion del juego "Torres de Hanoi" para el algoritmo STRIPS
 *
 * 
 * Java 1.8 es una caca, los genericos funcionan mal y no he podido hacer esto como queria pero bueno.
 * La implementacion que he sacado es capaz de resolver el juego hasta 3 discos, más no.
 * 
 * 
 * No he tenido tiempo de mejorarlo pero tengo intencion de implementar esto en un buen lenguaje
 * (Rust) ademas de intentar mejorar la porqueria de codigo que tengo hecho ahora porque madre 
 * de dios.
 * 
 * 
 * Espero que te lo pases bien leyendo el codigo pero aviso de que no hay ninguna variable
 * con nombres del estilo "FACTOR_DE_TONTEO".
 *
 * Si lo ejecutas en Java 17 es mas de el doble de rapido que Java 1.8.
 *
 *
 * En el codigo hay un mecanismo de "cache hash" para guardar el hash de un objeto ya que tiene
 * un peso importante en el runtime del programa. De esta forma se logra mejorar el performance bastante.
 * */

public class JuegoStrips {

    public JuegoStrips() {
        // TODO Auto-generated constructor stub
    }

    public static void main(String[] args) {

        ArrayList<Meta> estado_actual = new ArrayList<>();
        // Tip: No pongas mas de 9 discos si no tienes +16Gb de RAM ;)
        final int discos = 8;

        estado_actual.add(new Despejado(1));
        estado_actual.add(new Despejado(-2));
        estado_actual.add(new Despejado(-3));

        // Discos menores que otros discos
        for(int disco_actual = 2; disco_actual <= discos; ++disco_actual) {
            for (int i = 1; i < disco_actual; i++) {
                estado_actual.add(new Menor(i, disco_actual));
            }
        }

        // Discos menores que las varillas
        for (int i = 1; i <= discos; i++) {
            for (int varilla = -3; varilla < 0; varilla++) {
                estado_actual.add(new Menor(i, varilla));
            }
        }

        // Disposicion de los discos
        for (int i = 1; i < discos; i++) {
            estado_actual.add(new Sobre(i, i+1));
        }
        estado_actual.add(new Sobre(discos, -1));

        // Estado inicial de los discos
        ArrayList<IStackeable> objetivos = new ArrayList<>();
        for (int i = 1; i < discos; i++) {
            objetivos.add(new Sobre(i, i+1));
        }
        objetivos.add(new Sobre(discos, -3));
        
        StripsState estado_inicial = new StripsState(estado_actual, objetivos);

        ArrayList<Accion> acciones = new ArrayList<>();
        acciones.add(new Apilar());

        // Estado de meta
        ArrayList<Meta> objetivo_meta = new ArrayList<>();
        for (int i = 1; i < discos; i++) {
            objetivo_meta.add(new Sobre(i, i + 1));
        }
        objetivo_meta.add(new Sobre(discos, -3));
        
        Strips super_solver = new Strips(estado_inicial, acciones, objetivo_meta, TipoRecorrido.Anchura);
        super_solver.resolver();
    }
}
