import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 *
 * @author Sjaak Smetsers & Renske Smetsers-Weeda
 * @version 3.0 -- 20-01-2017
 */
public class MyDodo extends Dodo
{
    private int myNrOfEggsHatched;
    
    public MyDodo() {
        super( EAST );
        myNrOfEggsHatched = 0;
    }

    public void act() {
    }

    /**
     * Move one cell forward in the current direction.
     * 
     * <P> Initial: Dodo is somewhere in the world
     * <P> Final: If possible, Dodo has moved forward one cell
     *
     */
    public void move() {
        if ( canMove() ) {
            step();
        } else {
            showError( "I'm stuck!" );
        }
    }

    /**
     * Test if Dodo can move forward, (there are no obstructions
     *    or end of world in the cell in front of her).
     * 
     * <p> Initial: Dodo is somewhere in the world
     * <p> Final:   Same as initial situation
     * 
     * @return boolean true if Dodo can move (no obstructions ahead)
     *                 false if Dodo can't move
     *                      (an obstruction or end of world ahead)
     */
    public boolean canMove() {
        if ( borderAhead() || fenceAhead() ){
            return false;
        } else {
            return true;
        }
    }

    /**
     * Hatches the egg in the current cell by removing
     * the egg from the cell.
     * Gives an error message if there is no egg
     * 
     * <p> Initial: Dodo is somewhere in the world. There is an egg in Dodo's cell.
     * <p> Final: Dodo is in the same cell. The egg has been removed (hatched).     
     */    
    public void hatchEgg () {
        if ( onEgg() ) {
            pickUpEgg();
            myNrOfEggsHatched++;
        } else {
            showError( "There was no egg in this cell" );
        }
    }
    
    /**
     * Returns the number of eggs Dodo has hatched so far.
     * 
     * @return int number of eggs hatched by Dodo
     */
    public int getNrOfEggsHatched() {
        return myNrOfEggsHatched;
    }
    
    /**
     * Move given number of cells forward in the current direction.
     * 
     * <p> Initial:   
     * <p> Final:  
     * 
     * @param   int distance: the number of steps made
     */
    public void jump( int distance ) {
        int nrStepsTaken = 0;               // set counter to 0
        while ( nrStepsTaken < distance ) { // check if more steps must be taken  
            move();                         // take a step
            nrStepsTaken++; 
            System.out.println("Moved," + nrStepsTaken); // increment the counter
        }
    }

    
    
    
    /**
     * Walks to edge of the world printing the coordinates at each step
     * 
     * <p> Initial: Dodo is on West side of world facing East.
     * <p> Final:   Dodo is on East side of world facing East.
     */

    public void walkToWorldEdge( ){
        while( ! borderAhead() ){
            move();
        }
    }

    /**
     * Test if Dodo can lay an egg.
     *          (there is not already an egg in the cell)
     * 
     * <p> Initial: Dodo is somewhere in the world
     * <p> Final:   Same as initial situation
     * 
     * @return boolean true if Dodo can lay an egg (no egg there)
     *                 false if Dodo can't lay an egg
     *                      (already an egg in the cell)
     */

    public boolean canLayEgg( ){
       if( onEgg() ){
            return false;
        }else{
            return true;
        }
    }  
    
    
    /**
     * Dodo turns 180 degrees, facing the opposite direction.
     */
    public void turn180() {
        turnRight();
        turnRight();
    }

    /**
     * Initial situation: Dodo is facing a fence on his right side.
     * Final situation: Dodo is on the other side of the fence, with the fence to his back.
     
     * Dodo first rotates, facing upward, then taking a step forward, and then 
     * rotates normally. Then taking 2 steps,then rotating downwards and take 
     * 1 step more.finally, the dodo rotates once more to have the same rotation
     * as the initial situation.
     */
    public void climbOverFence() {
            if (fenceAhead()) {
            turnLeft();
            move();
            turnRight();
            move();
            move();
            turnRight();
            move();
            turnLeft();
       } else {
           System.out.println("No fence to climb!");
       }
    }
    
    /**
     * Dodo turns around 180 degrees, then moving one step. After that the 
     * dodo turns again, to the original direction.
     */
    public void stepOneCellBackwards() {
        turn180();
        move();
        turn180();
    }
    
    /**
     * dodo moves forward, checking if there is grain on his position, then 
     * then takes a step back, returning a value that is true / false
     */
    public boolean grainAhead() {
        move();
        if (onGrain()) {
           stepOneCellBackwards();
            return true;
        } else {
            stepOneCellBackwards(); 
            return false;
        }
    }
    
    /**
     * Initial situation: egg lies ... cells ahead of the dodo. The cells 
     * between are empty.
     * 
     * Final situation: Dodo is standing still on top of the egg.
     */
    public void goToEgg() {
        while (!onEgg()) {
            move();
        }
    }
    
    /**
     * Dodo walks to the world border, then turn to face his original position
     */
    public void goBackToStartOfRowAndFaceBack() {
        walkToWorldEdge();
        turn180();
    }
    
    /**
     * Dodo walks to the world border while also checking if there are fences
     * ahead. If there is a fence ahead, the dodo will climb over the fence 
     * and continue his path to the world border. 
     * 
     * The dodo will also check if there are empty nests. if so, it will lay
     * an egg
     */
    public void walkToWorldEdgeClimbingOverFences() {
        while (!borderAhead()) {
            if (fenceAhead()) {
                climbOverFence();
            } else {
                move();
            }
            
            if (onNest()) {
                if (!onEgg()) {
                    layEgg();
                }
            }
        }
    }
    
    /**
     * if the dodo picks up a grain, the coordinates of the grain will be
     * printed in the console.
     */
    public void pickUpGrainsAndPrintCoordinates() {
        while (!borderAhead()) {
            if (onGrain()) {
                pickUpGrain();
                System.out.println("X = " + super.getX() + " " + "Y = " + 
                super.getY());
            }
            move();
        }
        if (onGrain()) {
                pickUpGrain();
                System.out.println("X = " + super.getX() + " " + "Y = " + 
                super.getY());
            }
    }
    
    /**
     * The dodo will walk to the world border while checking empty nests on
     * his path. if there is an empty nest, the dodo will lay an egg.
     */
    public void walkToWorldEdgeWhileCheckingNests() {
        while (!borderAhead()) {
            move();
            if (onNest()) {
                if (!onEgg()) {
                    layEgg();
                }
            }
        }
    }
    
    /**
     * Dodo checks if there is a path under him. If not, it will turn back and
     * forward. if so, it will go along the fence and keeps checking 
     * directions to go to. the dodo will stop walking around the fence until
     * the egg is reached.
     */
    public void walkAroundFencedArea() {
        while (!onEgg()) {
            move();
            turnRight();
            while (fenceAhead()) {
                turnLeft(); 
            }
        }
    }
}

