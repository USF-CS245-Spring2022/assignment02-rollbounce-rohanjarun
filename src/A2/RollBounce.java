package A2;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
//importing the packages required

public class RollBounce extends JPanel implements ActionListener { //declaring the parameters required for this assignment
    protected Timer tm;
    private final Properties ballConfig;
    private final ListNode<Ball> balls;
    private final ListNode<Color> colors;
    protected int x1, y1;
    protected int x2, y2;
    private final int minSpeed;
    private final int maxSpeed;
    private int gravity;
    private final int friction;
    private final int width;
    private final int height;
    private final int radius;
    public RollBounce (String propertyFileName) throws IOException { //gets the properties for the file
        ballConfig = new Properties();
        FileInputStream in = new FileInputStream(propertyFileName);
        ballConfig.load(in);
        tm = new Timer(Integer.parseInt(ballConfig.getProperty("timerDelay")), this);
        minSpeed = Integer.parseInt(ballConfig.getProperty("minspeed"));
        maxSpeed = Integer.parseInt(ballConfig.getProperty("maxspeed"));
        gravity = Integer.parseInt(ballConfig.getProperty("gravity"));
        friction = Integer.parseInt(ballConfig.getProperty("friction"));
        height = Integer.parseInt(ballConfig.getProperty("window_height"));
        width = Integer.parseInt(ballConfig.getProperty("window_width"));
        radius = Integer.parseInt(ballConfig.getProperty("ball_radius"));
        balls = new ListNode<>();
        colors = new ListNode<>();
        colors.add(Color.BLUE);
        colors.add(Color.ORANGE);
        colors.add(Color.MAGENTA);
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        colors.add(Color.YELLOW);
        for (int i = 0; i < Integer.parseInt(ballConfig.getProperty("balls")); i++) {
            addBall();
        }
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // Sets the color of the ball
        ListNode<Color> currentColorNode = colors.getNext();
        Color currentColor;
        ListNode<Ball> currentBallNode = balls.getNext();
        Ball currentBall;
        while (currentBallNode != null) {
            if (currentColorNode == null) {
                currentColorNode = colors.getNext();
            }
            currentBall = currentBallNode.getItem();
            currentColor = currentColorNode.getItem();
            g.setColor(currentColor);
            g.fillOval(currentBall.getX(), currentBall.getY(), radius, radius);
            currentBallNode = currentBallNode.getNext();
            currentColorNode = currentColorNode.getNext();
        }
        tm.start();
    }
    public void addBall() { //adds the ball by first checking the minSpeed and the Max
        Random random = new Random();
        int initialVertical = random.nextInt(maxSpeed-minSpeed) + minSpeed;
        int initialHorizontal = random.nextInt(maxSpeed-minSpeed) + minSpeed;
        int initialX = random.nextInt(width);
        int initialY = random.nextInt(height);
        Ball newBall = new Ball(initialX, initialY, initialVertical, initialHorizontal);
        balls.add(newBall);
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        ListNode<Ball> currentBallNode = balls.getNext(); //checks if the current ball is within the parameters of width and radius
        Ball currentBall;
        while (currentBallNode != null) {
            currentBall = currentBallNode.getItem();
            currentBall.setX(currentBall.getX() + currentBall.getHorizontalVelocity());
            if (currentBall.getX() <= 0) {
                currentBall.setX(0);
                slowBall(currentBall);
                currentBall.setHorizontalVelocity(-currentBall.getHorizontalVelocity());
            } else if (currentBall.getX() >= width - 2*radius) {
                currentBall.setX(width-2*radius);
                slowBall(currentBall);
                currentBall.setHorizontalVelocity(-currentBall.getHorizontalVelocity());
            }
            currentBall.setY(currentBall.getY() + currentBall.getVerticalVelocity());
            if (currentBall.getY() >= height-3*radius) {
                currentBall.setY(height-3*radius);
                slowBall(currentBall);
                currentBall.setVerticalVelocity(-Math.abs(currentBall.getVerticalVelocity()));
            }
            currentBall.setVerticalVelocity(currentBall.getVerticalVelocity()+gravity);
            currentBallNode = currentBallNode.getNext();
        }
        repaint();
    }

    private void slowBall(Ball ball) {
        int verticalVelocity = ball.getVerticalVelocity(); //gets the int value of the vertical ball velocity
        int horizontalVelocity = ball.getHorizontalVelocity();
        if (Math.abs(verticalVelocity) < friction) {
            verticalVelocity = 0;
        } else if (verticalVelocity > 0) {
            verticalVelocity -= friction;
        } else {
            verticalVelocity += friction;
        }
        if (Math.abs(horizontalVelocity) < friction) {
            horizontalVelocity = 0;
        } else if (horizontalVelocity > 0) {
            horizontalVelocity -= friction;
        } else {
            horizontalVelocity += friction;
        }
        ball.setVerticalVelocity(verticalVelocity);
        ball.setHorizontalVelocity(horizontalVelocity);
    }
    public static void main(String[] args) { //main class to run the file
        try {
            RollBounce rb = new RollBounce(args[0]);
            JFrame jf = new JFrame();
            jf.setTitle("Roll Bounce");
            jf.setSize(rb.width, rb.height);
            jf.add(rb);
            jf.setVisible(true);
            jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        } catch (FileNotFoundException e) { //added the exception when filenotfound
            System.out.println("Invalid file argument");
        } catch (IOException e) {
            System.out.println("Invalid properties file");
        }

    }
}

