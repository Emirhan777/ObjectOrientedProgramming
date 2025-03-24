public class Ball extends Environment{

    public double periodOfBall = 0.5;//suggested: 1.5


    public Ball( double ballX, double ballY, int level, boolean ballExists){
        this.ballX=ballX;
        this.ballY=ballY;
        this.level=level;
        this.ballExists=ballExists;
        radius = (10 * 0.0175) * Math.pow(2.0,level);
    }


    
    public void move(double iterationTimePast){ //move ball



        ballX=ballX +ballVelocityX*iterationTimePast;
        if(ballX-radius<0 || ballX+radius>16){
            ballVelocityX = -ballVelocityX;
        }

        ballY=ballY +ballVelocityY*iterationTimePast;
        ballVelocityY -= GRAVITATION_CONSTANT;

        if (ballY-radius<0){
            ballVelocityY =5; //Math.sqrt(2*playerHeight*1.4*GRAVITATION_CONSTANT);;
        }


    }
}
