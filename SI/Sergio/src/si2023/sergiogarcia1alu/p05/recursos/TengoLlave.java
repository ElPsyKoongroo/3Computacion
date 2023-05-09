package si2023.sergiogarcia1alu.p05.recursos;

import si2023.sergiogarcia1alu.p05.operadores.RecursosTypes;
import si2023.sergiogarcia1alu.strips.Meta;

public class TengoLlave extends Meta {
    public TengoLlave()
    {
        type = RecursosTypes.TengoLlave.Value;
    }
    @Override
    protected int calcule_hash() {
        return RecursosTypes.TengoLlave.Value * 31;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass())
            return false;

        return this.hashCode() == obj.hashCode();

    }
}
