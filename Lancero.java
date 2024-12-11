public class Lancero extends Soldado {
    private double longitudLanza;

    // Constructor de Lancero, inicializa el nombre, vida, ataque, defensa, posición y longitud de la lanza
    public Lancero(String nombre, int nivelVida, int ataque, int defensa, int fila, int columna, double longitudLanza) {
        super(nombre, nivelVida, ataque, defensa, fila, columna);
        if (longitudLanza > 0) {  // Verificación para evitar longitud negativa
            this.longitudLanza = longitudLanza;
        } else {
            System.out.println("La longitud de la lanza no puede ser negativa o cero.");
            this.longitudLanza = 1.0;  // Establece un valor por defecto si la longitud es inválida
        }
    }

    // Acción especial que aumenta la defensa del Lancero
    @Override
    public void accionEspecial() {
        System.out.println(nombre + " forma un schiltrom, aumentando defensa en 5 puntos.");
        this.defensa += 5;
    }

    // Getter para obtener la longitud de la lanza
    public double getLongitudLanza() {
        return longitudLanza;
    }

    // Setter para modificar la longitud de la lanza con validación
    public void setLongitudLanza(double longitudLanza) {
        if (longitudLanza > 0) {  // Verificación para asegurar que la longitud sea positiva
            this.longitudLanza = longitudLanza;
        } else {
            System.out.println("La longitud de la lanza no puede ser negativa o cero.");
        }
    }
}
