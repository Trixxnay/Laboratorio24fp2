import java.io.*;
public class Mapa implements Serializable {
    private static final long serialVersionUID = 1L;
    private final int filas;
    private final int columnas;
    private final Soldado[][] mapa;

    public Mapa(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.mapa = new Soldado[filas][columnas];
    }

    public boolean colocarSoldado(Soldado soldado) {
        if (esPosicionValida(soldado.getFila(), soldado.getColumna()) && mapa[soldado.getFila()][soldado.getColumna()] == null) {
            mapa[soldado.getFila()][soldado.getColumna()] = soldado;
            return true;
        }
        return false;
    }

    public boolean moverSoldado(Soldado soldado, int nuevaFila, int nuevaColumna) {
        if (esMovimientoValido(soldado, nuevaFila, nuevaColumna)) {
            limpiarPosicion(soldado.getFila(), soldado.getColumna());
            soldado.mover(nuevaFila, nuevaColumna);
            mapa[nuevaFila][nuevaColumna] = soldado;
            return true;
        }
        return false;
    }

    public void limpiarPosicion(int fila, int columna) {
        if (esPosicionValida(fila, columna)) {
            mapa[fila][columna] = null;
        }
    }

    public boolean esMovimientoValido(Soldado soldado, int nuevaFila, int nuevaColumna) {
        return esPosicionValida(nuevaFila, nuevaColumna) && (mapa[nuevaFila][nuevaColumna] == null || soldado.getNumEjercito() != mapa[nuevaFila][nuevaColumna].getNumEjercito());
    }

    public Soldado obtenerSoldadoEnPosicion(int fila, int columna) {
        return esPosicionValida(fila, columna) ? mapa[fila][columna] : null;
    }

    public boolean esPosicionValida(int fila, int columna) {
        return fila >= 0 && fila < filas && columna >= 0 && columna < columnas;
    }

    public void mostrarMapa(Ejercito ejercito1, Ejercito ejercito2) {
        System.out.println("Estado actual del mapa:");
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (mapa[i][j] == null) {
                    System.out.print("[     ] ");
                } else {
                    if (mapa[i][j].getNumEjercito() == 1){
                        String inicial = obtenerInicialReino(mapa[i][j], ejercito1);
                        System.out.print("[ " + inicial + " ] ");
                    } else if (mapa[i][j].getNumEjercito() == 2){
                        String inicial = obtenerInicialReino(mapa[i][j], ejercito2);
                        System.out.print("[ " + inicial + " ] ");
                    }
                }
            }
            System.out.println();
        }
    }
    
    private String obtenerInicialReino(Soldado soldado, Ejercito ejercito1) {
        String inicialEjercito;
        if (ejercito1.getSoldados().contains(soldado)) {
            inicialEjercito = ejercito1.getNombreReino().substring(0, 2);
        } else {
            inicialEjercito = ""; // Puedes asignar un valor predeterminado si no se cumple la condición
        }
        String tipoSoldado = soldado.getClass().getSimpleName().substring(0, 1); 
        if (ejercito1.getNombreReino().contains("Reino")){
            String nombreR=ejercito1.getNombreReino();
            int posicionEspacio = nombreR.lastIndexOf(" ");
        
        // Extraer la palabra después del último espacio
            String nombreReino = nombreR.substring(posicionEspacio + 1);
            nombreReino=nombreReino.substring(0, 2);
            return tipoSoldado + nombreReino;
        }
        return tipoSoldado + inicialEjercito; 
    }

    public void guardarMapa(String rutaArchivo) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(rutaArchivo))) {
            out.writeObject(this);
            System.out.println("Estado del mapa guardado exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar el mapa: " + e.getMessage());
        }
    }

    public static Mapa cargarMapa(String rutaArchivo) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(rutaArchivo))) {
            return (Mapa) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar el mapa: " + e.getMessage());
            return null;
        }
    }
}
