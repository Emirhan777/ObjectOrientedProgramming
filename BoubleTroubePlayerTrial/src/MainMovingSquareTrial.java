import java.awt.event.KeyEvent;

public class MainMovingSquareTrial {
    public static void main(String[] args) {
        int width = 800;
        int height = 500;

        double timePast=0;
// initialize the canvas
        StdDraw.setCanvasSize(width, height);
        StdDraw.setXscale(0.0, 16.0);
        StdDraw.setYscale(-1.0, 9.0);
        StdDraw.enableDoubleBuffering();

        double x = 8;
        double y = 1.2;
        double y1 = 1.2;
        double rectHalfSize = 0.04;
        double rectHalfHeight = 0.08;

        Arrow arrow=new Arrow(0,0);

        double startTime = System.currentTimeMillis()/1000.0;

        Ball ball = new Ball(2,3,startTime,2, 5, 0.3);

        boolean arrowExists = false;

        StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
        StdDraw.picture(x, y, "player_back.png");
        StdDraw.show();
// pause duration to get keyboard inputs
        int keyboardPauseDuration = 6;
        StdDraw.setPenRadius(0.005);

// main animation loop
        while (true && timePast<40) {
            timePast = System.currentTimeMillis()/1000.0-startTime;
            double systemTime = System.currentTimeMillis()/1000.0;
            System.out.println(timePast);

            if ((x>0.27)) {
                if (StdDraw.isKeyPressed(KeyEvent.VK_LEFT)) {
// Move left
                    StdDraw.pause(keyboardPauseDuration);
                    x = x - 0.12;
                }
            }

            if(x<15.72) {
                if (StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)) {
// Move right
                    StdDraw.pause(keyboardPauseDuration);
                    x = x + 0.12;
                }
            }

            if (StdDraw.isKeyPressed(KeyEvent.VK_SPACE) && !arrowExists) {
                StdDraw.pause(keyboardPauseDuration);
                arrow = new Arrow(x, System.currentTimeMillis()/1000.0);
                arrowExists=true;
            }

            StdDraw.clear();

            double arrowHeight = arrow.getCurrentY2(System.currentTimeMillis() / 1000.0 - arrow.getStartTime());
            if (arrowHeight<2.45) {
                System.out.println("height" +arrowHeight);
                StdDraw.picture(arrow.getCurrentX2(), arrow.getCurrentY2(arrowHeight), "arrow.png",0.16,10.0);}
            else {arrowExists=false;}

            if (ball.getCurrentY(ball.ballStartY, systemTime)<0){
                ball.ballStartY=0;
                ball.ballStartTime=systemTime;
            }

            if (ball.getCurrentX(ball.ballStartX, systemTime)<0){
                ball.ballStartY=0;
                ball.ballStartTime=systemTime;
            }


            ball.setVelocityY();
            StdDraw.picture(ball.getCurrentX(ball.ballStartX, timePast), ball.getCurrentY(ball.ballStartY, systemTime),"ball.png",1,1);

            StdDraw.picture(x, y, "player_back.png");
            StdDraw.show();
        }
    }
}
