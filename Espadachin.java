public class Espadachin extends Soldado {
    private double longitudEspada;

    // Constructor de Espadachin, inicializa los atributos del Soldado y la longitud de la espada
    public Espadachin(String nombre, int nivelVida, int ataque, int defensa, int fila, int columna) {
        super(nombre, nivelVida, ataque, defensa, fila, columna);
    }

    // Acción especial que aumenta la defensa del Espadachin
    @Override
    public void accionEspecial() {
        System.out.println(nombre + " crea un muro de escudos, aumentando su defensa en 3 puntos.");
        this.defensa += 3;
    }

    // Getter para obtener la longitud de la espada
    public double getLongitudEspada() {
        return longitudEspada;
    }

    // Setter para establecer la longitud de la espada con validación
    public void setLongitudEspada(double longitudEspada) {
        if (longitudEspada > 0) {  // Validación para asegurar que la longitud sea positiva
            this.longitudEspada = longitudEspada;
        } else {
            System.out.println("La longitud de la espada debe ser mayor que cero.");
        }
    }
}
