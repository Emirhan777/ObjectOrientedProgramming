
public class Main {
    public static void main(String[] args) {

        int canvasWidth=800;
        int canvasHeight=500;

        int green=255;
        double timePast=0;

        StdDraw.setCanvasSize(canvasWidth, canvasHeight); // set the size of the drawing canvas
        StdDraw.setXscale(0.0, 16.0); // set the scale of the coordinate system
        StdDraw.setYscale(-1.0, 9.0);
        StdDraw.enableDoubleBuffering();



        Ball balls0;
        Ball[] balls1 = new Ball[3];
        Ball[] balls2 = new Ball[7];


        Player player=new Player(4,(37.0/27.0)*(10.0/8.0)/4);
        Arrow arrow=new Arrow(2,player.playerHeight);

        balls0 =new Ball(2,1, 0,true);
        for (int i = 0; i < balls1.length ; i++) {
            if(i==0){balls1[i]=new Ball(2,2, 1,true);}
            else{balls1[i]=new Ball(2,2, 0,false);}
        }
        for (int i = 0; i < balls2.length ; i++) {
            if(i==0){balls2[i]=new Ball(2,3, 2, true);}
            else if (i==1 || i==2) {balls2[i]=new Ball(2,3, 1, false);}
            else{balls2[i]=new Ball(2,3, 0,false);}
        }


        Bar bar=new Bar(8,-0.5);




        int pauseDuration=6;

        double startTime = System.currentTimeMillis()/1000.0;

        while (timePast<40.0){
            double timePast1 = timePast; //take time past before previous iteration
            timePast = System.currentTimeMillis()/1000.0-startTime; //refresh time past
            double iterationTimePast=timePast-timePast1;


            StdDraw.clear();

            player.move(iterationTimePast);
            arrow.move(iterationTimePast);
//            System.out.println("tipOfArrow: " + arrow.tipOfArrowY);

            balls0.move(iterationTimePast);
            for (Ball ball : balls1) {ball.move(iterationTimePast);}
            for (Ball ball : balls2) {ball.move(iterationTimePast);}

            bar.move(iterationTimePast);

            StdDraw.picture(8,4,"background.png",16,10);

            if(arrow.tipOfArrowY<9 && arrow.arrowExists) {StdDraw.picture(arrow.arrowX, arrow.tipOfArrowY/2, "arrow.png",0.1, arrow.tipOfArrowY);}
            else {arrow.arrowExists=false;}

            StdDraw.picture(8, -0.5, "bar.png",16,1);
            StdDraw.picture(player.playerX, player.playerY, "player_back.png",player.playerWidth ,player.playerHeight);



            if( balls0.ballExists &&
                    (( balls0.ballX < player.playerX + player.playerWidth/2 - 0.036 + balls0.radius && balls0.ballX > player.playerX - player.playerWidth/2 + 0.036 - balls0.radius && balls0.ballY < player.playerHeight - 0.036 ) ||
                    ( balls0.ballX < player.playerX + player.playerWidth/2 - 0.036 && balls0.ballX > player.playerX - player.playerWidth/2 + 0.036 && balls0.ballY < player.playerHeight - 0.036 + balls0.radius ) ||
                    ( (balls0.ballX - player.playerX - player.playerWidth/2 + 0.036)*(balls0.ballX - player.playerX - player.playerWidth/2 + 0.036) + (balls0.ballY - player.playerHeight + 0.036)*(balls0.ballY - player.playerHeight + 0.036) < balls0.radius*balls0.radius ) ||
                    ( (balls0.ballX - player.playerX + player.playerWidth/2 - 0.036)*(balls0.ballX - player.playerX + player.playerWidth/2 - 0.036) + (balls0.ballY - player.playerHeight + 0.036)*(balls0.ballY - player.playerHeight + 0.036) < balls0.radius*balls0.radius )) ){
                System.out.println(balls0.ballExists);
                System.out.println("faul");
                System.out.println("balls0"+"radiıs:"+ balls0.radius + " ballX: " + balls0.ballX + " ballY: " + balls0.ballY + "PLAYERY: " + player.playerHeight);
                Environment.gameLost=true;
                StdDraw.pause(100000);
                //StdDraw.pause(10000);
                //System.exit(0);
            }
            
            else if(arrow.arrowExists && balls0.ballExists &&
                    (((balls0.ballX-balls0.radius)<arrow.arrowX && (balls0.ballX+balls0.radius)>arrow.arrowX && balls0.ballY<arrow.tipOfArrowY) ||
                            ((balls0.ballX-arrow.arrowX)*(balls0.ballX-arrow.arrowX) + (balls0.ballY- arrow.tipOfArrowY)*(balls0.ballY- arrow.tipOfArrowY) <balls0.radius*balls0.radius))){
                arrow.arrowExists=false;
                balls0.ballExists=false;
                System.out.println("hit");
            }

            for (int i = 0; i < balls1.length; i++) {
                if( balls1[i].ballExists &&
                        (( balls1[i].ballX < player.playerX + player.playerWidth/2 - 0.08 + balls1[i].radius && balls1[i].ballX > player.playerX - player.playerWidth/2 + 0.08 - balls1[i].radius && balls1[i].ballY < player.playerHeight - 0.08 ) ||
                        ( balls1[i].ballX < player.playerX + player.playerWidth/2 - 0.08 && balls1[i].ballX > player.playerX - player.playerWidth/2 + 0.08 && balls1[i].ballY < player.playerHeight - 0.08 + balls1[i].radius ) ||
                        ( (balls1[i].ballX - player.playerX - player.playerWidth/2 + 0.08)*(balls1[i].ballX - player.playerX - player.playerWidth/2 + 0.08) + (balls1[i].ballY - player.playerHeight + 0.08)*(balls1[i].ballY - player.playerHeight + 0.08) < balls1[i].radius*balls1[i].radius ) ||
                        ( (balls1[i].ballX - player.playerX + player.playerWidth/2 - 0.08)*(balls1[i].ballX - player.playerX + player.playerWidth/2 - 0.08) + (balls1[i].ballY - player.playerHeight + 0.08)*(balls1[i].ballY - player.playerHeight + 0.08) < balls1[i].radius*balls1[i].radius )) ){
                    System.out.println(balls1[i].ballExists);
                    System.out.println("faul");
                    System.out.println("balls1"+i+"radiıs:"+ balls1[i].radius + " ballX: " + balls1[i].ballX + " ballY: " + balls1[i].ballY + "PLAYERY: " + player.playerHeight);
                    Environment.gameLost=true;
                    StdDraw.pause(100000);
                    //System.exit(0);
                }
                else if (arrow.arrowExists && balls1[i].ballExists &&
                        (((balls1[i].ballX-balls1[i].radius)<arrow.arrowX && (balls1[i].ballX+balls1[i].radius)>arrow.arrowX && balls1[i].ballY<arrow.tipOfArrowY) ||
                                ((balls1[i].ballX-arrow.arrowX)*(balls1[i].ballX-arrow.arrowX) + (balls1[i].ballY- arrow.tipOfArrowY)*(balls1[i].ballY- arrow.tipOfArrowY) <balls1[i].radius*balls1[i].radius))){
                    arrow.arrowExists=false;
                    balls1[i].ballExists=false;
                    System.out.println("hit");
                    if(i==0) {
                        balls1[1].ballExists = true;
                        balls1[1].ballVelocityY=5;
                        balls1[2].ballExists = true;
                        balls1[2].ballVelocityY=5;
                        balls1[2].ballVelocityX=-balls1[2].ballVelocityX;
                    }
                }
            }

            for (int i = 0; i < balls2.length; i++) {
                if( balls2[i].ballExists &&
                        (( balls2[i].ballX < player.playerX + player.playerWidth/2 - 0.036 + balls2[i].radius && balls2[i].ballX > player.playerX - player.playerWidth/2 + 0.036 - balls2[i].radius && balls2[i].ballY < player.playerHeight - 0.036 ) ||
                        ( balls2[i].ballX < player.playerX + player.playerWidth/2 - 0.036 && balls2[i].ballX > player.playerX - player.playerWidth/2 + 0.036 && balls2[i].ballY < player.playerHeight - 0.036 + balls2[i].radius ) ||
                        ( (balls2[i].ballX - player.playerX - player.playerWidth/2 + 0.036)*(balls2[i].ballX - player.playerX - player.playerWidth/2 + 0.036) + (balls2[i].ballY - player.playerHeight + 0.036)*(balls2[i].ballY - player.playerHeight + 0.036) < balls2[i].radius*balls2[i].radius ) ||
                        ( (balls2[i].ballX - player.playerX + player.playerWidth/2 - 0.036)*(balls2[i].ballX - player.playerX + player.playerWidth/2 - 0.036) + (balls2[i].ballY - player.playerHeight + 0.036)*(balls2[i].ballY - player.playerHeight + 0.036) < balls2[i].radius*balls2[i].radius )) ){
                    System.out.println(balls2[i].ballExists);
                    System.out.println("faul");
                    System.out.println("balls2"+i+"radius:"+ balls2[i].radius +" ballX: " + balls2[i].ballX + " ballY: " + balls2[i].ballY + "PLAYERY: " + player.playerHeight);
                    Environment.gameLost=true;
                    StdDraw.pause(100000);
                    //System.exit(0);
                }
                else if(arrow.arrowExists && balls2[i].ballExists &&
                        (((balls2[i].ballX-balls2[i].radius)<arrow.arrowX && (balls2[i].ballX+balls2[i].radius)>arrow.arrowX && balls2[i].ballY<arrow.tipOfArrowY) ||
                        ((balls2[i].ballX-arrow.arrowX)*(balls2[i].ballX-arrow.arrowX) + (balls2[i].ballY- arrow.tipOfArrowY)*(balls2[i].ballY- arrow.tipOfArrowY) <balls2[i].radius*balls2[i].radius))){
                    arrow.arrowExists=false;
                    balls2[i].ballExists=false;
                    System.out.println("hit");
                    if(i==0) {
                        balls2[1].ballExists = true;
                        balls2[1].ballVelocityY=5;
                        balls2[1].ballX = balls2[0].ballX;
                        balls2[1].ballY=balls2[0].ballY;

                        balls2[2].ballExists = true;
                        balls2[2].ballVelocityY=5;
                        balls2[2].ballVelocityX=-balls2[2].ballVelocityX;
                        balls2[2].ballX = balls2[0].ballX;
                        balls2[2].ballY=balls2[0].ballY;
                    }
                    else if (i==1 || i==2) {
                        balls2[i + 2].ballExists = true;
                        balls2[i + 2].ballVelocityY = 5;
                        balls2[i + 2].ballX = balls2[i].ballX;
                        balls2[i + 2].ballY=balls2[i].ballY;

                        balls2[i + 4].ballExists = true;
                        balls2[i + 4].ballVelocityY = 5;
                        balls2[i + 4].ballVelocityX = -balls2[i + 4].ballVelocityX;
                        balls2[i + 4].ballX = balls2[i].ballX;
                        balls2[i + 4].ballY=balls2[i].ballY;
                    }
                }
            }



            if(balls0.ballExists){StdDraw.picture(balls0.ballX, balls0.ballY, "ball.png",2*balls0.radius, 2*balls0.radius);}
            for (int i = 0; i < balls1.length; i++) {
                if(balls1[i].ballExists){
                    StdDraw.picture(balls1[i].ballX, balls1[i].ballY, "ball.png",2*balls1[i].radius, 2*balls1[i].radius);
                }
            }
            for (int i = 0; i < balls2.length; i++) {
                if(balls2[i].ballExists){
                    StdDraw.picture(balls2[i].ballX, balls2[i].ballY, "ball.png",2*balls2[i].radius, 2*balls2[i].radius);}
            }


            int green2 = green - (int)((timePast*255)/40);
            StdDraw.setPenColor(255,green2,0);
            StdDraw.filledRectangle(bar.barX, bar.barY,  8,0.25);

            if(timePast>=40){
                Environment.gameLost=true;
            }
            if(Environment.gameLost){
                StdDraw.picture(8.0,10.0/2.18,"game_screen.png",16/3.8,10.0/4); //suggested
            }

            StdDraw.pause(pauseDuration);
            StdDraw.show();
            System.out.println(timePast);
        }







    }
}