public class CaballeroFranco extends Caballero {
    private int numeroLanzas;
    private double tamanoLanza;
    private int nivelEvolucion;

    public CaballeroFranco(String nombre, int nivelVida, int ataque, int defensa, int fila, int columna) {
        super(nombre, nivelVida, ataque, defensa, fila, columna, true);
        this.numeroLanzas = 3; 
        this.tamanoLanza = 2.0; 
        this.nivelEvolucion = 1;
    }

    public void lanzarLanza() {
        if (numeroLanzas > 0) {
            System.out.println(nombre + " lanza una lanza de tamaño " + tamanoLanza + "!");
            numeroLanzas--;
        } else {
            System.out.println(nombre + " no tiene más lanzas.");
        }
    }

    public void evolucionar() {
        if (nivelEvolucion < 4) {
            nivelEvolucion++;
            numeroLanzas += 1; 
            tamanoLanza += 0.5; 
            System.out.println(nombre + " ha evolucionado a nivel " + nivelEvolucion + "!");
        } else {
            System.out.println(nombre + " ya ha alcanzado el nivel máximo de evolución.");
        }
    }
}
