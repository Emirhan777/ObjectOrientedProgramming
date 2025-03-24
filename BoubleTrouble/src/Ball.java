public class Ball extends Environment{
    private final double GRAVITATION_CONSTANT = 0.06;




    public Ball( double ballX, double ballY, int level ){
        this.ballX=ballX;
        this.ballY=ballY;
        this.level=level;
    }


    public void move(double iterationTimePast){

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
