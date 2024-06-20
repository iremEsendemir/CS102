import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
/**
 * Controller
 */
public class Controller {
    protected SettingsFrame settingsFrame;
    protected ToolsFrame toolsFrame;
    protected PaintFrame paintFrame;
    protected BufferedImage image;
    protected int currentTool;
    public Controller() {
        settingsFrame = new SettingsFrame(this);
        toolsFrame = new ToolsFrame(this);
        paintFrame = new PaintFrame(this);
        currentTool = 0;
        
    }
    public void hideSettingsFrame(int width, int height) {
        settingsFrame.setVisible (false);
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        paintFrame.image = image;
        paintFrame.setSize(width, height);
        paintFrame.setTitle("PaintFrame"); 
        paintFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        paintFrame.setVisible (true);
        paintFrame.setLocation(175,0);
        toolsFrame.setSize(170,height);
        toolsFrame.setTitle("Tool Frame"); 
        toolsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        toolsFrame.setVisible (true);
    }

    public void handleClearButtonAction() {
        currentTool = -1;
        for (int i = 0; i < paintFrame.getWidth(); i++) {
            for (int j = 0; j < paintFrame.getHeight(); j++) {
                image.setRGB(i, j, (Color.WHITE).getRGB());
            }
        }
        paintFrame.repaint();
    }

    public void handlePenButtonAction(int x, int y) {
        currentTool = 0;
        int brushSize = paintFrame.brushSize;
        int startX = x - brushSize;
        int startY = y - brushSize;
        if(startX < 0){
            startX = 0;
        }
        if(startY < 0){
            startY = 0;
        }
        for(int i = 0; i < brushSize* 2 + 1; i++){
            for(int j = 0; j < brushSize* 2 + 1; j++){
                if(startX + i < image.getWidth() && startY + j < image.getHeight()){
                    image.setRGB(startX + i, startY + j, paintFrame.color.getRGB());
                } 
            }    
        }
        paintFrame.repaint();
    }

    public void handlePenSizeButtonAction() {
        currentTool = 1;
        String input = JOptionPane.showInputDialog(paintFrame, "Enter pen size:", "");
        if (input != null && !input.isEmpty()) {
            try {
                int penSize = Integer.parseInt(input);
                paintFrame.brushSize = penSize;
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(paintFrame, "Invalid input. Please enter a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void handleLaserButtonAction() {
        currentTool = 2;
    }

    public void handleColorButtonAction() {
        currentTool = 3;
        Color chosenColor = JColorChooser.showDialog(paintFrame, "Pick a Color!", Color.WHITE);
        if (chosenColor != null) {
            paintFrame.color = chosenColor;
        }
    }

    public void handleToleranceButtonAction() {
        currentTool = 4;
        String input = JOptionPane.showInputDialog(paintFrame, "Enter tolerance: ", "");
        if (input != null && !input.isEmpty()) {
            try {
                int tolerance = Integer.parseInt(input);
                paintFrame.tolerance = tolerance;
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(paintFrame, "Invalid input. Please enter a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public boolean isSimilar(int x1, int y1, int x2, int y2){
        return true;
    }

    public void laser(int x, int y) {
        Color startColor = new Color(image.getRGB(x, y));
        laserRecursion(x, y, startColor);
        paintFrame.repaint();
    }
    
    private void laserRecursion(int x, int y, Color startColor) {
        if (x >= 0 && x < paintFrame.image.getWidth() && y >= 0 && y < paintFrame.image.getHeight() &&
            isColorWithinTolerance(paintFrame.image.getRGB(x, y), startColor.getRGB())) {
            paintFrame.image.setRGB(x, y, paintFrame.color.getRGB());
            laserRecursion(x, y - 1, startColor);
            laserRecursion(x, y + 1, startColor);
        }
    }

    private boolean isColorWithinTolerance(int pixelColor, int targetColor) {
        Color color1 = new Color(pixelColor);
        Color color2 = new Color(targetColor);
        int tolerance = paintFrame.tolerance;
        int redDifference = Math.abs(color1.getRed() - color2.getRed());
        int greenDifference = Math.abs(color1.getGreen() - color2.getGreen());
        int blueDifference = Math.abs(color1.getBlue() - color2.getBlue());
        int average = (redDifference + greenDifference + blueDifference) / 3;
        return average <= tolerance;
    }
    
}