import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 * TreePanel
 */
public class TreePanel extends JPanel {
    public int season;
    public static final int SPRING = 0;
    public static final int SUMMER = 1;
    public static final int FALL = 2;
    public static final int WINTER = 3;
    public int shiftAmount = 0;
    public int[][] snowFlakes = new int[200][2];
    public int[] xOfRoot = {260, 330, 360, 340, 185, 175, 205, 350, 365, 330, 395, 380, 425, 475, 440, 505, 455, 500, 560, 600, 575, 440, 470, 540};
    public int[] yOfRoot = {750, 715, 620, 485, 415, 250, 390, 440, 320, 210, 310, 455, 530, 505, 350, 190, 350, 505, 450, 300, 470, 630, 720, 750};
    public int[] xOfLeaf = {260, 215, 105, 30, 145, 300, 510, 640, 700, 570, 375, 286};
    public int[] yOfLeaf = {200, 150, 185, 340, 455, 390, 440, 400, 260, 125, 80, 95};
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        /*ActionListener l = new TimerListenerForWind();
            Timer ti = new Timer(200, l);
            ti.start();*/
        if (season == SPRING) {
            drawBackGround(g, new Color(125, 164, 92), new Color(118, 241, 255), new Color(0, 212, 107), true);
        } else if (season == SUMMER) {
            drawBackGround(g, new Color(66, 183, 54), new Color(156, 233, 255), new Color(0, 213, 0), true);
            addApple(g);
        } else if (season == FALL) {
            drawBackGround(g, new Color(203, 110, 0), new Color(102, 174, 188), new Color(227, 112, 0), true);
            
        } else if (season == WINTER) {
            drawBackGround(g, new Color(125, 146, 144), new Color(78, 96, 114), new Color(0, 0, 0), false);
            /*ActionListener listenerSnow = new TimerListenerForSnow();
            Timer t = new Timer(500, listenerSnow);
            t.start();*/
            paintSnowFlakes(g);
        } 
    }

    public TreePanel(){
        makeSnowFlakes();

        ActionListener listenerSnow = new TimerListenerForSnow();
        Timer t = new Timer(3, listenerSnow);
        t.start();

        ActionListener l = new TimerListenerForWind();
        Timer ti = new Timer(250, l);
        ti.start();
    }

    public void setSeason(int s){
        season = s;
    }

    public void drawBackGround(Graphics g, Color surface, Color sky, Color leafColor, boolean leaf){
        g.setColor(sky);
        g.fillRect(0, 0, 800, 800);
        g.setColor(surface);
        g.fillRect(0,750, 800, 50);
        if (leaf) {
            g.setColor(leafColor);
            g.fillPolygon(xOfLeaf, yOfLeaf, xOfLeaf.length);
        }
        g.setColor(new Color(150,53,30));
        g.fillPolygon(xOfRoot, yOfRoot, xOfRoot.length);
    }

    public void addApple(Graphics g){
        g.setColor(Color.RED);
        g.fillOval(140, 280, 30, 30);
        g.fillOval(250, 325, 30, 30);
        g.fillOval(400, 170, 30, 30);
        g.fillOval(590, 220, 30, 30);
        g.fillOval(525, 350, 30, 30);
    }

    public void paintSnowFlakes(Graphics g){
        g.setColor(Color.WHITE);
        for (int i = 0; i < snowFlakes.length; i++) {
            g.fillOval(snowFlakes[i][0], snowFlakes[i][1]%750, 5, 5);
        }
        repaint();
    }

    public void makeSnowFlakes(){
        for (int i = 0; i < snowFlakes.length; i++) {
            snowFlakes[i][0] = (int)(Math.random()*750);
            snowFlakes[i][1] = (int)(Math.random()*800);
        }
    }

    public void moveSnowFlakes(){
        for (int i = 0; i < snowFlakes.length; i++) {
            snowFlakes[i][1] += (int)(Math.random()*2);
            snowFlakes[i][0] += (int)(((Math.random()) - (Math.random()))*2);
        }
        repaint();
    }

    public void wind(int strength){
        if (shiftAmount%2==0) {
            for (int i = 0; i < xOfRoot.length; i++) {
                if (yOfRoot[i]<370) {
                    xOfRoot[i]+=3*strength;
                } 
                else if(yOfRoot[i]<530){
                    xOfRoot[i]+=2*strength;
                }
                else if(yOfRoot[i]<725){
                    xOfRoot[i]+=1*strength;
                }
            }
            repaint();
        }
        else{
            for (int i = 0; i < xOfRoot.length; i++) {
                if(yOfRoot[i]<370){
                    xOfRoot[i]-=3*strength;
                }
                else if(yOfRoot[i]<530){
                    xOfRoot[i]-=2*strength;
                }
                else if(yOfRoot[i]<725){
                    xOfRoot[i]-=1*strength;
                }
            }
            repaint();
        }
        if (season==FALL||season==SPRING) {
            if (shiftAmount%2==0) {
                for (int i = 0; i < xOfLeaf.length; i++) {
                    if (yOfLeaf[i]<370) {
                        xOfLeaf[i]+=3*strength;
                    } 
                    else if(yOfLeaf[i]<530){
                        xOfLeaf[i]+=2*strength;
                    }
                    else if(yOfLeaf[i]<725){
                        xOfLeaf[i]+=1*strength;
                    }
                }
                repaint();
            }
            else{
                for (int i = 0; i < xOfLeaf.length; i++) {
                    if(yOfLeaf[i]<370){
                        xOfLeaf[i]-=3*strength;
                    }
                    else if(yOfLeaf[i]<530){
                        xOfLeaf[i]-=2*strength;
                    }
                    else if(yOfLeaf[i]<725){
                        xOfLeaf[i]-=1*strength;
                    }
                }
                repaint();
            }
        }
        shiftAmount++;
    }
    class TimerListenerForSnow implements ActionListener{
        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {
            moveSnowFlakes();
        }        
    }
    class TimerListenerForWind implements ActionListener{
        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {
            if (season == SPRING) {
                wind(1);
            }
            else if(season==FALL){
                wind(2);
            }
            else if (season==WINTER) {
                wind(3);
            }
        }        
    }
}