import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Game implements KeyListener {

    private Snake player;
    private Food food;
    private Graphics graphics;
    public boolean demarre = false;
    public boolean debut = true;
    public int SCORE_FINAL;

    public ArrayList<Integer> records = new ArrayList<Integer>();

    private JFrame window;

    public final static int width = 30;
    public final static int height = 30;
    public final static int dimension = 20;



    public Game(){
        window = new JFrame();


        player = new Snake();
        food = new Food(player);

        graphics = new Graphics(this);
        window.add(graphics);

        window.setTitle("CAMARA SNAKE");
        window.setSize(width * dimension +15,  height * dimension+17); // (600,600)
        window.setVisible(true);
        window.setAlwaysOnTop(true);
        window.setLocationRelativeTo(null);
        //window.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);



    }

    public void start(){
        graphics.state = "RUNNING";
    }
    public void update(){
        if(graphics.state == "RUNNING"){
            if(check_food_collision()){
                Audio.playSound("/Sons/cash_register_x.wav");
                player.grow();
                food.random_spawn(player);
            }else if(check_wall_collision() || check_self_collision()){

                SCORE_FINAL = player.getBody().size()-3;
                //System.out.println(player.getBody().size()-3);
                records.add(0,SCORE_FINAL);
                //System.out.println(records.size());
                Audio.playSound("/Sons/cannon_x.wav");
                graphics.state = "END";
            }else {
                player.move();
            }
        }
    }


    private boolean check_wall_collision(){
        if(player.getX() < 0 || player.getX() >= width || player.getY() < 0 || player.getY() >= height ){
            return true;
        }
        return false;
    }


    private boolean check_food_collision(){
        if(player.getX() == food.getX() && player.getY() == food.getY() ){
            return true;
        }
        return false;
    }
    private boolean check_self_collision(){
        for(int i = 1 ; i< player.getBody().size() ; i++){
            if(player.getX() * Game.dimension == player.getBody().get(i).x && player.getY() * Game.dimension == player.getBody().get(i).y){
                System.out.println("cogne");
                return true;
            }
        }
        //System.out.println("Cogne pas");
        return false;
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if(graphics.state == "RUNNING" ){

            if(keyCode == KeyEvent.VK_UP){
                player.up();
                demarre = true;
            }
            if(keyCode == KeyEvent.VK_DOWN){
                player.down();
                demarre = true;
            }
            if(demarre == true){
                if(keyCode == KeyEvent.VK_LEFT){
                    player.left();
                }
            }

            if(keyCode == KeyEvent.VK_RIGHT){
                player.right();
                demarre = true;
            }



        }else if(debut == true){
            this.start();
            debut = false;
            System.out.println(debut);
        }
        if(debut == false && graphics.state == "END"){
            if(keyCode == KeyEvent.VK_N){
                //System.out.println("vous avez appuyé sur n");

                window.dispose();


            }
            if(keyCode == KeyEvent.VK_O){
                //System.out.println("vous avez appuyé sur o");
                window.dispose();
                Game re = new Game();
            }
        }



    }

    @Override
    public void keyReleased(KeyEvent e) { }

    public Snake getPlayer() {
        return player;
    }

    public void setPlayer(Snake player) {
        this.player = player;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public JFrame getWindow() {
        return window;
    }

    public void setWindow(JFrame window) {
        this.window = window;
    }
}
