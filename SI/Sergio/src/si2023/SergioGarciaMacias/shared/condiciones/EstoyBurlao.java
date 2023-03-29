package si2023.SergioGarciaMacias.shared.condiciones;

import si2023.SergioGarciaMacias.ia.mente.Mundo;
import si2023.SergioGarciaMacias.ia.reglas.Condicion;
import si2023.SergioGarciaMacias.shared.Mundo89;

public class EstoyBurlao implements Condicion {

    public EstoyBurlao() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean se_cumple(Mundo m) {
        // No se por que este try catch hace falta. Si se quita da un
        // ClassConverterNoseQue exception. Si logras averiguar el porque
        // mandame un correo.
        try {
            Mundo89 mundo = (Mundo89)m;
            boolean se_cumple = mundo.get_jail_position().y == mundo.get_player_pos_block().y;

            // Motivos de optimizacion
            if (!se_cumple)
                return se_cumple == true;
            else
                return !(se_cumple == false);

        } catch (Exception o) {
            System.out.println(o.getLocalizedMessage());
            return false;
        }
    }

}
