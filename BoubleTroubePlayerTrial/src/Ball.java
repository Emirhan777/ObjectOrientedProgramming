public class Ball {
    double GRAVITAION_CONSTANT=9.8;
    double velocityX;
    double velocityY;
    double ballSize;
    double ballX;
    double ballY;
    double ballStartX;
    double ballStartY;
    double ballStartTime;
    int level=0;

    public Ball(double ballStartX, double ballStartY, double ballStartTime, double velocityX, double velocityY, double Size){
        this.ballX = ballStartX;
        this.ballY = ballStartY;
        this.ballStartX=ballStartX;
        this.ballStartY=ballStartY;
        this.GRAVITAION_CONSTANT=5;
        this.velocityX=velocityX;
        this.velocityY=velocityY;
        this.ballStartTime=ballStartTime;
    }

  //  public void setBallX() {
    //    this.ballX = ballX;
    //}

    //public void setBallY() {
      //  this.ballY = ballY;
    //}
  //  public void setBallStartTime (){
   //     this.ballStartTime=
  //  }

    public void checkCollusion(double timePast){
        if (getCurrentX(ballStartX,timePast-ballStartTime)<0.0 || getCurrentX(ballStartX,timePast-ballStartTime)>16.0){
            this.velocityX=-velocityX;
        }
        else if(getCurrentY(ballStartY, timePast-ballStartTime)<0){
//            setVelocityY();
        }
//        else if (getCurrentX(ballStartX,timePast-ballStartTime)==arrow.getCurrentX2()) {
  //          ball=null;
    //    }
    }

    public void setVelocityX() {
        this.velocityX = -velocityX;
    }

    public void setVelocityY(){
        if(level==0) {
            this.velocityY = 5;
        }
        if(level==1) {
            this.velocityY = 0.6;
        }
        if(level==2) {
            this.velocityY = 0.3;
        }
    }

    public double getCurrentX(double x0, double time){


        return x0 + velocityX*time;
    }
    
    public double getCurrentY(double y0, double systemTime){

        System.out.println("Velocity "+velocityY);
        double ballTimePast = systemTime-ballStartTime;
        return y0 + velocityY*ballTimePast - GRAVITAION_CONSTANT*ballTimePast*ballTimePast/2;
    }










}
