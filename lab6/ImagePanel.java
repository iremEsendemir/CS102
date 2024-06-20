import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

public class ImagePanel extends JPanel{
    int currentImageIndex;
    BufferedImage[] imageArr;
    BufferedImage[] originalArr;
    int[] iterationCount;
    Timer sortingTimer;
    ArrayList<ArrayList<int[]>> pixels;
    public ImagePanel(BufferedImage[] images, BufferedImage[] originals){
        iterationCount = new int[3];
        Arrays.fill(iterationCount, 0);
        imageArr = images;
        originalArr = originals;
        currentImageIndex = 0;
        sortingTimer = new Timer(1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                swapOnce();
                repaint();
                if (isSortingComplete()) {
                    sortingTimer.stop();
                    repaint();
                }
            }
        });
        sortingTimer.start();
    }

    void switchToNextImage() {
        currentImageIndex = (currentImageIndex + 1) % imageArr.length;
        sortingTimer.start();
    }

    void switchToPreviousImage() {
        currentImageIndex = (currentImageIndex - 1 + imageArr.length) % imageArr.length;
        sortingTimer.start();
    }

    private boolean isSortingComplete() {
        return iterationCount[currentImageIndex] >= imageArr[currentImageIndex].getHeight() +
                imageArr[currentImageIndex].getWidth() - 1;
    }


    protected void paintComponent(Graphics g) {
        BufferedImage currentImage = imageArr[currentImageIndex];

        int panelWidth = getWidth();
        int panelHeight = getHeight();

        if (panelWidth == 0 || panelHeight == 0) {
            return;
        }

        double widthRatio = (double) panelWidth / currentImage.getWidth();
        double heightRatio = (double) panelHeight / currentImage.getHeight();

        double resizeFactor = Math.min(widthRatio, heightRatio);

        int newWidth = (int) (resizeFactor * currentImage.getWidth());
        int newHeight = (int) (resizeFactor * currentImage.getHeight());

        g.drawImage(currentImage,0,0,newWidth,newHeight,this);
    }

    

    public ArrayList<ArrayList<int[]>> makeDiagonalChain() {
        BufferedImage image = imageArr[currentImageIndex];
        ArrayList<ArrayList<int[]>> diagonalChains = new ArrayList<>();


        int width = image.getWidth();
        int height = image.getHeight();

        // sağ üstü yapıyor
        for (int col = 0; col < width; col++) {
            ArrayList<int[]> diagonal = new ArrayList<>();
            for (int x = col, y = 0; x < width && y < height; x++, y++) {
                int[] coordinates = {x, y};
                diagonal.add(coordinates);
            }
            diagonalChains.add(diagonal);
        }

        // sol altı yapıyor
        for (int row = 1; row < height; row++) {
            ArrayList<int[]> diagonal = new ArrayList<>();
            for (int x = 0, y = row; x < width && y < height; x++, y++) {
                int[] coordinates = {x, y};
                diagonal.add(coordinates);
            }
            diagonalChains.add(diagonal);
        }

        return diagonalChains;
    }

    public void swapOnce() {
        pixels = makeDiagonalChain();
        for (int i = 0; i < pixels.size(); i++) {
            int minIndex = iterationCount[currentImageIndex];
            for (int j = iterationCount[currentImageIndex]; j < pixels.get(i).size(); j++) {
                if (brightnessPixel(pixels.get(i).get(j)) > brightnessPixel(pixels.get(i).get(minIndex))) {
                    minIndex = j;
                }
            }
            if (minIndex != iterationCount[currentImageIndex]) {
                swapPixels(i, iterationCount[currentImageIndex], minIndex);
            }
        }
        iterationCount[currentImageIndex]++;
    }
    
    private int brightnessPixel(int[] coordinates) {
        Color color = new Color(imageArr[currentImageIndex].getRGB(coordinates[0], coordinates[1]));
        return (int) (0.2126 * color.getRed() + 0.7152 * color.getGreen() + 0.0722 * color.getBlue());
    }
    
    private void swapPixels(int chainIndex, int index1, int index2) {
        int[] c1 = pixels.get(chainIndex).get(index1);
        int[] c2 = pixels.get(chainIndex).get(index2);
    
        int rgb1 = imageArr[currentImageIndex].getRGB(c1[0], c1[1]);
        int rgb2 = imageArr[currentImageIndex].getRGB(c2[0], c2[1]);
    
        imageArr[currentImageIndex].setRGB(c1[0], c1[1], rgb2);
        imageArr[currentImageIndex].setRGB(c2[0], c2[1], rgb1);
    }

    public void resetImage() {
        BufferedImage copy = new BufferedImage(originalArr[currentImageIndex].getWidth(), originalArr[currentImageIndex].getHeight(), originalArr[currentImageIndex].getType());
        Graphics2D g2d = copy.createGraphics();
        g2d.drawImage(originalArr[currentImageIndex], 0, 0, null);
        g2d.dispose();
        imageArr[currentImageIndex] = copy;
       iterationCount[currentImageIndex] = 0;
    }
}