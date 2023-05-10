package si2023.sergiogarcia1alu.p05.recursos;

import si2023.sergiogarcia1alu.p05.operadores.RecursosTypes;
import si2023.sergiogarcia1alu.strips.Meta;

public class TengoSeta extends Meta implements Comparable <TengoSeta> {
    public TengoSeta(){
        type = RecursosTypes.TengoSeta.Value;
    }
    @Override

    protected int calcule_hash() {
        return RecursosTypes.TengoSeta.Value * 17;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass())
            return false;

        TengoSeta other = (TengoSeta) obj;
        return this.type == other.type;

    }

    @Override
    public int compareTo(TengoSeta other) {
        return 0;
    }
}
