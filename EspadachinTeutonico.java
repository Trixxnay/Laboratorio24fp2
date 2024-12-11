public class EspadachinTeutonico extends Espadachin {
    private int numeroJabalinas;
    private double tamanoJabalina;
    private int nivelEvolucion;
    private boolean modoTortuga;

    public EspadachinTeutonico(String nombre, int nivelVida, int ataque, int defensa, int fila, int columna) {
        super(nombre, nivelVida, ataque, defensa, fila, columna);
        this.numeroJabalinas = 4; 
        this.tamanoJabalina = 1.5; 
        this.nivelEvolucion = 1;
        this.modoTortuga = false;
    }

    public void lanzarJabalina() {
        if (numeroJabalinas > 0) {
            System.out.println(nombre + " lanza una jabalina de tamaño " + tamanoJabalina + "!");
            numeroJabalinas--;
        } else {
            System.out.println(nombre + " no tiene más jabalinas.");
        }
    }

    public void activarModoTortuga() {
        this.modoTortuga = true;
        this.defensa += 3; 
        System.out.println(nombre + " activa el modo tortuga, aumentando su defensa.");
    }

    public void desactivarModoTortuga() {
        this.modoTortuga = false;
        this.defensa -= 3; 
        System.out.println(nombre + " desactiva el modo tortuga.");
    }

    public void evolucionar() {
        if (nivelEvolucion < 4) {
            nivelEvolucion++;
            numeroJabalinas += 2;
            tamanoJabalina += 0.5;
            System.out.println(nombre + " ha evolucionado a nivel " + nivelEvolucion + "!");
        } else {
            System.out.println(nombre + " ya ha alcanzado el nivel máximo de evolución.");
        }
    }

    public boolean isModoTortuga() {
        return modoTortuga;
    }
}

