public class EspadachinConquistador extends Espadachin {
    private int numeroHachas;
    private double tamanoHacha;
    private int nivelEvolucion;

    // Constructor de EspadachinConquistador, inicializa los atributos heredados y los propios
    public EspadachinConquistador(String nombre, int nivelVida, int ataque, int defensa, int fila, int columna) {
        super(nombre, nivelVida, ataque, defensa, fila, columna);
        this.numeroHachas = 3;  // Inicializa el número de hachas
        this.tamanoHacha = 2.0;  // Tamaño de las hachas inicial
        this.nivelEvolucion = 1;  // Nivel de evolución inicial
    }

    // Método para lanzar un hacha, disminuyendo el número de hachas disponibles
    public void lanzarHacha() {
        if (numeroHachas > 0) {
            System.out.println(nombre + " lanza un hacha de tamaño " + tamanoHacha + "!");
            numeroHachas--;
        } else {
            System.out.println(nombre + " no tiene más hachas.");
        }
    }

    // Método para evolucionar al EspadachinConquistador, aumentando su número de hachas y el tamaño de las mismas
    public void evolucionar() {
        if (nivelEvolucion < 4) {  // Verifica si se puede evolucionar
            nivelEvolucion++;
            numeroHachas += 1;  // Aumenta el número de hachas
            tamanoHacha += 0.5;  // Aumenta el tamaño del hacha
            System.out.println(nombre + " ha evolucionado a nivel " + nivelEvolucion + "!");
        } else {
            System.out.println(nombre + " ya ha alcanzado el nivel máximo de evolución.");
        }
    }

    // Getter para obtener el número de hachas
    public int getNumeroHachas() {
        return numeroHachas;
    }

    // Setter para establecer el número de hachas con validación
    public void setNumeroHachas(int numeroHachas) {
        if (numeroHachas >= 0) {  // Verifica que el número de hachas no sea negativo
            this.numeroHachas = numeroHachas;
        } else {
            System.out.println("El número de hachas no puede ser negativo.");
        }
    }

    // Getter para obtener el tamaño del hacha
    public double getTamanoHacha() {
        return tamanoHacha;
    }

    // Setter para establecer el tamaño del hacha
    public void setTamanoHacha(double tamanoHacha) {
        if (tamanoHacha > 0) {  // Verifica que el tamaño del hacha sea positivo
            this.tamanoHacha = tamanoHacha;
        } else {
            System.out.println("El tamaño del hacha debe ser mayor que cero.");
        }
    }
}
