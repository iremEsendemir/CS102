import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class ImageFrame extends JFrame implements KeyListener{
    protected ImagePanel imagePanel;
    int currentImageIndex;
    BufferedImage[] imageArr;
    BufferedImage[] originalArr;
    public ImageFrame(){
        imageArr = new BufferedImage[3];
        originalArr = new BufferedImage[3];
        loadImage("p1.jpg", 0);
        loadImage("p2.jpg", 1);
        loadImage("p3.jpg", 2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        imagePanel = new ImagePanel(imageArr, originalArr);
        setLayout(new BorderLayout());
        add(imagePanel, BorderLayout.CENTER);
        pack();
        addKeyListener(this);
        setFocusable(true);
        setSize(600, 600);
    }

    public void loadImage(String fileName, int i){
        try {
            imageArr[i] = ImageIO.read(new File(fileName));
            originalArr[i] = ImageIO.read(new File(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void keyPressed(KeyEvent e) {
        // Handle key presses
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            imagePanel.switchToPreviousImage();
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            // Switch to the next image in a circular manner
            imagePanel.switchToNextImage();
            currentImageIndex = (currentImageIndex + 1) % imageArr.length;
        } else if (e.getKeyCode() == KeyEvent.VK_R) {
            imagePanel.resetImage();
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent arg0) {}

    @Override
    public void keyTyped(KeyEvent arg0) {}

}
