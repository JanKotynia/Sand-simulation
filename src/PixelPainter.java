import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PixelPainter extends JPanel {
    private static final int WIDTH = 200;
    private static final int HEIGHT = 400;
    private boolean[][] pixels;

    public PixelPainter() {
        pixels = new boolean[WIDTH][HEIGHT];
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);

    MouseAdapter mouseAdapter = new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
            drawPixel(e.getX(), e.getY());
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            drawPixel(e.getX(), e.getY());
        }

        private void drawPixel(int x, int y) {
            if (x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT) {
                for(int i=6;i>0;i--)
                {
                        if(x+i < WIDTH && y+i < HEIGHT)
                            pixels[x+i][y+i]=true;
                }
                repaint();
            }
        }
    };

    addMouseListener(mouseAdapter);
    addMouseMotionListener(mouseAdapter);

    Timer timer = new Timer(8, e -> {
        Physics();
        repaint();
    });
        timer.start();
}

    private void Physics() {
        for (int i = WIDTH - 1; i > 0; i--) {
            for (int j = HEIGHT - 2; j > 0; j--) {
                if (pixels[i][j] && !pixels[i][j + 1]) {
                    pixels[i][j] = false;
                    pixels[i][j + 1] = true;
                }
            }
        }
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                if (pixels[x][y]) {
                    g.setColor(Color.WHITE);
                    g.drawLine(x, y, x, y);
                }
            }
        }
    }
}
