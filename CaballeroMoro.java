public class CaballeroMoro extends Caballero {
    private int numeroFlechas;
    private double tamanoFlecha;
    private int nivelEvolucion;

    // Constructor que incluye validaciones para el nombre
    public CaballeroMoro(String nombre, int nivelVida, int ataque, int defensa, int fila, int columna) {
        super(nombre, nivelVida, ataque, defensa, fila, columna, true);

        // Verificar que el nombre no sea null ni vacío
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del Caballero Moro no puede ser null o vacío.");
        }

        this.numeroFlechas = 5;
        this.tamanoFlecha = 1.5;
        this.nivelEvolucion = 1;
    }

    // Método para lanzar una flecha
    public void lanzarFlecha() {
        if (numeroFlechas > 0) {
            System.out.println(nombre + " lanza una flecha de tamaño " + tamanoFlecha + "!");
            numeroFlechas--;
        } else {
            System.out.println(nombre + " no tiene más flechas para lanzar.");
        }
    }

    // Método para realizar una envestida
    public void envestir() {
        System.out.println(nombre + " realiza una envestida poderosa!");
        this.ataque += 5; 
    }

    // Método para evolucionar al caballero
    public void evolucionar() {
        if (nivelEvolucion < 4) {
            nivelEvolucion++;
            numeroFlechas += 3; 
            tamanoFlecha += 0.5; 
            System.out.println(nombre + " ha evolucionado a nivel " + nivelEvolucion + " y ahora tiene " + numeroFlechas + " flechas de tamaño " + tamanoFlecha + "!");
        } else {
            System.out.println(nombre + " ya ha alcanzado el nivel máximo de evolución.");
        }
    }
}
