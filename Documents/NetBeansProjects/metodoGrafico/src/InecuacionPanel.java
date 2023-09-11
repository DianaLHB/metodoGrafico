

import javax.swing.*;
import java.awt.*;
import javax.swing.*;
import java.awt.*;

public class InecuacionPanel extends JPanel {
    private JTextField coeficienteAField;
    private JTextField coeficienteBField;
    private JComboBox<String> sentidoComboBox;
    private JTextField valorObjetivoField;

    public InecuacionPanel() {
        setLayout(new GridLayout(1, 4));

        coeficienteAField = new JTextField("0");
        coeficienteBField = new JTextField("0");
        sentidoComboBox = new JComboBox<>(new String[]{">", ">=", "<", "<=", "="});
        valorObjetivoField = new JTextField("0");

        add(coeficienteAField);
        add(new JLabel("X + "));
        add(coeficienteBField);
        add(new JLabel("Y "));

        add(sentidoComboBox);
        add(new JLabel(" "));
        add(new JLabel(" "));
        add(new JLabel("Valor Objetivo: "));
        add(valorObjetivoField);
    }

    public JTextField getCoeficienteAField() {
        return coeficienteAField;
    }

    public JTextField getCoeficienteBField() {
        return coeficienteBField;
    }

    public JComboBox<String> getSentidoComboBox() {
        return sentidoComboBox;
    }

    public JTextField getValorObjetivoField() {
        return valorObjetivoField;
    }
}