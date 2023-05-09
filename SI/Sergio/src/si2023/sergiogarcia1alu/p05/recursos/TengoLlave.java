package si2023.sergiogarcia1alu.p05.recursos;

import si2023.sergiogarcia1alu.strips.Meta;

public class TengoLlave extends Meta {
    public TengoLlave()
    {
        type = 6;
    }
    @Override
    protected int calcule_hash() {
        return 6;
    }
}
