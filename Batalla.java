import java.util.*;

public class Batalla {

    // Método para enfrentar a dos soldados
    public static Soldado enfrentar(Soldado soldado1, Soldado soldado2) {
        // Comprobación de null para evitar errores
        if (soldado1 == null || soldado2 == null) {
            System.out.println("Error: uno de los soldados es null.");
            return null;
        }

        // Cálculo de la suma de vida de los dos soldados
        int sumaVida = soldado1.getNivelVida() + soldado2.getNivelVida();

        // Generación de un número aleatorio entre 0 y sumaVida
        Random random = new Random();
        double resultado = random.nextDouble() * sumaVida + 1; // Corregido el uso de nextDouble()

        // Determinar el ganador de la batalla y aumentar su vida
        if (resultado < (double) soldado1.getNivelVida()) {
            soldado1.nivelVida += 1; 
            System.out.println(soldado1.getNombre() + " gana la batalla contra " + soldado2.getNombre() + 
                               ". Vida de " + soldado1.getNombre() + ": " + soldado1.getNivelVida() +
                               " | Vida de " + soldado2.getNombre() + ": " + soldado2.getNivelVida());
            return soldado1;
        } else {
            soldado2.nivelVida += 1; 
            System.out.println(soldado2.getNombre() + " gana la batalla contra " + soldado1.getNombre() + 
                               ". Vida de " + soldado1.getNombre() + ": " + soldado1.getNivelVida() +
                               " | Vida de " + soldado2.getNombre() + ": " + soldado2.getNivelVida());
            return soldado2;
        }
    }

    // Método para verificar las reglas de la batalla
    public static boolean verificarReglasBatalla(Soldado soldado1, Soldado soldado2) {
        // Comprobación de null para evitar errores
        if (soldado1 == null || soldado2 == null) {
            System.out.println("Error: uno de los soldados es null.");
            return false;
        }

        // Reglas de batalla según las clases de los soldados
        if (soldado1 instanceof Caballero && soldado2 instanceof Arquero) {
            soldado1.nivelVida += 1; // Caballero gana
        } else if (soldado1 instanceof Caballero && soldado2 instanceof Lancero) {
            soldado2.nivelVida += 1; // Lancero gana
        } else if (soldado1 instanceof Arquero && soldado2 instanceof Lancero) {
            soldado1.nivelVida += 1; // Arquero gana
        } else if (soldado1 instanceof Caballero && soldado2 instanceof Espadachin) {
            soldado1.nivelVida += 1; // Caballero gana
        } else if (soldado1 instanceof Espadachin && soldado2 instanceof Lancero) {
            soldado1.nivelVida += 1; // Espadachin gana
        }

        return true;
    }
}
