package si2023.sergiogarcia1alu.strips;

import java.util.HashSet;

public class HashSetMetas extends HashSet<Meta> {

    public HashSetMetas(){
        super();
    }

    public HashSetMetas(HashSetMetas metas){
        super();
        this.addAll(metas);
    }

    @Override
    public boolean contains(Object o) {
        if (!(o instanceof Meta)) {
            return false;
        }
        Meta p = (Meta) o;
        for (Meta persona : this) {
            if (persona.hashCode() == p.hashCode()) {
                return true;
            }
        }
        return false;
    }
}
