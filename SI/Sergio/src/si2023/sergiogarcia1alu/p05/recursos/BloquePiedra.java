package si2023.sergiogarcia1alu.p05.recursos;

import si2023.sergiogarcia1alu.strips.Meta;
import tools.Vector2d;

import java.util.Objects;

public class BloquePiedra extends Meta {
    public Vector2d Posicion;
    public BloquePiedra(Vector2d pos)
    {
        Posicion = pos;
        type = 2;
    }
    @Override
    protected int calcule_hash()
    {
        return Objects.hash(2, Posicion.x, Posicion.y);
    }
}
