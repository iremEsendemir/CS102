import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Application {
    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.settingsFrame.setSize(300,400);
        controller.settingsFrame.setTitle("hello"); 
        controller.settingsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        controller.settingsFrame.setVisible (true);
    }
}
