public class CaballeroMoro extends Caballero {
    private int numeroFlechas;
    private double tamanoFlecha;
    private int nivelEvolucion;

    public CaballeroMoro(String nombre, int nivelVida, int ataque, int defensa, int fila, int columna) {
        super(nombre, nivelVida, ataque, defensa, fila, columna, true);
        this.numeroFlechas = 5; 
        this.tamanoFlecha = 1.5; 
        this.nivelEvolucion = 1;
    }

    public void lanzarFlecha() {
        if (numeroFlechas > 0) {
            System.out.println(nombre + " lanza una flecha de tamaño " + tamanoFlecha + "!");
            numeroFlechas--;
        } else {
            System.out.println(nombre + " no tiene más flechas.");
        }
    }

    public void envestir() {
        System.out.println(nombre + " realiza una envestida poderosa!");
        this.ataque += 5; 
    }

    public void evolucionar() {
        if (nivelEvolucion < 4) {
            nivelEvolucion++;
            numeroFlechas += 3; 
            tamanoFlecha += 0.5; 
            System.out.println(nombre + " ha evolucionado a nivel " + nivelEvolucion + "!");
        } else {
            System.out.println(nombre + " ya ha alcanzado el nivel máximo de evolución.");
        }
    }
}
