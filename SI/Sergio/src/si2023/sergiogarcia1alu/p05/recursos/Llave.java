package si2023.sergiogarcia1alu.p05.recursos;

import si2023.sergiogarcia1alu.p05.operadores.RecursosTypes;
import si2023.sergiogarcia1alu.strips.Meta;
import tools.Vector2d;

import java.util.Objects;

public class Llave extends Meta {
    public Vector2d posicion;
    public Llave(Vector2d pos) {
        posicion = pos;
        type = RecursosTypes.Llave.Value;
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

    @Override
    protected int calcule_hash()
    {
        return Objects.hash(RecursosTypes.Llave.Value, posicion.x, posicion.y);
    }
}
