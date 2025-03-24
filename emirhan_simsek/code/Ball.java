//Emirhan Şimşek 2021402189 16.04.2023


public class Ball extends Environment{

    public double periodOfBall = 1.5;//suggested: 1.5


    public Ball( double ballX, double ballY, int level, boolean ballExists, double ballVelocityX){
        this.ballX=ballX;
        this.ballY=ballY;
        this.level=level;
        this.ballExists=ballExists;
        radius = (10 * 0.0175) * Math.pow(2.0,level); //suggested
        this.ballVelocityX=ballVelocityX; //suggested
        ballVelocityY = Math.sqrt(2*10*0.0175*20*Math.pow(1.75,level)); //suggested v=sqrt(2gh); 𝑐𝑢𝑟𝑟𝑒𝑛𝑡𝐻𝑒𝑖𝑔h𝑡 = 𝑚𝑖𝑛𝑃𝑜𝑠𝑠𝑖𝑏𝑙𝑒𝐻𝑒𝑖𝑔h𝑡 * 𝐻𝐸𝐼𝐺𝐻𝑇_𝑀𝑈𝐿𝑇𝐼𝑃𝐿𝐼𝐸𝑅(𝑐𝑢𝑟𝑟𝑒𝑛𝑡 𝑙𝑒𝑣𝑒𝑙 𝑜𝑓 𝑏𝑎𝑙𝑙)
    }


    
    public void move(double iterationTimePast){ //move ball



        ballX=ballX +ballVelocityX*iterationTimePast;
        if(ballX-radius<0 || ballX+radius>16){
            ballVelocityX = -ballVelocityX;
        }

        ballY=ballY +ballVelocityY*iterationTimePast;
        ballVelocityY -= GRAVITATION_CONSTANT;

        if (ballY-radius-Math.sqrt(2*10*0.0175*20*Math.pow(1.75,level))*iterationTimePast<0){
            ballVelocityY =Math.sqrt(2*10*0.0175*20*Math.pow(1.75,level)); //Math.sqrt(2*playerHeight*1.4*(1.75^level)*GRAVITATION_CONSTANT);𝑐𝑢𝑟𝑟𝑒𝑛𝑡𝐻𝑒𝑖𝑔h𝑡 = 𝑚𝑖𝑛𝑃𝑜𝑠𝑠𝑖𝑏𝑙𝑒𝐻𝑒𝑖𝑔h𝑡 * 𝐻𝐸𝐼𝐺𝐻𝑇_𝑀𝑈𝐿𝑇𝐼𝑃𝐿𝐼𝐸𝑅(𝑐𝑢𝑟𝑟𝑒𝑛𝑡 𝑙𝑒𝑣𝑒𝑙 𝑜𝑓 𝑏𝑎𝑙𝑙)

        }


    }
}
