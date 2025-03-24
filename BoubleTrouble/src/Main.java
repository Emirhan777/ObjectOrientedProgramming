import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        int canvasWidth=800;
        int canvasHeight=500;

        double timePast=0;

        StdDraw.setCanvasSize(canvasWidth, canvasHeight); // set the size of the drawing canvas
        StdDraw.setXscale(0.0, 16.0); // set the scale of the coordinate system
        StdDraw.setYscale(-1.0, 9.0);
        StdDraw.enableDoubleBuffering();



        ArrayList<Ball> balls = new ArrayList<>();

        Player player=new Player(2,(37.0/27.0)*(10.0/8.0)/4);
        Arrow arrow=new Arrow(2,(37.0/27.0)*(10.0/8.0)/2-4.5);
        Ball ball1=new Ball(2,3, 3);
        Bar bar=new Bar(8,-0.5);


        int pauseDuration=6;

        double startTime = System.currentTimeMillis()/1000.0;

        while (true && timePast<20.0){
            double timePast1 = timePast; //take time past before previous iteration
            timePast = System.currentTimeMillis()/1000.0-startTime; //refresh time past
            double iterationTimePast=timePast-timePast1;

            StdDraw.clear();

            player.move(iterationTimePast);
            arrow.move(iterationTimePast);
            ball1.move(iterationTimePast);

            //StdDraw.picture(8,4,"background.png",16,10);

            if(arrow.arrowY<4.5 && arrow.arrowExists) {StdDraw.picture(arrow.arrowX, arrow.arrowY, "arrow.png",0.1,9.0);}
            else {arrow.arrowExists=false;}
            StdDraw.picture(player.playerX, player.playerY, "player_back.png",(10.0/8.0)/2 ,(37.0/27.0)*(10.0/8.0)/2);
            StdDraw.picture(ball1.ballX, ball1.ballY, "ball.png",2*ball1.radius, 2*ball1.radius);
            StdDraw.picture(bar.barX, bar.barY, "bar.png",16,1);


            StdDraw.pause(pauseDuration);
            StdDraw.show();
            System.out.println("iteration time"+iterationTimePast);

        }





    }
}