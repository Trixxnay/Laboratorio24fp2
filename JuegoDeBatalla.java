import java.util.*;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.io.*;
public class JuegoDeBatalla implements Serializable {
    private static final long serialVersionUID = 1L;
    private final Ejercito ejercito1;
    private final Ejercito ejercito2;
    private final Mapa mapa;
    private final Scanner scanner;
    private final List<String> registroTurnos = new ArrayList<>();


    public JuegoDeBatalla(Mapa mapa, Ejercito ejercito1, Ejercito ejercito2) {
        this.mapa = mapa;
        this.ejercito1 = ejercito1;
        this.ejercito2 = ejercito2;
        this.scanner = new Scanner(System.in);
    }

    public void mostrarPosicionesSoldados(Ejercito ejercitoOponente) {
        System.out.println("Posiciones de los soldados del ejército oponente:");
        for (Soldado soldado : ejercitoOponente.getSoldados()) {
            System.out.printf("%s está en la posición (%d, %d)%n",
                              soldado.getNombre(), soldado.getFila(), soldado.getColumna());
        }
    }
    
    public void iniciar() {
        System.out.println("¡Bienvenidos al campo de batalla!");
        boolean juegoActivo = true;
        boolean turnoJugador1 = true;
    
        while (juegoActivo) {
            mapa.mostrarMapa(ejercito1, ejercito2);
            
            if (ejercito1.getSoldados().isEmpty()) {
                mostrarResultadoFinal("Jugador 2", ejercito1, ejercito2);
            } else if (ejercito2.getSoldados().isEmpty()) {
                mostrarResultadoFinal("Jugador 1", ejercito1, ejercito2);
            }
            
            System.out.println("\nTurno del jugador " + (turnoJugador1 ? "1" : "2"));
    
            Ejercito ejercitoActual = turnoJugador1 ? ejercito1 : ejercito2;
            Ejercito ejercitoOponente = turnoJugador1 ? ejercito2 : ejercito1;
    
            if (!ejercitoActual.isEvolucionRealizada()) {
                ejercitoActual.intentarEvolucionar();
            }
    
            System.out.println("\nSoldados disponibles:");
            for (int i = 0; i < ejercitoActual.getSoldados().size(); i++) {
                System.out.println((i + 1) + ". " + ejercitoActual.getSoldados().get(i));
            }
    
            System.out.print("Selecciona el soldado a mover (número): ");
            int seleccion = scanner.nextInt() - 1;
    
            if (seleccion < 0 || seleccion >= ejercitoActual.getSoldados().size()) {
                System.out.println("Selección inválida. Pierdes tu turno.");
                turnoJugador1 = !turnoJugador1;
                continue;
            }
    
            Soldado soldadoSeleccionado = ejercitoActual.getSoldados().get(seleccion);
    
            mostrarPosicionesSoldados(ejercitoOponente);
    
            System.out.print("Ingresa la nueva fila: ");
            int nuevaFila = scanner.nextInt();
            System.out.print("Ingresa la nueva columna: ");
            int nuevaColumna = scanner.nextInt();
    
            if (mapa.esMovimientoValido(soldadoSeleccionado, nuevaFila, nuevaColumna)) {
                registrarTurno("Turno del jugador " + (turnoJugador1 ? "1" : "2") + ": "
                    + soldadoSeleccionado.getNombre() + " se movió a (" + nuevaFila + ", " + nuevaColumna + ")");
                Soldado oponente = mapa.obtenerSoldadoEnPosicion(nuevaFila, nuevaColumna);
                if (oponente != null) {
                    System.out.println("¡Batalla entre " + soldadoSeleccionado.getNombre() + " y " + oponente.getNombre() + "!");
                    Soldado ganador = Batalla.enfrentar(soldadoSeleccionado, oponente);
                    if (ganador == soldadoSeleccionado) {
                        mapa.limpiarPosicion(oponente.getFila(), oponente.getColumna());
                        ejercitoOponente.getSoldados().remove(oponente);
                    } else {
                        mapa.limpiarPosicion(soldadoSeleccionado.getFila(), soldadoSeleccionado.getColumna());
                        ejercitoActual.getSoldados().remove(soldadoSeleccionado);
                    }
                } else {
                    mapa.moverSoldado(soldadoSeleccionado, nuevaFila, nuevaColumna);
                }
            } else {
                System.out.println("Movimiento inválido. Pierdes tu turno.");
            }
            ObjectOutputStream fileOut;
            DataOutputStream dataOut;
            try{
                System.out.println("Desea guardar partida? (y/n)");
                char guardar=scanner.next().toUpperCase().charAt(0);
                if (guardar == 'Y'){
                    dataOut=new DataOutputStream(new FileOutputStream("Guardar partida"));
                    dataOut.writeBoolean(true);
                    dataOut.close();
                    fileOut=new ObjectOutputStream(new FileOutputStream("Ejercito 1"));
                    for (int i=0;i<ejercito1.getSoldados().size();i++){
                        fileOut.writeObject(ejercito1.getSoldados().get(i));
                    }
                    fileOut.close();
                    fileOut=new ObjectOutputStream(new FileOutputStream("Ejercito 2"));
                    for (int i=0;i<ejercito2.getSoldados().size();i++){
                        fileOut.writeObject(ejercito2.getSoldados().get(i));
                    }
                    fileOut.close();
                    fileOut=new ObjectOutputStream(new FileOutputStream("Mapa"));
                    fileOut.writeObject(mapa);
                    fileOut.close();
                }
                
            } catch (IOException e){
                System.out.println("Error: " + e.getMessage());
            }
            
            if (ejercito1.getSoldados().isEmpty()) {
                mostrarResultadoFinal("Jugador 2", ejercito1, ejercito2);
            } else if (ejercito2.getSoldados().isEmpty()) {
                mostrarResultadoFinal("Jugador 1", ejercito1, ejercito2);
            }
            turnoJugador1=!turnoJugador1;
        }
        System.out.println("Juego terminado.");
    } 

    private void mostrarResultadoFinal(String ganador, Ejercito ejercito1, Ejercito ejercito2) {
        JFrame frame = new JFrame("Resultado Final de la Batalla");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        JLabel labelGanador = new JLabel("¡El ganador es: " + ganador + "!", SwingConstants.CENTER);
        labelGanador.setFont(new Font("Arial", Font.BOLD, 20));
        labelGanador.setForeground(Color.BLUE);
        frame.add(labelGanador, BorderLayout.NORTH);

        JPanel panelCentral = new JPanel(new BorderLayout());

        JPanel panelDetalles = new JPanel();
        panelDetalles.setLayout(new GridLayout(1, 2));

        JTextArea detallesEjercito1 = new JTextArea();
        detallesEjercito1.setText("Ejército de " + ejercito1.getNombreReino() + ":\n");
        for (Soldado soldado : ejercito1.getSoldados()) {
            detallesEjercito1.append(soldado.toString() + "\n");
        }
        detallesEjercito1.setEditable(false);
        panelDetalles.add(new JScrollPane(detallesEjercito1));

        JTextArea detallesEjercito2 = new JTextArea();
        detallesEjercito2.setText("Ejército de " + ejercito2.getNombreReino() + ":\n");
        for (Soldado soldado : ejercito2.getSoldados()) {
            detallesEjercito2.append(soldado.toString() + "\n");
        }
        detallesEjercito2.setEditable(false);
        panelDetalles.add(new JScrollPane(detallesEjercito2));

        panelCentral.add(panelDetalles, BorderLayout.CENTER);

        JPanel panelOpciones = new JPanel(new GridLayout(2, 1));

        JButton botonDescargarTodo = new JButton("Descargar Todos los Archivos");
        botonDescargarTodo.addActionListener(e -> {
            try {
                // Guardar Turnos
                try (FileWriter writer = new FileWriter("registro_turnos.txt")) {
                    for (String turno : registroTurnos) {
                        writer.write(turno + "\n");
                    }
                    System.out.println("Registro de turnos guardado en registro_turnos.txt.");
                }

                // Guardar Historia del Vencedor
                String historiaVencedor = "El ejército de " + ganador + " se alzó con la victoria, ganando la gloria y el respeto de todos los reinos.\n" +
                        "Los soldados fueron recibidos como héroes, y el reino floreció bajo el liderazgo de sus valientes combatientes.\n" +
                        "La victoria marcó el inicio de una era de prosperidad, consolidando su posición como el poder dominante.";
                try (FileOutputStream fos = new FileOutputStream("historia_vencedor.bin")) {
                    fos.write(historiaVencedor.getBytes());
                    System.out.println("Historia del vencedor guardada en historia_vencedor.bin.");
                }

                // Guardar Mapa Final
                mapa.guardarMapa("mapa_final.obj");
                System.out.println("Mapa final guardado en mapa_final.obj.");

                JOptionPane.showMessageDialog(frame, "Todos los archivos se han descargado exitosamente:" +
                        "- registro_turnos.txt\n" +
                        "- historia_vencedor.bin\n" +
                        "- mapa_final.obj");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(frame, "Error al generar los archivos: " + ex.getMessage());
            }
        });

        JPanel panelSeleccion = new JPanel(new FlowLayout());
        panelSeleccion.add(botonDescargarTodo);

        panelOpciones.add(panelSeleccion);

        frame.add(panelOpciones, BorderLayout.SOUTH);
        frame.setVisible(true);
    }


    public void registrarTurno(String mensaje) {
        registroTurnos.add(mensaje);
        try (FileWriter writer = new FileWriter("registro_turnos.txt", true)) {
            writer.write(mensaje + "\n");
        } catch (IOException e) {
            System.out.println("Error al registrar el turno: " + e.getMessage());
        }
    }

    public void guardarHistoriaVencedor(String rutaArchivo, String historia) {
        try (FileOutputStream fos = new FileOutputStream(rutaArchivo)) {
            fos.write(historia.getBytes());
            System.out.println("Historia del vencedor guardada exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar la historia del vencedor: " + e.getMessage());
        }
    }

    public void finalizarJuego(String ganador) {
        String historiaVencedor = "El ejército de " + ganador + " se alzó con la victoria, ganando la gloria y el respeto de todos los reinos.\n" +
            "Los soldados fueron recibidos como héroes, y el reino floreció bajo el liderazgo de sus valientes combatientes.\n" +
            "La victoria marcó el inicio de una era de prosperidad, consolidando su posición como el poder dominante.";

        guardarHistoriaVencedor("historia_vencedor.bin", historiaVencedor);
        mapa.guardarMapa("mapa_final.obj");
    }
    
}
