package si2023.sergiogarcia1alu.p03.agente89.mente;

import si2023.sergiogarcia1alu.ia.mente.Arbol;
import si2023.sergiogarcia1alu.p03.agente89.nodos.NodoAccion;
import si2023.sergiogarcia1alu.p03.agente89.nodos.NodoEstandar;
import si2023.sergiogarcia1alu.shared.acciones.CapturaEnemigo;
import si2023.sergiogarcia1alu.shared.acciones.IrCarcel;
import si2023.sergiogarcia1alu.shared.acciones.IrCentro;
import si2023.sergiogarcia1alu.shared.acciones.ProtegeParguela;
import si2023.sergiogarcia1alu.shared.acciones.SalirDeCarcel;
import si2023.sergiogarcia1alu.shared.acciones.SalvaParguela;
import si2023.sergiogarcia1alu.shared.acciones.SalvaParguelaPeligro;
import si2023.sergiogarcia1alu.shared.condiciones.BolsaLLena;
import si2023.sergiogarcia1alu.shared.condiciones.EstoyBurlao;
import si2023.sergiogarcia1alu.shared.condiciones.HayEnemigo;
import si2023.sergiogarcia1alu.shared.condiciones.ParguelaCayendo;
import si2023.sergiogarcia1alu.shared.condiciones.ParguelaEnPeligro;
import si2023.sergiogarcia1alu.shared.condiciones.ParguelaMedioMuerto;

// La clase se llama Baobab porque los Baobab son
// mis arboles favoritos. Podria llamarse arbol89
// pero eso no encajaria bien con el FACTOR_DE_TONTEO.
//
// see:
// 	https://es.wikipedia.org/wiki/Adansonia_grandidieri
public class Baobab89 extends Arbol {

    public Baobab89() {
        NodoAccion default_node = new NodoAccion(new IrCentro());

        NodoEstandar hay_enemigo = new NodoEstandar(new HayEnemigo(),
                new NodoAccion(new CapturaEnemigo()), default_node
        );
 
        NodoEstandar salir_carcel = new NodoEstandar(new EstoyBurlao(),
                new NodoAccion(new SalirDeCarcel()), hay_enemigo);

        NodoEstandar bolsa_llena = new NodoEstandar(new BolsaLLena(),
                new NodoAccion(new IrCarcel()), salir_carcel);

        /*
         * Experimental feature.
         * 
         * Parece bajar un poco el winrate si se activa. Por ahora
         * esta desactivado.
         * 
         * Para activar cambiar en el NodoEstandar "bolsa_llena" por "protege_parguela"
         */ 
        @SuppressWarnings("unused")
        NodoEstandar protege_parguela = new NodoEstandar(new ParguelaEnPeligro(),
                new NodoAccion(new ProtegeParguela()), bolsa_llena);

        NodoEstandar rescata_parguela = new NodoEstandar(new ParguelaCayendo(),
                new NodoAccion(new SalvaParguela()), bolsa_llena 
        );

        NodoEstandar rescate_pp = new NodoEstandar(new ParguelaMedioMuerto(),
                new NodoAccion(new SalvaParguelaPeligro()), rescata_parguela);

        this.root = rescate_pp;
    } 
}
