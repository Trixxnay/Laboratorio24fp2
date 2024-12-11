import java.util.*;
public class Reino {
    private static final String[] REINOS_VALIDOS = {
        "Reinos Escandinavos", 
        "Reinos Anglo-Sajones", 
        "Reino de Asturias", 
        "Imperio Bizantino", 
        "Imperio Carolingio", 
        "Reino de los Búlgaros"
    };

    public static String[] escogerReinos() {
        Scanner scanner = new Scanner(System.in);
        String[] reinosSeleccionados = new String[2];

        for (int i = 0; i < 2; i++) {
            boolean seleccionValida = false;
            while (!seleccionValida) {
                System.out.println("Jugador " + (i + 1) + ", elige tu reino:");
                for (int j = 0; j < REINOS_VALIDOS.length; j++) {
                    System.out.println((j + 1) + ". " + REINOS_VALIDOS[j]);
                }

                System.out.print("Ingresa el número de tu reino: ");
                int seleccion = scanner.nextInt() - 1;

                if (seleccion >= 0 && seleccion < REINOS_VALIDOS.length) {
                    String reinoSeleccionado = REINOS_VALIDOS[seleccion];
                    if (i == 0 || !reinoSeleccionado.equals(reinosSeleccionados[0])) {
                        reinosSeleccionados[i] = reinoSeleccionado;
                        seleccionValida = true;
                        System.out.println("Jugador " + (i + 1) + " ha elegido: " + reinoSeleccionado);
                        System.out.println("------------------------------------------");
                    } else {
                        System.out.println("Ese reino ya ha sido elegido. Elige otro.");
                    }
                } else {
                    System.out.println("Selección inválida. Intenta nuevamente.");
                }
            }
        }
        
        return reinosSeleccionados;
    }
}
