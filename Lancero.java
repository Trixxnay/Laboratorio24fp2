public class Lancero extends Soldado {
    private double longitudLanza;

    public Lancero(String nombre, int nivelVida, int ataque, int defensa, int fila, int columna, double longitudLanza) {
        super(nombre, nivelVida, ataque, defensa, fila, columna);
        this.longitudLanza = longitudLanza;
    }

    @Override
    public void accionEspecial() {
        System.out.println(nombre + " forma un schiltrom, aumentando defensa en 5 puntos.");
        this.defensa += 5;
    }

    public double getLongitudLanza() {
        return longitudLanza;
    }
}