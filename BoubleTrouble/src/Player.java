import java.awt.event.KeyEvent;

public class Player extends Environment{


    public Player( double playerX, double playerY){
        this.playerX=playerX;
        this.playerY=playerY;
    }

    public void move(double iterationTimePast){

        if (StdDraw.isKeyPressed(KeyEvent.VK_LEFT)) {
            if(playerX>0){playerX = playerX - 10*iterationTimePast;} // Move left
        }

        if (StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)) {
            if(playerX<16.0){playerX = playerX + 10*iterationTimePast;} // Move right
        }

        if (StdDraw.isKeyPressed(KeyEvent.VK_SPACE) && !arrowExists){ //Reset arrow
            arrowX=playerX;
            arrowY=(37.0/27.0)*(10.0/8.0)-4.5;
            arrowExists=true;
        }



    }
}
