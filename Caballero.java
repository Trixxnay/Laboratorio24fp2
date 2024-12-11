public class Caballero extends Soldado {
    private boolean montado;

    public Caballero(String nombre, int nivelVida, int ataque, int defensa, int fila, int columna, boolean montado) {
        super(nombre, nivelVida, ataque, defensa, fila, columna);

        // Verificar si el nombre es null o vacío
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del caballero no puede ser null o vacío.");
        }
        
        this.montado = montado;
    }

    public void alternarMontura() {
        this.montado = !montado;
        System.out.println(nombre + (montado ? " monta su caballo y se prepara para atacar." : " desmonta de su caballo y se pone en guardia."));
    }

    @Override
    public void accionEspecial() {
        if (montado) {
            System.out.println(nombre + " realiza una poderosa envestida desde su caballo.");
            this.ataque += 10;
        } else {
            System.out.println(nombre + " realiza un ataque doble con su espada.");
            this.ataque += 5;
        }
    }
}
