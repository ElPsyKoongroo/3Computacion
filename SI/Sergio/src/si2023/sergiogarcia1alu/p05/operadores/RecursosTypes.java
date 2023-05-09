package si2023.sergiogarcia1alu.p05.operadores;

public enum RecursosTypes {
    BloquePiedra(2),
    BloqueLibre(1),
    Gujero(3),
    Jugador(8),
    Llave(5),
    Puerta(7),
    Seta(4),
    TengoLlave(6),
    TengoSeta(9);


    public final int Value;
    RecursosTypes(int value) { this.Value = value; }
}
