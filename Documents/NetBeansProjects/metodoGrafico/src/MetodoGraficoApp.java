

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MetodoGraficoApp extends JFrame {
    private JTextField coeficienteXField;
    private JTextField coeficienteYField;
    private JComboBox<String> objetivoComboBox;
    private List<InecuacionPanel> inecuacionPanels;
    private JButton graficarButton;

    public MetodoGraficoApp() {
        setTitle("Método Gráfico - Programación Lineal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);//tamaño de la ventana
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());

        // Panel para la función objetivo
        JPanel objetivoPanel = new JPanel(new FlowLayout());
        objetivoPanel.add(new JLabel("Función Objetivo:"));
        coeficienteXField = new JTextField();
        coeficienteXField.setColumns(3);
        objetivoPanel.add(coeficienteXField);
        objetivoPanel.add(new JLabel("X + "));
        
        coeficienteYField = new JTextField();
        coeficienteYField.setColumns(3);
        objetivoPanel.add(coeficienteYField);
        objetivoPanel.add(new JLabel("Y"));
        
        objetivoComboBox = new JComboBox<>(new String[]{"Maximización", "Minimización"});
        objetivoPanel.add(objetivoComboBox);
        mainPanel.add(objetivoPanel, BorderLayout.NORTH);

        // Panel para las inecuaciones
        JPanel inecuacionesPanel = new JPanel();
        inecuacionesPanel.setLayout(new BoxLayout(inecuacionesPanel, BoxLayout.Y_AXIS));

        inecuacionPanels = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            InecuacionPanel inecuacionPanel = new InecuacionPanel();
            inecuacionesPanel.add(inecuacionPanel);
            inecuacionPanels.add(inecuacionPanel);
        }

        mainPanel.add(inecuacionesPanel, BorderLayout.CENTER);

        // Botón Graficar
        graficarButton = new JButton("Graficar");
        mainPanel.add(graficarButton, BorderLayout.SOUTH);

        graficarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                graficar();
            }
        });

        add(mainPanel);
    }

    private void graficar() {
        // Obtener los coeficientes y el objetivo seleccionado de la función objetivo
        double coeficienteX = Double.parseDouble(coeficienteXField.getText());
        double coeficienteY = Double.parseDouble(coeficienteYField.getText());
        String objetivo = objetivoComboBox.getSelectedItem().toString();

        // Crear la lista de inecuaciones
        List<Inecuacion> inecuaciones = new ArrayList<>();
        for (InecuacionPanel inecuacionPanel : inecuacionPanels) {
            double coeficienteA = Double.parseDouble(inecuacionPanel.getCoeficienteAField().getText());
            double coeficienteB = Double.parseDouble(inecuacionPanel.getCoeficienteBField().getText());
            String sentido = inecuacionPanel.getSentidoComboBox().getSelectedItem().toString();
            double valorObjetivo = Double.parseDouble(inecuacionPanel.getValorObjetivoField().getText());

            Inecuacion inecuacion = new Inecuacion(coeficienteA, coeficienteB, sentido, valorObjetivo);
            inecuaciones.add(inecuacion);
        }

        // Crear y mostrar la ventana de gráficos
        Grafico grafico = new Grafico(coeficienteX, coeficienteY, objetivo, inecuaciones);
        grafico.setVisible(true);
    }
}
