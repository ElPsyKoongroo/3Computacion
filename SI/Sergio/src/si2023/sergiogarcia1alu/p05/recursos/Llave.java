package si2023.sergiogarcia1alu.p05.recursos;

import si2023.sergiogarcia1alu.strips.Meta;
import tools.Vector2d;

import java.util.Objects;

public class Llave extends Meta {
    public Vector2d posicion;
    public Llave(Vector2d pos)
    {
        posicion = pos;
        type = 5;
    }
    @Override
    protected int calcule_hash()
    {
        return Objects.hash(5, posicion.x, posicion.y);
    }
}
