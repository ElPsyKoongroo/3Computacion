package si2023.sergiogarcia1alu.p05.recursos;

import si2023.sergiogarcia1alu.strips.Meta;
import tools.Vector2d;

import java.util.Objects;

public class Puerta extends Meta {
    public Vector2d posicion;
    public Puerta(Vector2d pos) {
        posicion = pos;
        type = 7;
    }
    @Override
    protected int calcule_hash() {
        return Objects.hash(7, posicion.x, posicion.y);
    }
}
