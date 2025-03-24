public class Bar extends Environment{

    public double barX;
    public double barY;
    public double barVelocityX = 16.0/40.0;

    public Bar( double barX, double barY){
        this.barX=barX;
        this.barY=barY;

    }

    public void move(double iterationTimePast){
        barX -= barVelocityX*iterationTimePast;
    }
}
