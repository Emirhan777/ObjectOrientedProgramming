

public class Environment {

    //constant
    protected final double GRAVITATION_CONSTANT = 0.06; //suggested: 3*9

    //variables
    public static double playerX;
    public static double playerY;
    public double playerWidth=(10.0/8.0)/2;
    public double playerHeight=(37.0/27.0)*(10.0/8.0)/2;

    public static double arrowX;
    public static double tipOfArrowY;
    public static boolean arrowExists=false;

    public double ballX;
    public double ballY;
    public double level;
    public double ballVelocityX = (16.0/15.0); //suggested
    public double ballVelocityY = Math.sqrt(2*playerHeight*1.4*GRAVITATION_CONSTANT); //suggested v=sqrt(2gh)
    public double radius;
    public boolean ballExists=false;



    //

    public boolean gameOver=false;
    public static boolean gameWon=false;
    public static boolean gameLost=false;

    public static void drawGameScreen(){
    //    StdDraw.picture(8.0,10.0/2.18,"gamescreen.png",16/3.8,10.0/4); //suggested

        //if()
    }

}
