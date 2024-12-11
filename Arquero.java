public class Arquero extends Soldado {
    private int flechasDisponibles;

    public Arquero(String nombre, int nivelVida, int ataque, int defensa, int fila, int columna, int flechasDisponibles) {
        super(nombre, nivelVida, ataque, defensa, fila, columna);
        this.flechasDisponibles = flechasDisponibles;
    }

    @Override
    public void accionEspecial() {
        if (flechasDisponibles > 0) {
            System.out.println(nombre + " dispara una flecha, aumentando su ataque temporalmente.");
            this.ataque += 5;
            flechasDisponibles--;
        } else {
            System.out.println(nombre + " no tiene flechas disponibles.");
        }
    }
}
