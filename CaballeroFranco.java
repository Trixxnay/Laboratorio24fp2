public class CaballeroFranco extends Caballero {
    private int numeroLanzas;
    private double tamanoLanza;
    private int nivelEvolucion;

    // Constructor que incluye validaciones para el nombre
    public CaballeroFranco(String nombre, int nivelVida, int ataque, int defensa, int fila, int columna) {
        super(nombre, nivelVida, ataque, defensa, fila, columna, true);

        // Verificar que el nombre no sea null ni vacío
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del Caballero Franco no puede ser null o vacío.");
        }

        this.numeroLanzas = 3; 
        this.tamanoLanza = 2.0; 
        this.nivelEvolucion = 1;
    }

    // Método para lanzar una lanza
    public void lanzarLanza() {
        if (numeroLanzas > 0) {
            System.out.println(nombre + " lanza una lanza de tamaño " + tamanoLanza + "!");
            numeroLanzas--;
        } else {
            System.out.println(nombre + " no tiene más lanzas para lanzar.");
        }
    }

    // Método para evolucionar al caballero
    public void evolucionar() {
        if (nivelEvolucion < 4) {
            nivelEvolucion++;
            numeroLanzas += 1; 
            tamanoLanza += 0.5; 
            System.out.println(nombre + " ha evolucionado a nivel " + nivelEvolucion + " y ahora tiene " + numeroLanzas + " lanzas de tamaño " + tamanoLanza + "!");
        } else {
            System.out.println(nombre + " ya ha alcanzado el nivel máximo de evolución.");
        }
    }
}
