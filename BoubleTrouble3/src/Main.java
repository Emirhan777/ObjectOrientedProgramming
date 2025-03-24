import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        int canvasWidth=800;
        int canvasHeight=500;

        int green=255;
        double timePast=0;

        StdDraw.setCanvasSize(canvasWidth, canvasHeight); // set the size of the drawing canvas
        StdDraw.setXscale(0.0, 16.0); // set the scale of the coordinate system
        StdDraw.setYscale(-1.0, 9.0);
        StdDraw.enableDoubleBuffering();



        Ball[] balls0 = new Ball[1];
        Ball[] balls1 = new Ball[3];
        Ball[] balls2 = new Ball[7];


        Player player=new Player(2,(37.0/27.0)*(10.0/8.0)/4);
        Arrow arrow=new Arrow(2,(37.0/27.0)*(10.0/8.0)/2-4.5);

        balls0[0]=new Ball(2,3, 3);
        for (int i = 0; i < balls1.length ; i++) {
            balls1[i]=new Ball(2,3, 3);
        }
        balls2[0]=new Ball(2,3, 3);


        Bar bar=new Bar(8,-0.5);


        int pauseDuration=6;

        double startTime = System.currentTimeMillis()/1000.0;

        while (timePast<40.0){
            double timePast1 = timePast; //take time past before previous iteration
            timePast = System.currentTimeMillis()/1000.0-startTime; //refresh time past
            double iterationTimePast=timePast-timePast1;

            StdDraw.clear();

            player.move(iterationTimePast);
            arrow.move(iterationTimePast);
            balls1[0].move(iterationTimePast);
            bar.move(iterationTimePast);

            StdDraw.picture(8,4,"background.png",16,10);

            if(arrow.arrowY<4.5 && arrow.arrowExists) {StdDraw.picture(arrow.arrowX, arrow.arrowY, "arrow.png",0.1,9.0);}
            else {arrow.arrowExists=false;}

            StdDraw.picture(8, -0.5, "bar.png",16,1);
            StdDraw.picture(player.playerX, player.playerY, "player_back.png",(10.0/8.0)/2 ,(37.0/27.0)*(10.0/8.0)/2);

            for (int i = 0; i < 1; i++) {
                if(balls1[0].ballExists){StdDraw.picture(balls1[0].ballX, balls1[0].ballY, "ball.png",2*balls1[0].radius, 2*balls1[0].radius);}
                else if(!balls1[1].ballExists){
                    System.out.println("velocity"+balls1[1].ballVelocityX);
                    balls1[1].exists();
                    balls1[1].ballVelocityX=-balls1[1].ballVelocityX;}
                System.out.println("velocity"+balls1[1].ballVelocityX);

            }

            int green2 = green - (int)((timePast*255)/40);
            StdDraw.setPenColor(255,green2,0);
            StdDraw.filledRectangle(bar.barX, bar.barY,  8,0.25);

            StdDraw.pause(pauseDuration);
            StdDraw.show();
            System.out.println("iteration time"+iterationTimePast);
            System.out.println(timePast);
            System.out.println(green2);

        }





    }
}