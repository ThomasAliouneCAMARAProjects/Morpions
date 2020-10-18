import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Graphics extends JPanel implements ActionListener {

    private Timer t = new Timer(100,this);
    public String state;

    private Snake s;
    private Food f;
    private Game game;
    private Font police;

    protected ImageIcon ico;
    protected Image img;

    protected ImageIcon ico2;
    protected Image img2;




    public Graphics(Game g){
        t.start();
        state = "START";

        game = g;
        s = g.getPlayer();
        f = g.getFood();



        this.addKeyListener(g);
        this.setFocusable(true);
        this.setFocusTraversalKeysEnabled(false);
    }

    public void paintComponent(java.awt.Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.BLACK);
        g2d.fillRect(0,0,Game.width * Game.dimension+5,Game.height * Game.dimension+5);

        if(state == "START"){
            ico = new ImageIcon(getClass().getResource("/Images/adv2.png"));
            img = this.ico.getImage();

            ico2 = new ImageIcon(getClass().getResource("/Images/front1.png"));
            img2 = this.ico2.getImage();
            g2d.setColor(Color.white);
            police = new Font("Arial",Font.PLAIN,40);
            g2d.drawString("Appuyez sur un boutton !",230 ,290);
            g2d.drawString("Objectif, mangez le plus de fruits ! (utilisez les commandes directionnelles))",100,270);
            g2d.drawImage(img,115,345,null);
            g2d.drawImage(img2,180,40,null);

        }
        else if(state == "RUNNING"){

            g2d.setColor(Color.white);
            g2d.drawString("Score : " + (s.getBody().size()-3),Game.width /2 * Game.dimension -40 ,2 * Game.dimension - 20);

            g2d.setColor(Color.red);
            g2d.fillRect(f.getX() * Game.dimension ,f.getY() * Game.dimension,Game.dimension,Game.dimension);

            g2d.setColor(Color.GREEN);
            for(Rectangle r : s.getBody()){
                g2d.fill(r);
            }
        }else {
            g2d.setColor(Color.white);
            g.setFont(police);
            g2d.drawString("Votre score : " + (s.getBody().size()-3),160 ,150);
            police = new Font("Arial",Font.PLAIN,40);
            g2d.drawString("Continue ? (oui : o / non : n)",40 ,300 );

        }

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        game.update();
        //System.out.println(state);
    }
}
