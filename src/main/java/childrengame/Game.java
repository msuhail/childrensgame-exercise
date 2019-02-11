package childrengame;

import childrengame.exceptions.InvalidChildrenGameInputException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


/**
 * In order for the program to utilize minimal memory and provide better performance ArrayList was chosen instead of LinkedList.
 * ArrayList provides:
 * Add:    O(1)
 * Get:    O(1)
 * Remove: O(N)
 * <p>
 * Even though the remove operation of ArrayList O(N) takes longer in comparison to insert or retrieval, it is still better than LinkedList
 * which uses a search operation internally before executing the delete operation. The removeChild method was designed to be a tail
 * recursive function which helps with the overall performance of the program in comparison to using a loop function with respect to
 * the user story.
 * <p>
 * The program was designed to avoid nested iteration which leads to O(N^2) performance and hence with only one iteration loop the runtime
 * order complexity of the function is O(N)
 *
 * @author Mohamed Suwaibith Suhail
 */

public class Game {

    private static final int ZERO = 0;
    private static final Logger logger = LoggerFactory.getLogger(Game.class);

    /**
     * This is the static method that is called to initialize the game using parameter numOfChildren which represents 'n'
     * and removedAt which represents 'k' respectively.
     *
     * @param numOfChildren number of children that are participating in the game.
     * @param removedAt     the position of the child in the circle to be removed from the game.
     * @return an array list which contains the order of the sequence of the children eliminated from the game and winner who is
     * represented as the last element in the array list.
     * @throws InvalidChildrenGameInputException when the number of children or the constant(removeAt) is less than or equal
     *                                           to zero.
     */
    public static ArrayList<Integer> printSequenceAndWinner(int numOfChildren, int removedAt) throws InvalidChildrenGameInputException {
        if (numOfChildren > 0 && removedAt > 0) {
            ArrayList<Integer> childrenArray = new ArrayList<>(numOfChildren);
            ArrayList<Integer> resultOfGame = new ArrayList<>();

            childrenArray.addAll(IntStream.rangeClosed(1, numOfChildren).boxed().collect(Collectors.toList()));
            removeChild(childrenArray, resultOfGame, ZERO, removedAt);
            return resultOfGame;
        } else {
            throw new InvalidChildrenGameInputException(numOfChildren, removedAt);
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            printSequenceAndWinner(5, 1);
        } catch (InvalidChildrenGameInputException ex) {
            logger.error("Invalid number of children '{}' or '{}'", ex.getNumberOfChildren(), ex.getRemovedAt());
        }
    }

    /**
     * This is a recursive function that continues to execute until the all the children in the circle has been eliminated except
     * for the winner. The winner is the last element that is added to the resultOfGame ArrayList.
     *
     * @param childrenInCircle is an array list that contains the list of children's Ids sequentially.
     * @param resultOfGame     is an array list of all children removed from the game and the winner which is represented as the last element.
     * @param startingIndex    represents the position of the student in the circle.
     * @param removeAt         the position of the child in the circle to be removed from the game.
     * @return returns the next child's position after elimination.
     */
    private static int removeChild(ArrayList<Integer> childrenInCircle, ArrayList<Integer> resultOfGame, int startingIndex, int removeAt) {
        if (childrenInCircle.size() == 1) {
            int winner = childrenInCircle.remove(ZERO);
            logger.info("Winner: {}", winner);
            resultOfGame.add(winner);
            return winner;
        } else {
            for (int count = 1, childIndex = startingIndex; count <= removeAt; count++, childIndex++) {
                if (childIndex == childrenInCircle.size()) {
                    childIndex = 0;
                }

                if (count == removeAt) {
                    int removedChildId = childrenInCircle.remove(childIndex);
                    logger.info("Removed: {}", removedChildId);
                    resultOfGame.add(removedChildId);
                    if (childIndex < childrenInCircle.size()) {
                        return removeChild(childrenInCircle, resultOfGame, childIndex, removeAt);
                    }
                }
            }
            return removeChild(childrenInCircle, resultOfGame, ZERO, removeAt);
        }
    }

}
