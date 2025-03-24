public class Arrow {
    double x2=0;
    static double y2=-0.8;
    static double velocity=2;
    double startTime=0;

    public Arrow(double x2, double startTime){
        this.x2=x2;
        this.startTime=startTime;
    }

    public double getCurrentX2(){
        return x2;
    }
    public double getCurrentY2( double time){
        return y2 + velocity*time;
    }
    public double getStartTime(){return startTime;}



}
