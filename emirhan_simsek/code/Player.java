//Emirhan Şimşek 2021402189 16.04.2023

import java.awt.event.KeyEvent;

public class Player extends Environment{
    public double periodOfPLayer=5.4; //suggested: 6

    public Player( double playerX, double playerY){
        this.playerX=playerX;
        this.playerY=playerY;
        playerWidth=(5.4/8.0); //suggested:(10.0/8.0)
        playerHeight=(37.0/23.0)*playerWidth; //suggested: (37.0/27.0)*(10.0/8.0)
    }

    public void move(double iterationTimePast){ //move player

        if (StdDraw.isKeyPressed(KeyEvent.VK_LEFT) && playerX - playerWidth/2 - (16.0/periodOfPLayer)*iterationTimePast> 0){
            playerX = playerX - (16.0/periodOfPLayer)*iterationTimePast;} // Move left


        if ((StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)) && playerX + playerWidth/2 + (16.0/periodOfPLayer)*iterationTimePast< 16.0){
            playerX = playerX + (16.0/periodOfPLayer)*iterationTimePast;} // Move right


        if (StdDraw.isKeyPressed(KeyEvent.VK_SPACE) && !arrowExists){ //Reset arrow
            System.out.println("pressed");
            arrowX=playerX;
            tipOfArrowY=playerHeight;
            arrowExists=true;
        }



    }
}
