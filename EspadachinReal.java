public class EspadachinReal extends Espadachin {
    private int numeroCuchillos;
    private double tamanoCuchillos;
    private int nivelEvolucion;

    // Constructor que inicializa los atributos del Espadachín Real
    public EspadachinReal(String nombre, int nivelVida, int ataque, int defensa, int fila, int columna) {
        super(nombre, nivelVida, ataque, defensa, fila, columna);
        this.numeroCuchillos = 5;  // Inicializa con 5 cuchillos
        this.tamanoCuchillos = 1.0;  // Tamaño inicial del cuchillo
        this.nivelEvolucion = 1;  // Nivel de evolución inicial
    }

    // Método para lanzar un cuchillo, verificando que haya cuchillos disponibles
    public void lanzarCuchillo() {
        if (numeroCuchillos > 0) {
            System.out.println(nombre + " lanza un cuchillo de tamaño " + tamanoCuchillos + "!");
            numeroCuchillos--;  // Disminuye el número de cuchillos disponibles
        } else {
            System.out.println(nombre + " no tiene más cuchillos.");
        }
    }

    // Método de evolución que aumenta el número y tamaño de cuchillos con cada nivel
    public void evolucionar() {
        if (nivelEvolucion < 4) {  // Verifica si el Espadachín puede evolucionar
            nivelEvolucion++;  // Aumenta el nivel de evolución
            numeroCuchillos += 2;  // Aumenta el número de cuchillos disponibles
            tamanoCuchillos += 0.5;  // Aumenta el tamaño del cuchillo
            System.out.println(nombre + " ha evolucionado a nivel " + nivelEvolucion + "!");
        } else {
            System.out.println(nombre + " ya ha alcanzado el nivel máximo de evolución.");
        }
    }

    // Getters y Setters adicionales (si se necesitan en el futuro)
    public int getNumeroCuchillos() {
        return numeroCuchillos;
    }

    public void setNumeroCuchillos(int numeroCuchillos) {
        if (numeroCuchillos >= 0) {  // Verificación para evitar un valor negativo
            this.numeroCuchillos = numeroCuchillos;
        } else {
            System.out.println("El número de cuchillos no puede ser negativo.");
        }
    }

    public double getTamanoCuchillos() {
        return tamanoCuchillos;
    }

    public void setTamanoCuchillos(double tamanoCuchillos) {
        if (tamanoCuchillos > 0) {  // Verificación para evitar un tamaño no válido
            this.tamanoCuchillos = tamanoCuchillos;
        } else {
            System.out.println("El tamaño del cuchillo debe ser positivo.");
        }
    }
}
