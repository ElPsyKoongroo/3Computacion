package si2023.sergiogarcia1alu.p05.operadores;

public enum RecursosTypes {
    BloquePiedra(1),
    BloqueLibre(0),
    Gujero(2),
    Jugador(7),
    Llave(4),
    Puerta(6),
    Seta(3),
    TengoLlave(5),
    TengoSeta(8),
    Pared(9),
    HeSalido(10);

    public final static int SIZE = 11;
    public final int Value;
    RecursosTypes(int value) { this.Value = value; }
}
