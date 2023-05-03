package si2023.sergiogarcia1alu.strips;

import java.util.ArrayList;
import java.util.Objects;

public class ConjuncionMeta implements IStackeable {

        private final ArrayList<Meta> recursos;

        public ConjuncionMeta(ArrayList<IStackeable> recursos) {
            this.recursos = new ArrayList<>();
            for(IStackeable meta: recursos) {
                if (!meta.is_accion()) {
                    this.recursos.add((Meta)meta);
                }
            }
        }

        public ConjuncionMeta(ConjuncionMeta other) {
            this.recursos = other.recursos;
        }

        public ArrayList<Meta> get_recursos(){
            return this.recursos;
        }

        @Override
        public int hashCode() {
            int hash = 1;
            for(int i = 0; i<this.get_recursos().size(); i++) {
                hash *= this.get_recursos().get(i).hashCode() + i;
            }
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            ConjuncionMeta other = (ConjuncionMeta) obj;
            return Objects.equals(recursos, other.recursos);
        }
        
        public boolean contains(Meta recurso) {
            return this.recursos.contains(recurso);
        }
        
        
        @Override
        public boolean is_accion() {
            return false;
        }

        @Override
        public ConjuncionMeta clone() {
            return new ConjuncionMeta(this);
            
        }

        @Override
        public String toString(){
            String result = "";
            for(IStackeable e: this.get_recursos()) {
                result = result.concat(String.format("<%s>", e.toString()));
            }
            return result;
        }

}
