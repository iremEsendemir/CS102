import javax.swing.*;
import java.awt.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolsFrame extends JFrame{
    protected Controller controller;
    protected JButton clearButton;
    protected JButton penButton;
    protected JButton penSizeButton;
    protected JButton laserButton;
    protected JButton colorButton;
    protected JButton toleranceButton;
    public ToolsFrame(Controller controller){
        this.controller = controller;
        setLayout(new GridLayout(6,1));
        clearButton = new JButton("Clear");
        penButton = new JButton("Pen");
        penSizeButton = new JButton("Pen Size");
        laserButton = new JButton("Laser");
        colorButton = new JButton("Color");
        toleranceButton = new JButton("Tolerance");
        MyButtonListener l = new MyButtonListener();
        clearButton.addActionListener(l);
        penButton.addActionListener(l);
        penSizeButton.addActionListener(l);
        laserButton.addActionListener(l);
        colorButton.addActionListener(l);
        toleranceButton.addActionListener(l);
        add(clearButton);
        add(penButton);
        add(penSizeButton);
        add(laserButton);
        add(colorButton);
        add(toleranceButton);
    }
    public class MyButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == clearButton){
                controller.currentTool = -1;
                System.out.println("ghncb ");
                controller.handleClearButtonAction();
            }
            else if(e.getSource() == penButton){
                controller.currentTool = 0;
                controller.paintFrame.repaint();
                System.out.println("abcdef");
            }
            else if(e.getSource() == penSizeButton){
                controller.currentTool = 1;
                controller.handlePenSizeButtonAction();
            }
            else if(e.getSource() == laserButton){
                controller.currentTool = 2;
                controller.handleLaserButtonAction();
            }
            else if(e.getSource() == colorButton){
                controller.currentTool = 3;
                controller.handleColorButtonAction();
            }
            else if(e.getSource() == toleranceButton){
                controller.currentTool = 4;
                controller.handleToleranceButtonAction();
            }
        } 
    }
}
