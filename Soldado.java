public abstract class Soldado {
    protected String nombre;
    protected int nivelVida;
    protected int ataque;
    protected int defensa;
    protected int fila;
    protected int columna;
    protected int numEjercito;

    public Soldado(String nombre, int nivelVida, int ataque, int defensa, int fila, int columna) {
        this.numEjercito=Ejercito.getContEjercitos();
        this.nombre = nombre;
        this.nivelVida = nivelVida;
        this.ataque = ataque;
        this.defensa = defensa;
        this.fila = fila;
        this.columna = columna;
    }

    public void setNumEjercito(int numEjercito) {
        this.numEjercito = numEjercito;
    }
    
    public int getNumEjercito() {
        return numEjercito;
    }
    
    public abstract void accionEspecial(); 

    public void mover(int nuevaFila, int nuevaColumna) {
        this.fila = nuevaFila;
        this.columna = nuevaColumna;
    }

    public void recibirAtaque(int dano) {
        this.nivelVida -= Math.max(0, dano - defensa);
    }

    @Override
    public String toString() {
        return String.format("%s [Vida: %d, Ataque: %d, Defensa: %d, Pos: (%d,%d), NumEjercito: %d]", 
                              nombre, nivelVida, ataque, defensa, fila, columna,numEjercito);
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

    public void aumentarVida(int cantidad) {
        this.nivelVida += cantidad;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getDefensa() {
        return defensa;
    }   
}
