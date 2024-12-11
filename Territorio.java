import java.util.*;
public class Territorio {
    private static final String[] TERRITORIOS = {
        "Bosque", "Campo Abierto", "Montaña", "Desierto", "Playa"
    };

    public static String generarTerritorio() {
        Random random = new Random();
        return TERRITORIOS[random.nextInt(TERRITORIOS.length)];
    }
}
