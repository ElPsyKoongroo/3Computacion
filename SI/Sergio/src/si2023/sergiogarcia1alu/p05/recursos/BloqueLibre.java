package si2023.sergiogarcia1alu.p05.recursos;

import si2023.sergiogarcia1alu.strips.Meta;
import tools.Vector2d;

import java.util.Objects;

public class BloqueLibre extends Meta {
    public Vector2d posicion;
    public BloqueLibre(Vector2d pos)
    {
        posicion = pos;
        type = 1;
    }
    @Override
    protected int calcule_hash()
    {
        return Objects.hash(1, posicion.x, posicion.y);
    }
}
