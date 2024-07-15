
import javax.swing.*;

public static void main() {
        JFrame frame = new JFrame("Pixel Painter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new PixelPainter());
        frame.pack();
        frame.setVisible(true);

    }

