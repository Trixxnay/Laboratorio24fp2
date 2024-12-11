import javax.swing.*;
import java.awt.*;

public class ResultadoFinalSwing {
    public static void mostrarResultado(String ganador, String reinoGanador) {
        JFrame frame = new JFrame("Resultado Final");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        JLabel labelTitulo = new JLabel("¡El ganador es: " + ganador + "!", SwingConstants.CENTER);
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(labelTitulo, BorderLayout.NORTH);

        JLabel labelReino = new JLabel("Reino ganador: " + reinoGanador, SwingConstants.CENTER);
        labelReino.setFont(new Font("Arial", Font.PLAIN, 16));
        frame.add(labelReino, BorderLayout.CENTER);

        JButton botonCerrar = new JButton("Cerrar");
        botonCerrar.addActionListener(e -> frame.dispose());
        frame.add(botonCerrar, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}