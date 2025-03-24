

public class Arrow extends Environment {



    public Arrow( double arrowX, double arrowY){
        this.arrowX=arrowX;
        this.arrowY=arrowY;
    }

    public void move(double iterationTimePast){
        arrowY+= 3*iterationTimePast;
    }
}
