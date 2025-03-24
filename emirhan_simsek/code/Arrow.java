//Emirhan Şimşek 2021402189 16.04.2023


public class Arrow extends Environment {

    public double periodOfArrow = 1.5;//suggested: 1.5

    public Arrow( double arrowX, double tipOfArrowY){
        this.arrowX=arrowX;
        this.tipOfArrowY=tipOfArrowY;
    }

    public void move(double iterationTimePast){ //move arrow
        tipOfArrowY+= ((9.0-playerHeight)/periodOfArrow)*iterationTimePast;
    }
}
