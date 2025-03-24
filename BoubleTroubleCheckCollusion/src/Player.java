import java.awt.event.KeyEvent;

public class Player extends Environment{
    public double periodOfPLayer=2; //suggested: 6

    public Player( double playerX, double playerY){
        this.playerX=playerX;
        this.playerY=playerY;
    }

    public void move(double iterationTimePast){ //move player

        if (StdDraw.isKeyPressed(KeyEvent.VK_LEFT)) {
            if(playerX>0){playerX = playerX - (16.0/periodOfPLayer)*iterationTimePast;} // Move left
        }

        if (StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)) {
            if(playerX<16.0){playerX = playerX + (16.0/periodOfPLayer)*iterationTimePast;} // Move right
        }

        if (StdDraw.isKeyPressed(KeyEvent.VK_SPACE) && !arrowExists){ //Reset arrow
            System.out.println("pressed");
            arrowX=playerX;
            tipOfArrowY=playerHeight;
            arrowExists=true;
        }



    }
}
