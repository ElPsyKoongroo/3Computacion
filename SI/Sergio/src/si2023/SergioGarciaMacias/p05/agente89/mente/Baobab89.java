package si2023.SergioGarciaMacias.p05.agente89.mente;

import ontology.Types.ACTIONS;
import si2023.SergioGarciaMacias.ia.mente.Arbol;
import si2023.SergioGarciaMacias.ia.mente.Mundo;
import si2023.SergioGarciaMacias.ia.reglas.Nodo;
import si2023.SergioGarciaMacias.p05.agente89.nodos.NodoAccion;
import si2023.SergioGarciaMacias.p05.agente89.nodos.NodoEstandar;
import si2023.SergioGarciaMacias.shared.acciones.CapturaEnemigo;
import si2023.SergioGarciaMacias.shared.acciones.IrCarcel;
import si2023.SergioGarciaMacias.shared.acciones.IrCentro;
import si2023.SergioGarciaMacias.shared.acciones.ProtegeParguela;
import si2023.SergioGarciaMacias.shared.acciones.SalirDeCarcel;
import si2023.SergioGarciaMacias.shared.acciones.SalvaParguela;
import si2023.SergioGarciaMacias.shared.acciones.SalvaParguelaPeligro;
import si2023.SergioGarciaMacias.shared.condiciones.BolsaLLena;
import si2023.SergioGarciaMacias.shared.condiciones.EstoyBurlao;
import si2023.SergioGarciaMacias.shared.condiciones.HayEnemigo;
import si2023.SergioGarciaMacias.shared.condiciones.ParguelaCayendo;
import si2023.SergioGarciaMacias.shared.condiciones.ParguelaEnPeligro;
import si2023.SergioGarciaMacias.shared.condiciones.ParguelaMedioMuerto;

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

    
    @Override
    public ACTIONS decide(Mundo m) {
        // TODO Auto-generated method stub
        return null;
    }
    

    @Override
    public ACTIONS pensar(Mundo m) {
        Nodo estado_actual = this.root;

        while (!estado_actual.es_hoja()) {
            estado_actual = estado_actual.decide(m);
        }

        NodoAccion estado_accion = (NodoAccion) estado_actual;
        return estado_accion.get_action(m);
    }

}
