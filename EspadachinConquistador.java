public class EspadachinConquistador extends Espadachin {
    private int numeroHachas;
    private double tamanoHacha;
    private int nivelEvolucion;

    public EspadachinConquistador(String nombre, int nivelVida, int ataque, int defensa, int fila, int columna) {
        super(nombre, nivelVida, ataque, defensa, fila, columna);
        this.numeroHachas = 3; 
        this.tamanoHacha = 2.0; 
        this.nivelEvolucion = 1;
    }

    public void lanzarHacha() {
        if (numeroHachas > 0) {
            System.out.println(nombre + " lanza un hacha de tamaño " + tamanoHacha + "!");
            numeroHachas--;
        } else {
            System.out.println(nombre + " no tiene más hachas.");
        }
    }

    public void evolucionar() {
        if (nivelEvolucion < 4) {
            nivelEvolucion++;
            numeroHachas += 1; 
            tamanoHacha += 0.5; 
            System.out.println(nombre + " ha evolucionado a nivel " + nivelEvolucion + "!");
        } else {
            System.out.println(nombre + " ya ha alcanzado el nivel máximo de evolución.");
        }
    }
}
