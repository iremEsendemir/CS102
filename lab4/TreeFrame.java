import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TreeFrame extends JFrame{
    public TreePanel treePanel;
    public JPanel buttonPanel = new JPanel();
    public JButton springButton = new JButton("Spring");
    public JButton summerButton = new JButton("Summer");
    public JButton fallButton = new JButton("Fall");
    public JButton winterButton = new JButton("Winter");

    public TreeFrame(){
        treePanel = new TreePanel();
        setTitle("Ağaç mağaç");
        buttonPanel.setLayout(new GridLayout(1, 4));
        buttonPanel.add(springButton);
        buttonPanel.add(summerButton);
        buttonPanel.add(fallButton);
        buttonPanel.add(winterButton);
        springButton.addActionListener(new ActionListener() {
        @Override
            public void actionPerformed(ActionEvent e) {
            ((TreePanel)treePanel).setSeason(0);
            ((TreePanel)treePanel).repaint();
        } });

        summerButton.addActionListener(new ActionListener() {
        @Override
            public void actionPerformed(ActionEvent e) {
            ((TreePanel)treePanel).setSeason(1);
            ((TreePanel)treePanel).repaint();
        } });

        fallButton.addActionListener(new ActionListener() {
        @Override
            public void actionPerformed(ActionEvent e) {
            ((TreePanel)treePanel).setSeason(2);
            ((TreePanel)treePanel).repaint();
        } });

        winterButton.addActionListener(new ActionListener() {
        @Override
            public void actionPerformed(ActionEvent e) {
            ((TreePanel)treePanel).setSeason(3);
            ((TreePanel)treePanel).repaint();
        } });


        setLayout(new BorderLayout());
        add(treePanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        this.pack();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        TreeFrame frame = new TreeFrame();
        frame.setTitle("Season Change App");
        frame.setSize(800, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
