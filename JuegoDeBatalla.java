import java.util.*;
import javax.swing.*;
import java.awt.*;
public class JuegoDeBatalla {
    private final Ejercito ejercito1;
    private final Ejercito ejercito2;
    private final Mapa mapa;
    private final Scanner scanner;

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
    
            if (ejercito1.getSoldados().isEmpty()) {
                mostrarResultadoFinal("Jugador 2", ejercito1, ejercito2);
            } else if (ejercito2.getSoldados().isEmpty()) {
                mostrarResultadoFinal("Jugador 1", ejercito1, ejercito2);
            }
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
    
        ButtonGroup grupoOpciones = new ButtonGroup();
        JRadioButton opcion1 = new JRadioButton("Historia del Ejército Perdedor");
        JRadioButton opcion2 = new JRadioButton("Historia del Ejército Vencedor");
        grupoOpciones.add(opcion1);
        grupoOpciones.add(opcion2);
    
        opcion1.addActionListener(e -> {
            String perdedor = ganador.equals(ejercito1.getNombreReino()) ? ejercito2.getNombreReino() : ejercito1.getNombreReino();
            JOptionPane.showMessageDialog(frame, 
                "El ejército de " + perdedor + " sufrió una derrota devastadora. Los soldados, abatidos, regresaron a sus tierras con la carga de la derrota.\n"
                + "El reino ahora enfrenta tiempos difíciles, con luchas internas y un pueblo que clama por liderazgo en medio de la incertidumbre.\n"
                + "A pesar de ello, el ejército prometió fortalecerse y estar listo para futuras batallas.");
        });
    
        opcion2.addActionListener(e -> {
            String vencedor = ganador.equals(ejercito1.getNombreReino()) ? ejercito1.getNombreReino() : ejercito2.getNombreReino();
            JOptionPane.showMessageDialog(frame, 
                "El ejército de " + vencedor + " se alzó con la victoria, ganando la gloria y el respeto de todos los reinos.\n"
                + "Los soldados fueron recibidos como héroes, y el reino floreció bajo el liderazgo de sus valientes combatientes.\n"
                + "La victoria marcó el inicio de una era de prosperidad, consolidando su posición como el poder dominante.");
        });
    
        JPanel panelRadioButtons = new JPanel(new FlowLayout());
        panelRadioButtons.add(opcion1);
        panelRadioButtons.add(opcion2);
    
        panelOpciones.add(panelRadioButtons);
        panelCentral.add(panelOpciones, BorderLayout.SOUTH);
    
        frame.add(panelCentral, BorderLayout.CENTER);
    
        JComboBox<String> opcionesFinales = new JComboBox<>(new String[] {"Cerrar", "Historia" });
        JButton botonAccion = new JButton("Ejecutar");
        botonAccion.addActionListener(e -> {
            String seleccion = (String) opcionesFinales.getSelectedItem();
            switch (seleccion) {
                case "Cerrar" -> frame.dispose();
                case "Historia" -> JOptionPane.showMessageDialog(frame, 
                    "En un mundo donde las ambiciones de poder y gloria dominan los corazones de los gobernantes, "
                  + "los antiguos reinos han decidido enfrentar su destino en el campo de batalla.\n"
                  + "Desde las frías tierras de los Reinos Escandinavos, pasando por los férreos Reinos Anglo-Sajones y el noble Reino de Asturias,\n"
                  + "hasta los imponentes dominios del Imperio Bizantino, el Imperio Carolingio y el Reino de los Búlgaros, las tensiones han alcanzado un punto crítico.\n"
                  + "Cada reino busca consolidar su supremacía en un mundo dividido, utilizando estrategias militares, unidades especializadas y el dominio del territorio como sus principales armas.\n"
                  + "Las guerras han comenzado, y solo uno podrá reclamar la hegemonía sobre estas tierras conflictivas.\n\n"
                  + "El campo de batalla ardía con la intensidad de la lucha, un escenario donde el destino de los reinos colisionaba con una ferocidad inigualable.\n"
                  + "Las espadas resonaban al encontrarse, las lanzas perforaban el aire con precisión mortal, y el grito de los soldados se entremezclaba con el estruendo de los tambores de guerra.\n"
                  + "Las tierras, que una vez fueron símbolo de paz y prosperidad, ahora temblaban bajo el peso de la discordia.\n\n"
                  + "Desde el amanecer hasta el ocaso, los combatientes se enfrentaron en un duelo implacable, sus cuerpos agotados pero sus espíritus impulsados por un propósito que trascendía la supervivencia.\n"
                  + "No había lugar para la duda ni para el arrepentimiento; cada decisión, cada movimiento, era un eco de la resolución y el sacrificio que los había llevado hasta allí.\n"
                  + "El aire estaba cargado de cenizas y sudor, y el suelo, salpicado por el esfuerzo de quienes no cederían ante la derrota.\n\n"
                  + "Pero en medio del caos, surgieron momentos de asombrosa humanidad: un guerrero que ayudaba a un camarada herido, un líder que inspiraba a su gente a seguir adelante, "
                  + "y una determinación colectiva que se negaba a rendirse.\n"
                  + "La batalla no se trataba solo de victoria o derrota. Era un enfrentamiento que definía la esencia de cada reino, una lucha por preservar sus ideales, "
                  + "su historia y su lugar en un mundo que, tras este día, nunca volvería a ser el mismo.\n"
                  + "La guerra, implacable y ardiente, no dejaba testigos ilesos; solo sobrevivientes transformados por el fuego del conflicto."
                );
            }
        });
    
        JPanel panelInferior = new JPanel(new FlowLayout());
        panelInferior.add(opcionesFinales);
        panelInferior.add(botonAccion);
    
        frame.add(panelInferior, BorderLayout.SOUTH);
    
        frame.setVisible(true);
    }
}
