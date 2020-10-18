import java.awt.*;

public class Food {
    private int x;
    private int y;



    public Food(Snake player){
        this.random_spawn(player);
    }

    public void random_spawn(Snake player){

        boolean onSnake = true;
        while (onSnake){  // tant que la nourriture est sur le serpent, true.
            onSnake = false;
            x = (int)(Math.random() * Game.width-1);
            y = (int)(Math.random() * Game.height-1);


            for(Rectangle r : player.getBody()){
                if(r.x == x && r.y == y){  // si la nourriture est sur le serpent
                    onSnake = true;        // on reste dans le while
                }// else return false ? sinon il ne revient pas à true
            }

        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
