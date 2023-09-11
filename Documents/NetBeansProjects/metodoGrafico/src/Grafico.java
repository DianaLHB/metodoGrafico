//la presente clase contiene las instrucciones que devolvera el resultado de las lineas a graficar al introducir los datos de la funcion objeto y las restricciones
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Grafico extends JFrame {
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    private double coeficienteX;
    private double coeficienteY;
    private String objetivo;
    private List<Inecuacion> inecuaciones;

    private double xMin;
    private double xMax;
    private double yMin;
    private double yMax;
   
//esta seccion es acerca de los botones del grafico
    private JButton acercarEjeXButton;
    private JButton alejarEjeXButton;
    private JButton acercarEjeYButton;
    private JButton alejarEjeYButton;

    public Grafico(double coeficienteX, double coeficienteY, String objetivo, List<Inecuacion> inecuaciones) {
        this.coeficienteX = coeficienteX;
        this.coeficienteY = coeficienteY;
        this.objetivo = objetivo;
        this.inecuaciones = inecuaciones;

        this.xMin = -1;
        this.xMax = 10;
        this.yMin = -1;
        this.yMax = 10;

        setTitle("Gráfico - Método Gráfico");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);

        // Crear botones
        acercarEjeXButton = new JButton("Aumentar Eje X");
        alejarEjeXButton = new JButton("Reducir Eje X");
        acercarEjeYButton = new JButton("Aumentar Eje Y");
        alejarEjeYButton = new JButton("Reducir Eje Y");

        // Agregar listeners a los botones
        acercarEjeXButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                acercarEjeX();
                repaint();
            }
        });

        alejarEjeXButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alejarEjeX();
                repaint();
            }
        });

        acercarEjeYButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                acercarEjeY();
                repaint();
            }
        });

        alejarEjeYButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alejarEjeY();
                repaint();
            }
        });

        // Crear panel de botones y agregarlo a la parte superior de la ventana
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(acercarEjeXButton);
        buttonPanel.add(alejarEjeXButton);
        buttonPanel.add(acercarEjeYButton);
        buttonPanel.add(alejarEjeYButton);
        add(buttonPanel, BorderLayout.NORTH);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        int width = getWidth();
        int height = getHeight();
        int xCenter = width / 2;
        int yCenter = height / 2;

        // Calcular el factor de escalado para los ejes X e Y
        double divisiones=(xMax - xMin);
        double xScale = width / (divisiones);
        double yScale = height / (divisiones);
        int xEje=(int) Math.ceil(xScale);
        int yEje=(int) Math.ceil((divisiones-1)*yScale);
        // Dibujar los ejes cartesianos
        g.drawLine(0,yEje, width,yEje);
        g.drawLine(xEje, 0, xEje, height);

        // Dibujar las marcas de escala y la numeración en el eje X
        double xScaleIncrement = 2;
        double xScaleCount = (xMax - xMin) / xScaleIncrement;

        for (double i = -xScaleCount; i <= xScaleCount; i++) {
            int x = (int) ((xScale + i * xScaleIncrement * xScale) - xScale);
            g.drawLine(xEje + x,yEje - 5,xEje + x,yEje + 5);
            g.drawString(String.valueOf(i * xScaleIncrement), xEje + x - 3, yEje + 20);
        }

        // Dibujar las marcas de escala y la numeración en el eje Y
        double yScaleIncrement = 2;
        double yScaleCount = (yMax - yMin) / yScaleIncrement;

        for (double i = -yScaleCount; i <= yScaleCount; i++) {
            int y = (int) ((yEje + i * yScaleIncrement * yScale) - yEje);
            g.drawLine(xEje - 5, yEje + y, xEje + 5, yEje + y);
            g.drawString(String.valueOf(-i * yScaleIncrement), xEje - 25, yEje + y + 5);
        }

        // Dibujar las inecuaciones
        for (Inecuacion inecuacion : inecuaciones) {
            double coeficienteA = inecuacion.getCoeficienteA();
            double coeficienteB = inecuacion.getCoeficienteB();
            String sentido = inecuacion.getSentido();
            double valorObjetivo = inecuacion.getValorObjetivo();

            int x1, y1, x2, y2;

            if (coeficienteB != 0) {
                x1 = (int) ((xMin - (coeficienteA * xMax)) * xScale);
                y1 = (int) ((valorObjetivo / coeficienteB - yMin) * yScale);
                x2 = (int) ((xMax - coeficienteA * xMin) * xScale);
                y2 = (int) (((valorObjetivo - coeficienteA * xMax) / coeficienteB - yMin) * yScale);
            } else {
                x1 = (int) ((valorObjetivo / coeficienteA - xMin) * xScale);
                y1 = (int) ((yMin - coeficienteB * yMax) * yScale);
                x2 = (int) (((valorObjetivo - coeficienteB * yMax) / coeficienteA - xMin) * xScale);
                y2 = (int) ((yMax - coeficienteB * yMin) * yScale);
            }
            if(sentido.equals(">")||sentido.equals(">="))
            	g.setColor(Color.GREEN);
            else
            	g.setColor(Color.BLUE);
            g.drawLine(0 + x1, height - y1, 0 + x2, height - y2);
        }

        int x1 = (int) ((xMin - coeficienteX * xMax) * xScale);
        int y1 = (int) ((evaluarFuncionObjetivo(xMin) - yMin) * yScale);
        int x2 = (int) ((xMax - coeficienteX * xMin) * xScale);
        int y2 = (int) ((evaluarFuncionObjetivo(xMax) - yMin) * yScale);
        g.setColor(Color.RED);
        g.drawLine(0 + x1, height - y1, 0 + x2, height - y2);
    }

    private double evaluarFuncionObjetivo(double x) {
        return (coeficienteX * x + coeficienteY) / (-coeficienteY);
    }

    private void acercarEjeX() {
        int increment = 2;

        // Incrementar el rango de graficación en el eje X
        xMax += increment;

        // Limitar el rango de graficación máximo en el eje X
        xMin = Math.max(xMin, -1);
        xMax = Math.min(xMax, 50);
    }

    private void alejarEjeX() {
        int decrement = 2;

        // Reducir el rango de graficación en el eje X
        xMax -= decrement;

        // Limitar el rango de graficación mínimo en el eje X
        xMin = Math.min(xMin, -1);
        xMax = Math.max(xMax, 2);
    }

    private void acercarEjeY() {
        int increment = 2;

        // Incrementar el rango de graficación en el eje Y
        yMax += increment;

        // Limitar el rango de graficación máximo en el eje Y
        yMin = Math.max(yMin, -1);
        yMax = Math.min(yMax, 50);
    }

    private void alejarEjeY() {
        int decrement = 2;

        // Reducir el rango de graficación en el eje Y
        yMax -= decrement;

        // Limitar el rango de graficación mínimo en el eje Y
        yMin = Math.min(yMin, -2);
        yMax = Math.max(yMax, 2);
    }


}
