package si2023.sergiogarcia1alu.p05.recursos;

import si2023.sergiogarcia1alu.p05.operadores.RecursosTypes;
import si2023.sergiogarcia1alu.strips.Meta;

public class HeSalido extends Meta implements Comparable<HeSalido>{

    public HeSalido()
    {
        type = RecursosTypes.HeSalido.Value;
    }
    @Override
    protected int calcule_hash() {
        return RecursosTypes.HeSalido.Value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass())
            return false;

        HeSalido other = (HeSalido) obj;
        return this.type == other.type;

    }

    @Override
    public int compareTo(HeSalido other) {
        return 0;
    }
}
