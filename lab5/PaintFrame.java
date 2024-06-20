import javax.swing.*;
import javax.swing.event.MouseInputListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;


public class PaintFrame extends JFrame{
    protected BufferedImage image;
    protected Controller controller;
    //public Point lastPoint;
    protected int brushSize;
    protected Color color;
    protected int tolerance;
    public PaintFrame(Controller controller){
        this.controller = controller;
        brushSize = 15;
        color = Color.RED;
        tolerance = 1;
        MyMouseListener listener = new MyMouseListener(); 
        addMouseMotionListener(listener);
        this.setVisible(true);
    }

    public void paint(Graphics g) {
        g.drawImage(image, 0, 0, null);
    }

    class MyMouseListener implements MouseInputListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            if(controller.currentTool == 0){
            controller.handlePenButtonAction(e.getX(),e.getY());
            repaint();
            }
            if(controller.currentTool == 2){
                controller.laser(e.getX(), e.getY());
            }
        }
        @Override
        public void mouseEntered(MouseEvent e) {}
        @Override
        public void mouseExited(MouseEvent e) {}
        @Override
        public void mousePressed(MouseEvent e) {}
        @Override
        public void mouseReleased(MouseEvent e) {}
        @Override
        public void mouseDragged(MouseEvent e) {
            if(controller.currentTool == 0){
            controller.handlePenButtonAction(e.getX(),e.getY());
            repaint();
            }
            if(controller.currentTool == 2){
                controller.laser(e.getX(), e.getY());
            }
        }
        @Override
        public void mouseMoved(MouseEvent e) {}
    }
}
