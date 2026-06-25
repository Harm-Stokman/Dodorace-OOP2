import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

/**
 *
 * @author Sjaak Smetsers & Renske Smetsers-Weeda
 * @version 3.1 -- 29-07-2017
 */
public class MyDodo extends Dodo
{

    public MyDodo() {
        super( EAST );
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
     * Test if Dodo can move forward, 
     * i.e. there are no obstructions or end of world in the cell in front of her.
     * 
     * <p> Initial:   Dodo is somewhere in the world
     * <p> Final:     Same as initial situation
     * 
     * @return  boolean true if Dodo can move (thus, no obstructions ahead)
     *                  false if Dodo can't move
     *                      there is an obstruction or end of world ahead
     */
    public boolean canMove() {
        if ( borderAhead() || fenceAhead() ){
            return false;
        } else {
            return true;
        }
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
            nrStepsTaken++;                 // increment the counter
        }
    }

    /**
     * Places all the Egg objects in the world in a list.
     * 
     * @return List of Egg objects in the world
     */
    public List<Egg> getListOfEggsInWorld() {
        return getWorld().getObjects(Egg.class);
    }

    public List<Integer> createListOfNumbers() {
        return new ArrayList<> (Arrays.asList( 2, 43, 7, -5, 12, 7 ));
    }

    /**
     * Method for praciticing with lists.
     */
    public void practiceWithLists( ){
        List<Integer> listOfNumbers = createListOfNumbers();
        
        //the following is incorrect and is to be fixed in challenge 6.1c
        System.out.println("First element: " + listOfNumbers.get(1) ); 
    }

    public void practiceWithListsOfSurpriseEggs( ){
        List<SurpriseEgg>  listOfEgss = SurpriseEgg.generateListOfSurpriseEggs( 12, getWorld() );
    }
    
    /**
     * makes a list of 10 surprise eggs
     */
    public List<SurpriseEgg> makeListOfSurpriseEggs() {
        List<SurpriseEgg> SurpriseEggList = SurpriseEgg.generateListOfSurpriseEggs(10, getWorld());
        return SurpriseEggList;
    }
    
    /**
     * prints of coordinates of an egg
     */
    public void printCoordinatesOfEgg(Egg egg) {
        System.out.println(egg.getX() +" "+ egg.getY());
    }
    
    /**
     * Makes a list of 10 surprise  eggs and prints every egg's coordinates
     */
    public void makeListOfSurpriseEggsAndPrintCoordinates() {
        for (Egg SurpriseEgg : makeListOfSurpriseEggs()) {
            printCoordinatesOfEgg(SurpriseEgg);
        }
    }
    
    /**
     * makes a list of 10 eggs and checks what egg has the highest value
     */
    public void getMostValuedEgg() {
        int highestValueEgg = 0;
        int indexOfHeighestEgg = -1;
        
        for (Egg SurpriseEgg : makeListOfSurpriseEggs()) {
            printCoordinatesOfEgg(SurpriseEgg);
            if (SurpriseEgg.getValue() > highestValueEgg) {
                System.out.println(SurpriseEgg.getValue());
                highestValueEgg = SurpriseEgg.getValue();
            }
        }
        System.out.println(highestValueEgg);
    }
    
    /**
     * makes a list of 10 eggs and calculates the average egg value
     */
    public double getAverageValue() {
        int total = 0;
        double size = 0;
         for (Egg SurpriseEgg : makeListOfSurpriseEggs()) {
             total = total + SurpriseEgg.getValue();
             size++;
        }
        

        double average = total / size;
        return average;
    }
}
