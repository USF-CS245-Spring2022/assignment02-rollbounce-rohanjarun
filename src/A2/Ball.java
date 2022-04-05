//Ball file Rohan Jay Arun
package A2; //package for assignment 2
import java.awt.*; //importing the awt
public class Ball {
    private int x; //declaring the ints for the Ball
    private int y;
    private int verticalVelocity;
    private int horizontalVelocity;

    public Ball (int x, int y, int verticalVelocity, int horizontalVelocity) { //public class for variables
        this.horizontalVelocity = horizontalVelocity;
        this.x = x;
        this.y = y;
        this.verticalVelocity = verticalVelocity;

    }
 //getters and setters for the integers
    public int getHorizontalVelocity() { return horizontalVelocity; }
    public void setHorizontalVelocity(int horizontalVelocity) { this.horizontalVelocity = horizontalVelocity; }
    public int getX() { return x; } 
    public void setX(int x) { this.x = x; }
    public int getY() { return y; }
    public void setY(int y) { this.y = y; }
    public int getVerticalVelocity() { return verticalVelocity; }
    public void setVerticalVelocity(int verticalVelocity) { this.verticalVelocity = verticalVelocity; }

    
}
