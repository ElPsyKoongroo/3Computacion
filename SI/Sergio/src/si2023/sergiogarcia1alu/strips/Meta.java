package si2023.sergiogarcia1alu.strips;

/**
 * @author sergio <br>
 * <p>
 * La clase meta esta dedicada a guardar los recursos de nuestro problema.<br><br>
 * <p>
 * En el ejemplo del pdf de las letras una meta podria ser el recurso 'a' o el recurso 'b'<br><br>
 */
public abstract class Meta implements IStackeable {

    protected int cached_hash;
    protected boolean cached = false;
    public int type;

    @Override
    public final boolean is_accion() {
        return false;
    }

    // Es final porque siempre va a devolver this. Nunca se van a modificar las metas,
    // solo se van a crear nuevas o clonar a partir de otras, por lo tanto es seguro.
    @Override
    public final Meta clone() {
        return this;
    }

    @Override
    public abstract boolean equals(Object o);

    protected abstract int calcule_hash();

    public int hashCode() {
        if (cached) return cached_hash;
        return this.calcule_hash();
    }
}
