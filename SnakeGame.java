/**
 * Snake game
 * by
 * Alex Zaurov
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 * Current:
 * 1) create poison similar hitting the wall, use hitTHeWall CHECKED
 *  a) using the food init to create poison CHECKED
 * 2) The snake does die when it bumps into itself CHECKED
 * 3) when the snake grows after eating food CHECKED
 * 4) create multiple food at once - CHECKED
 * 5) After snake eats 20 pellets, message pops up saying YOU WON  - CHECKED
 *
 * Extra credit:
 * 1) Poison chasing the snake CHECKED
 * 2) Poison comes from food
 * 3) Poison respawns after eating food CHECKED
 *

 */
public class SnakeGame extends JFrame {
    // A snake is just a list of coordinates (java.util.LinkedList, not our List)
    private LinkedList<Coordinate> snake = new LinkedList<Coordinate>();
    // The snake grows when it eats food
    private LinkedList<Coordinate> food = new LinkedList<Coordinate>();
    // The poison grows when it eats food
    private LinkedList<Coordinate> poison = new LinkedList<Coordinate>();
    // The game is on or over
    private static enum Game { ON, WON, OVER};
    private Game status = Game.ON;

    // Repeatedly moves the snake
    private Timer timer;



    // Counter
    private int food_counter = 0;

    // The snake can move in one of 4 directions
    public static enum Direction { UP, DOWN, LEFT, RIGHT };
    // The snake's current direction (heading). Default: moving right
    private Direction heading = Direction.RIGHT;

    // The snake can't switch to the opposite direction
    public boolean oppositeDirection(Direction newHeading) {
        return (heading == Direction.UP && newHeading == Direction.DOWN) ||
                (heading == Direction.DOWN && newHeading == Direction.UP) ||
                (heading == Direction.LEFT && newHeading == Direction.RIGHT) ||
                (heading == Direction.RIGHT && newHeading == Direction.LEFT);
    }

    // Update the heading based on the new heading
    public void changeHeading(Direction newHeading) {
        if (!oppositeDirection(newHeading)) {
            heading = newHeading;
        }
    }

    // Handle keyboard input (arrows change the snake's heading)
    private class KeyControl implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {}

        @Override
        public void keyPressed(KeyEvent e) {
            Direction newHeading = heading;

            switch(e.getKeyCode()) {
                case KeyEvent.VK_LEFT: case KeyEvent.VK_KP_LEFT:
                    newHeading = Direction.LEFT; break;
                case KeyEvent.VK_RIGHT: case KeyEvent.VK_KP_RIGHT:
                    newHeading = Direction.RIGHT; break;
                case KeyEvent.VK_UP: case KeyEvent.VK_KP_UP:
                    newHeading = Direction.UP; break;
                case KeyEvent.VK_DOWN: case KeyEvent.VK_KP_DOWN:
                    newHeading = Direction.DOWN; break;
            }
            changeHeading(newHeading);
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    }

    // An (x,y) coordinate in a 64 by 48 grid
    public static class Coordinate {
        public int x;
        public int y;
        // By default, construct a random coordinate not too far from the wall
        Coordinate() {
            this.x = new Random().nextInt(60) + 2;
            this.y = new Random().nextInt(40) + 2;
        }
        Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }
    @Override
    // This view renders the snake, food, and poison
    // Each snake coordinate is a 10x10 pixel square
    public void paint(Graphics g) {
        g.clearRect(0, 0, 640, 480);
        Color green = new Color(0,128,0);
        g.setColor(green);

        for (Coordinate c : snake) {
            g.fillRect(c.x*10, c.y*10, 10, 10);
        }
        g.setColor(Color.BLUE);
        for (Coordinate c: food) {
            g.fillOval(c.x*10, c.y*10, 10, 10);
        }
        g.setColor(Color.RED);
        for(Coordinate c: poison) {
            g.fillOval(c.x*10, c.y*10, 10, 10);
        }


    }

    // The snake's heading determines its new head coordinate
    private Coordinate newHead() {
        Coordinate head, newHead;
        head = snake.getFirst();

        switch (heading) {
            case DOWN: newHead = new Coordinate(head.x, head.y + 1); break;
            case LEFT: newHead = new Coordinate(head.x - 1, head.y); break;
            case RIGHT: newHead = new Coordinate(head.x + 1, head.y); break;
            case UP: newHead = new Coordinate(head.x, head.y - 1); break;
            // The default case is never reached because we have only 4 events.
            default: newHead = new Coordinate(); break;
        }
        return newHead;
    }

    // This method implements the poison chasing the snake
    private void poisonChase()
    {

        for(Coordinate c: poison) {
            if (c.x > snake.getFirst().x)
            {
                c.x = c.x - 1;         // If snake goes left, poison follows left
            }
            else if (c.x < snake.getFirst().x)
            {
                c.x = c.x + 1;          // If snake goes right, poison follows right
            }

            if ( c.y > snake.getFirst().y)
            {
                c.y = c.y - 1;          // If snake goes up, poison follows up
            }
            else if (c.y < snake.getFirst().y)
            {
                c.y = c.y + 1;          // If snake goes down, poison follows down
            }
        }
    }

    // When the snake moves, it can hit the wall, hit the food, poison or itself
    public void move() {
        Coordinate newHead = newHead();

        poisonChase();  //calling on poison chase method

        if (hitTheWall(newHead) || EatPoison(newHead) || HitSelf(newHead)) {        // added EatPoison & HitSelf
            status = Game.OVER;
            return; // will return back to where this method is called
        }

        snake.addFirst(newHead);

        int increaseSpeed = timer.getDelay();  //declaring the increment variable that will increase the speed of
                                                // the snake after each time it eats the pellets


        /**
         * if condition where every time the snake eats food, new food elements appear, and counted every time
         * the snake eats the food.  Poison is also cleared, and then 2 poison elements are added to random locations.
          */

        if (hitTheFood(newHead)) {
            food.add(new Coordinate());
            food_counter++;
            increaseSpeed = increaseSpeed - 10;  // increment speed of the snake
            timer.setDelay(increaseSpeed);
            poison.clear();
            for (int i = 0; i < 2; i++)
            {
                poison.add(new Coordinate());
            }
        } else {
            snake.removeLast();
        }
        if (food_counter == 20)   // Once the players eats 20 pellets the game is won
        {
            status = Game.WON;
        }


    }

    // creating a boolean method that tests to see if the food is eaten
    private boolean hitTheFood(Coordinate newHead) {
        for(Coordinate c: food)
        {
            if(newHead.x == c.x && newHead.y == c.y)
            {
                food.remove(c);  //removing the object that is holding all of the foods

                return true;
            }
        }
        return false;

    }

    // creating boolean method that tests to see if the snake hit the wall
    public boolean hitTheWall(Coordinate head) {
        return (head.x == 64 || head.y == 48 || head.x == 0 || head.y == 0);
    }

    // creating a boolean method that tests to see if the snake eats the poison or if the poison reaches the snakes head
    // or tail
    public boolean EatPoison(Coordinate head) {
        for(Coordinate p: poison)
        {
            if((head.x == p.x && head.y == p.y) || (snake.getLast().x == p.x && snake.getLast().y == p.y))
            {
                return true;
            }

        }
        return false;

    }

    // creating a boolean method that tests to see if the snake hits itself
    public boolean HitSelf(Coordinate head) {

        for(Coordinate s: snake)
        {
            if(head.x == s.x && head.y == s.y)
            {
                return true;
            }
        }
        return false;
    }

    // The timer moves the snake using this class.
    private class SnakeMover implements ActionListener {
        @Override
        // Listening Action (in this case Timer - every certain millisecond) and execute this method
        public void actionPerformed(ActionEvent e) {
            move();
            repaint();	// from AWT library. It will call paint() automatically
            if (status == Game.OVER) {
                playAgain("The snake's dead");
            }
            else if (status == Game.WON) {
                playAgain("Congrats! You won! ☺");
            }
        }
    }

    // Ask the player what to do when the game is over
    private void playAgain(String message) {
        String[] options = new String[] {"Play again","Quit"};
        int choice = JOptionPane.showOptionDialog(null, message, "Game over", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,null,options,options[0]);

        if (choice == 0) {
            initialize();
        } else {
            System.exit(0);
        }
    }

    // Initialize game (snake, food, etc)
    private void initialize() {
        status = Game.ON;

        // Make a small snake with 1 node (a 10x10 pixel coordinate)

        snake.clear(); // remove all of the elements of the LinkedList
        food.clear(); // remove all of the food elements
        poison.clear();  // remove all of the poison elements
        timer.setDelay(150); // resetting the timer back to default
        food_counter = 0;    // resetting the food counter back to zero
        snake.add(new Coordinate()); // append the new element to the end of the LinkedList


        for(int i = 0; i < 4; i++) {
            food.add(new Coordinate());  // creates 4 pellets of food
        }

        for(int i = 0; i < 2; i++) {
            poison.add(new Coordinate());  // creates 2 poison elements
        }

    }
    public SnakeGame() {

        setSize(640, 480);	// Window size - pixel
        setTitle("Snake Game");
        setVisible(true);

        // Update the snake's direction using keyboard arrows
        // Event Handler: addKeyListener is from AWT library. This is how to "register" event
        addKeyListener(new KeyControl());

        // Make the snake move every 150 milliseconds
        timer = new Timer(150, new SnakeMover());
        timer.start();


        // Initialize game (snake, food, poison)
        initialize();

    }

    public static void main(String[] args) {
        new SnakeGame();
    }
}
