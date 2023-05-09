package si2023.sergiogarcia1alu.p05.recursos;

import si2023.sergiogarcia1alu.p05.operadores.RecursosTypes;
import si2023.sergiogarcia1alu.strips.Meta;
import tools.Vector2d;

import java.util.Objects;

public class Gujero extends Meta {

    public Vector2d posicion;
    public Gujero(Vector2d pos) {
        posicion = pos;
        type = RecursosTypes.Gujero.Value;
    }
    @Override
    protected int calcule_hash()
    {
        return Objects.hash(RecursosTypes.Gujero.Value, posicion.x, posicion.y);
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
