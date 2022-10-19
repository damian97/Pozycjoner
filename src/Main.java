import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class OknoApki extends JPanel {

    int positionXInt, positionYInt;
    String positionX, positionY;

    JTextField posXField = new JTextField();
    JTextField posYField = new JTextField();
    JTextField colorR = new JTextField();
    JTextField colorG = new JTextField();
    JTextField colorB = new JTextField();

    public OknoApki() {

    setLayout(null);

    JLabel posXLabel = new JLabel("X:");
    posXLabel.setBounds(10,30,25,25);
    add(posXLabel);


    posXField.setBounds(25,30,50,25);
    add(posXField);


    JLabel posYLabel = new JLabel("Y:");
    posYLabel.setBounds(105,30,25,25);
    add(posYLabel);


    posYField.setBounds(120,30,50,25);
    add(posYField);


    JButton copyButton = new JButton("Copy");
    copyButton.setBounds(55,75,75,30);
    add(copyButton);

    copyButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            String copyPosCursor = "X: " + positionX + ", Y: " + positionY;
            StringSelection stringSelection = new StringSelection(copyPosCursor);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);

        }
    });



            colorR.setBounds(30, 120, 50, 25);
            add(colorR);


            colorG.setBounds(90, 120, 50, 25);
            add(colorG);

            colorB.setBounds(150, 120, 50, 25);
            add(colorB);









    }

    public void refreshColors() {

        try {
            Robot robot = new Robot();
            Color color = robot.getPixelColor(positionXInt, positionYInt);
            String colorRed = String.valueOf(color.getRed());
            String colorGreen = String.valueOf(color.getGreen());
            String colorBlue = String.valueOf(color.getBlue());
            colorR.setText(colorRed);
            colorG.setText(colorGreen);
            colorB.setText(colorBlue);
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }

    }

    public void refresh() {

        PointerInfo pi = MouseInfo.getPointerInfo();
        Point p = pi.getLocation();

        positionXInt = (int) p.getX();
        positionYInt = (int) p.getY();
        positionX = String.valueOf(positionXInt);
        positionY = String.valueOf(positionYInt);

        posXField.setText(positionX);
        posYField.setText(positionY);

    }




    @Override
    public Dimension getPreferredSize() {
        return new Dimension(225,175);
    }
}




public class Main {
    public static void main(String[] args) throws InterruptedException {


        JFrame ramka = new JFrame("Pozycjoner");

        OknoApki oa = new OknoApki();

        ramka.add(oa);



        ramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ramka.setVisible(true);
        ramka.pack();

        while (true) {
            oa.refresh();
            oa.refreshColors();
            Thread.sleep(50);


        }
    }
}