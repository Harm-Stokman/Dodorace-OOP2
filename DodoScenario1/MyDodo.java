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

    
    /**
     * Dodo keeps looking if an egg is in front of her, and turns to the right 
     * when there is no egg in front of her. search stops after standing on
     * her nest
     */
    public void eggTrailToNest() {
        while (!onNest()) {
            if (eggAhead() || nestAhead()) {
                move();
            } else {
                turnRight();
                if (!eggAhead()) {
                    turn180();
                } 
            }
            
        }
    }
    
    /**
     * dodo keeps checking if she can go right. doing this, she follows the 
     * right side until she reaches her nest.
     */
    public void walkToNestInMaze() {
        while (!onNest()) {
            turnRight();
            if (canMove()) {
                move();
            } else {
                while (!canMove()) {
                    turnLeft();
                }
                move();
            }
        }
        showCompliment("Well done!");
    }
    
    
    /**
     * dodo faces direction depending on the input. can be north, east, south,
     * west.
     */
    public void faceDirection(int direction) {
        if (direction >= 0 && direction <= 3) {
            while (getDirection() != direction) {
            turnRight();
        }
        } else {
            System.out.println("Invalid number");
        }
    }
    
    /**
     * The value of temporaryValue is set to the value of BlueEgg.
     * The value of blueEgg is set to the value of GoldenEgg.
     * The value of goldenEgg is set to the value of temporaryValue
     */
    public void changeEggValue() {
        BlueEgg blueEgg = new BlueEgg();
        GoldenEgg goldenEgg = new GoldenEgg();
        
        System.out.println(blueEgg.getValue());
        System.out.println(goldenEgg.getValue());
        
        int temporaryValue = blueEgg.getValue();
        
        blueEgg.setValue(goldenEgg.getValue());
        goldenEgg.setValue(temporaryValue);
        
        System.out.println(blueEgg.getValue());
        System.out.println(goldenEgg.getValue());
    }
    
    
    /**
     * this will let the dodo know when to stop.
     */
    public boolean locationReached(int x, int y) {
        return getX() == x && getY() == y;
    }
    
    /**
     * checks if the submitted coordinates are valid and not out of bounds.
     */
    public boolean validCoordinates(int x, int y) {
        int height = getWorld().getHeight();
        int width = getWorld().getWidth();
        return x >= 0 && x <= width && y >= 0 && y <= height;
    }
    
    /**
     * the dodo will travel to a location based on submitted coordinates.
     */
    public void goToLocation(int coordX, int coordY) {
        if (validCoordinates(coordX, coordY)) {
            while (!locationReached(coordX, coordY)) {
        if (getX() < coordX) {
            faceDirection(1);
            move();
        } else if (getX() > coordX) {
            faceDirection(3);
            move();
        } else if (getY() < coordY) {
            faceDirection(2);
            move();
        } else if (getY() > coordY) {
            faceDirection(0);
            move();
        } 
        faceDirection(1);
        }
        } else {
             showError("Invalid coordinates");
        }
    }
    
    /**
     * The dodo walks forward until she reaches the edge while counting eggs 
     * on the same row and walks back to her starting position when finished.
     */
    public int countEggsInRow() {
        int eggAmount = 0;
         if (onEgg())  {
            eggAmount++; 
            }
        while (!borderAhead()) {
            if (onEgg())  {
            eggAmount++; 
            }
            move();
        }
        turn180();
        goBackToStartOfRowAndFaceBack();    
        return eggAmount;
    }
    
    /**
     * Dodo lays eggs of the same amount of the input
     */
    
    public void layTrailOfEggs(int n) {
        if (n <= getWorld().getWidth()) {
            for (int index = 0; index < n; index++) {
            if (!onEgg()) {
            layEgg();
            }
            if (borderAhead()) {
                if (!onEgg()) {
                    layEgg();
                }
                index = n;
            }
            if (canMove()) {
                move();
            }
        }
        
        } else {
            showError("Invalid number.");
        }
    }
    
    /**
     * Dodo counts all eggs in the rows and gives a total amount of eggs back
     */
    public void countEggsInWorld() {
        int eggCount = 0;
        for (int index = 0; index < getWorld().getHeight(); index++) {
            eggCount = eggCount + countEggsInRow();
            turnRight();
            if (!borderAhead()) {
            move();
            turnLeft();
            }
        }
        goToLocation(0, 0);
        faceDirection(1);
        showCompliment("You collected " + eggCount + " " + "eggs.");
    }
    
    /**
     * Dodo counts every egg in a row. it checks if the total eggs in a row is
     * higher than the previous row. it does this for every row.
     */
    public void findRowWithMostEggs() {
        int highestRowCoords = 0;
        int highestEggAmount = 0;
        
        for (int index = 0; index < getWorld().getHeight(); index++) {
            int rowSearch = countEggsInRow();
            if (rowSearch >  highestEggAmount) {
                highestEggAmount = rowSearch;
                highestRowCoords = getY();
            } 
            turnRight();
            if (!borderAhead()) {
            move();
            turnLeft();
            }
        }
        
        goToLocation(0, 0);
        faceDirection(1);
        showCompliment("Row with the most eggs is: " + highestRowCoords
        + "\n" + "With " + highestEggAmount + " eggs.");
    }
    
    /**
     * dodo makes a stairs like shape, until she reaches a border
     */
    public void makeStairsWithEggs() {
        int startCoordsX = getX();
        int startCoordsY = getY();
        int height = getWorld().getHeight();
        int row = 0;
        while (row < height - startCoordsY) {
            goToLocation(startCoordsX, startCoordsY + row);
            faceDirection(1);
            layTrailOfEggs(row + 1);
            row++;
        }
        goToLocation(startCoordsX, startCoordsY);
    }
    
    /**
     * Dodo makes a stairs like shape, but doubles the amount of eggs on each 
     * row.
     */
    public void makeStairsWithEggsDoubled() {
        int startCoordsX = getX();
        int startCoordsY = getY();
        int height = getWorld().getHeight();
        int row = 0;
        int count = 1;
        while (row < height - startCoordsY && count <= getWorld().getWidth() -
        startCoordsX) {
            goToLocation(startCoordsX, startCoordsY + row);
            faceDirection(1);
            layTrailOfEggs(count);
            count = count * 2;
            row++;
        }
        goToLocation(startCoordsX, startCoordsY);
    }
    
    /**
     * Dodo creates a pyramid shaped object.
     */
    public void createPyramidWithEggs() {
        int startCoordsX = getX();
        int startCoordsY = getY();
        int height = getWorld().getHeight();
        int row = 0;
        while (row < height && startCoordsX - row >= 0) {
            goToLocation(startCoordsX - row, startCoordsY + row );
            faceDirection(1);
            layTrailOfEggs(2 * row + 1);
            row++;
        }
        goToLocation(startCoordsX, startCoordsY);
    }
    
    /**
     * dodo counts all eggs in all rows, then calculates the average amount 
     * of eggs per row based on how many rows there are in the world
     */
    public double calculateAverageEggs() {
        int rows = 0;
        double eggAmount = 0;
        while (rows < getWorld().getHeight()) {
             eggAmount = eggAmount + countEggsInRow();
             rows++;    
             turnRight();
             if (!borderAhead()) {
            move();
            turnLeft();
            }
        }
        
        double average = eggAmount / rows;
        
        return average;
    }
    
    public int getIncorrectRowNr(int direction) {
        if (direction == 1) {
            int number = getY();
            return number;
        } else {
            int number = getX();
            return number;
        }
    }
    
    public void goToIncorrectBit(int coordX, int coordY) {
        goToLocation(coordX, coordY);
    }
    
    public void fixIncorrectBit() {
        layEgg();
    }
    
    public void fixBrokenColumnsOrRows() {
        int height = getWorld().getHeight();
        int width = getWorld().getHeight();
        int incorrectY = 0;
        int incorrectX = 0;
        for (int heightIndex = 0; heightIndex < height; heightIndex++) {
            int rowResult = countEggsInRow() % 2;
            if (rowResult == 1) {
                incorrectY = getIncorrectRowNr(1);
                 System.out.println(incorrectY);
            }
             turnRight();
            if (!borderAhead()) {
                move();
                turnLeft();
            }
        }  
        goToLocation(0, 0);
        faceDirection(2);
        for (int widthIndex = 0; widthIndex < width; widthIndex++) {
            int columnResult = countEggsInRow() % 2;
            if (columnResult == 1) {
                incorrectX = getIncorrectRowNr(2);
                System.out.println(incorrectX);
            }
            turnLeft();
            if (!borderAhead()) {
                move();
                turnRight();
            }
        }
        goToIncorrectBit(incorrectX, incorrectY);
        fixIncorrectBit();
        goToLocation(0, 0);
    }
}