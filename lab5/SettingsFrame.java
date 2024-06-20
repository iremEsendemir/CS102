import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsFrame extends JFrame{
    protected JPanel inputPanel;
    protected Controller controller;
    protected JTextField widthField, heightField;
    protected JLabel heightLabel, widthLabel ;
    protected JButton continueButton;
    protected int width, height, toolNum;
    protected boolean missionCompleted;
    public static boolean isNumeric(String s) {
        int intValue;            
        if(s == null || s.equals("")) {
            return false;
        }
        try {
            intValue = Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public SettingsFrame(Controller controller){
        this.controller = controller;
        //layout??     
        inputPanel = new JPanel();
        widthField = new JTextField(20);
        heightField = new JTextField(20);
        //widthField.setPreferredSize(new Dimension(200, 30));
        //heightField.setPreferredSize(new Dimension(200, 30));
        heightLabel = new JLabel("Height: ");
        widthLabel = new JLabel("Width: ");
        inputPanel.add(widthLabel);
        inputPanel.add(widthField);
        inputPanel.add(heightLabel);
        inputPanel.add(heightField);
        continueButton = new JButton("Continue");
        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputWidth = widthField.getText();
                String inputHeight = heightField.getText();
                if (!isNumeric(inputWidth)||!isNumeric(inputHeight)) {
                    JOptionPane.showMessageDialog(null, "Please enter valid numeric values");
                } 
                else if(Integer.parseInt(inputWidth)>1000||Integer.parseInt(inputWidth)<0||
                        Integer.parseInt(inputHeight)>1000||Integer.parseInt(inputHeight)<0){
                    JOptionPane.showMessageDialog(null, "Please enter values between 0 and 1000");
                }
                else{
                    width = Integer.valueOf(inputWidth);
                    height = Integer.valueOf(inputHeight);
                    controller.hideSettingsFrame(width, height);
                }
            }
        });
        inputPanel.add(continueButton);
        add(inputPanel);
    }
}
