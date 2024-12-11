import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Mapa mapa = new Mapa(10, 10);

        System.out.println("\n");

        System.out.println("En un mundo donde las ambiciones de poder y gloria dominan los corazones de los gobernantes, "
                         + "los antiguos reinos han decidido enfrentar su destino en el campo de batalla.\n"
                         + "Desde las frías tierras de los Reinos Escandinavos, pasando por los férreos Reinos Anglo-Sajones y el noble Reino de Asturias,\n"
                         + "hasta los imponentes dominios del Imperio Bizantino, el Imperio Carolingio y el Reino de los Búlgaros, las tensiones han alcanzado un punto crítico.\n"
                         + "Cada reino busca consolidar su supremacía en un mundo dividido, utilizando estrategias militares, unidades especializadas y el dominio del territorio como sus principales armas.\n"
                         + "Las guerras han comenzado, y solo uno podrá reclamar la hegemonía sobre estas tierras conflictivas.");

        System.out.println("\n");

        String[] reinosSeleccionados = Reino.escogerReinos();

        System.out.println("Jugador 1 ha elegido: " + reinosSeleccionados[0]);

        System.out.println("------------------------------------------");

        System.out.println("Jugador 2 ha elegido: " + reinosSeleccionados[1]);

        System.out.println("------------------------------------------");

        Ejercito ejercito1 = new Ejercito(reinosSeleccionados[0]);
        ejercito1.generarSoldados(1);

        Ejercito ejercito2 = new Ejercito(reinosSeleccionados[1]);
        ejercito2.generarSoldados(1);

        ejercito1.mostrarSoldados();
        System.out.println("------------------------------------------");

        ejercito2.mostrarSoldados();
        System.out.println("------------------------------------------");

        String territorio = Territorio.generarTerritorio();
        System.out.println("El territorio actual es: " + territorio);

        ejercito1.aplicarBeneficios(territorio);
        ejercito2.aplicarBeneficios(territorio);

        for (Soldado soldado : ejercito1.getSoldados()) {
            mapa.colocarSoldado(soldado);
        }

        for (Soldado soldado : ejercito2.getSoldados()) {
            mapa.colocarSoldado(soldado);
        }

        JuegoDeBatalla juego = new JuegoDeBatalla(mapa, ejercito1, ejercito2);
        juego.iniciar();

        scanner.close();
    }
}
