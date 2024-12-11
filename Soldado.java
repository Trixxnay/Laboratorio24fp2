public abstract class Soldado {
    protected String nombre;
    protected int nivelVida;
    protected int ataque;
    protected int defensa;
    protected int fila;
    protected int columna;
    protected int numEjercito;

    public Soldado(String nombre, int nivelVida, int ataque, int defensa, int fila, int columna) {
        this.nombre = nombre;
        this.nivelVida = Math.max(1, nivelVida); // Validación para evitar valores negativos o cero
        this.ataque = Math.max(0, ataque);
        this.defensa = Math.max(0, defensa);
        this.fila = fila;
        this.columna = columna;
    }

    public void mover(int nuevaFila, int nuevaColumna) {
        this.fila = nuevaFila;
        this.columna = nuevaColumna;
    }

    public void recibirAtaque(int dano) {
        this.nivelVida -= Math.max(0, dano - defensa);
        this.nivelVida = Math.max(0, nivelVida); // Asegura que la vida no sea negativa
    }

    public abstract void accionEspecial();

    @Override
    public String toString() {
        return String.format("%s [Vida: %d, Ataque: %d, Defensa: %d, Pos: (%d,%d), Ejército: %d]", 
                              nombre, nivelVida, ataque, defensa, fila, columna, numEjercito);
    }

    public String getNombre() {
        return nombre;
    }

    public int getNivelVida() {
        return nivelVida;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public int getNumEjercito() {
        return numEjercito;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getDefensa() {
        return defensa;
    }
}