package si2023.sergiogarcia1alu.p05.recursos;

import si2023.sergiogarcia1alu.strips.Meta;
import tools.Vector2d;

import java.util.Objects;

public class Gujero extends Meta {

    public Vector2d posicion;
    public Gujero(Vector2d pos)
    {
        posicion = pos;
        type = 3;
    }
    @Override
    protected int calcule_hash()
    {
        return Objects.hash(3, posicion.x, posicion.y);
    }
}
