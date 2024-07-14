import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class PixelPainter extends JPanel {
    private static final int WIDTH = 200;
    private static final int HEIGHT = 400;
    public int increase =0;
    private int[][] pixels;

    public PixelPainter() {
        pixels = new int[WIDTH][HEIGHT];
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.WHITE);

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
            int r1;
            int r2;
            Random random = new Random();
            if (x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT) {
                for(int i=0;i<7;i++)
                {
                    r1 =random.nextInt((6 - (-6) + 1)) + (-6);
                    r2 =random.nextInt((6 - (-6) + 1)) + (-6);
                        if(x+r1 < WIDTH && y+r2 < HEIGHT)
                            pixels[x+r1][y+r2]=1;
                }
                repaint();
            }
        }
    };

    addMouseListener(mouseAdapter);
    addMouseMotionListener(mouseAdapter);

    Timer timer = new Timer(8, e -> {
        Physics();
        pilling();
        repaint();
    });
        timer.start();
}

    private void Physics() {

        for (int i = WIDTH - 2; i > 0; i--) {
            for (int j = HEIGHT - 2; j > 0; j--) {
                if (pixels[i][j] > 0 && pixels[i][j + 1] == 0) {
                    pixels[i][j] = 0;
                    pixels[i][j + 1] = 1;

                }
            }
        }

    }

    private void pilling()
    {
        Random random = new Random();
        int r;

        for (int i = WIDTH - 2; i > 0; i--) {
            for (int j = HEIGHT - 3; j > 0; j--) {
            if(pixels[i][j+2] > 0 && pixels[i][j] > 0)
            {
                if(pixels[i+1][j+2] == 0 && pixels[i-1][j+2]==0)
                {
                    r = random.nextInt(2) + 1;
                    pixels[i][j]=0;
                    if(r==1)
                    {
                        pixels[i+1][j+2]=1;
                    }
                    else
                    {
                        pixels[i-1][j+2]=1;
                    }
                }
                else if(pixels[i+1][j+2]==0)
                {
                 pixels[i][j]=0;
                 pixels[i+1][j+2]=1;
                }
                else if(pixels[i-1][j+2]==0)
                {
                    pixels[i][j]=0;
                    pixels[i-1][j+2]=1;
                }
            }
            }
        }
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                if (pixels[x][y]>0) {
                    g.setColor(new Color(0,0,0));
                    g.drawLine(x, y, x, y);
                }
            }
        }
    }

}
