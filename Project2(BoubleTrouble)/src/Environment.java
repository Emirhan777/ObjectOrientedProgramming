

public class Environment {

    protected final double GRAVITATION_CONSTANT = 0.06;

    //
    public double playerX;
    public double playerY;

    public static double arrowX;
    public static double arrowY;
    public static boolean arrowExists=false;

    public double ballX;
    public double ballY;
    public double level;
    public double ballVelocityX = 2;
    public double ballVelocityY = 5;
    public double radius = 0.3;
    public static boolean ballExists=true;

    //

    public void exists(){
        ballExists=false;
    }

    public void move(double iterationTimePast){

        double tipOfArrowY=2*arrowY;
//        if (arrowExists && (((ballX-radius)>arrowX && (ballX+radius)>arrowX && ballY<tipOfArrowY) || ((ballX-arrowX)*(ballX-arrowX) + (ballY-tipOfArrowY)*(ballY-tipOfArrowY) <radius*radius)){
        if (arrowExists && (ballX-radius)<arrowX && (ballX+radius)>arrowX){
            System.out.println("hit");
            ballExists=false;
        }

        ballX=ballX +ballVelocityX*iterationTimePast;
        if(ballX-radius<0 || ballX+radius>16){
            ballVelocityX = -ballVelocityX;
        }

        ballY=ballY +ballVelocityY*iterationTimePast;
        ballVelocityY -= GRAVITATION_CONSTANT;

        if (ballY-radius<0){
            ballVelocityY =5;
        }
    }

}
