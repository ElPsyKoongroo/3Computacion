package si2023.SergioGarciaMacias.ia.mente;

import ontology.Types.ACTIONS;
import si2023.SergioGarciaMacias.ia.reglas.Nodo;

public abstract class Arbol {

    protected Nodo root;

    public Arbol() {
    };

    public Nodo get_root() {
        return this.root;
    }

    public ACTIONS pensar(Mundo m) {
        Nodo estado_actual = this.root;

        while (!estado_actual.es_hoja()) {
            estado_actual = estado_actual.decide(m);
        }
 
        return estado_actual.get_action(m);
    }
}
