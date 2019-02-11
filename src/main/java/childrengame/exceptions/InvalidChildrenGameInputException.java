package childrengame.exceptions;

/**
 *
 * @author Mohamed Suwaibith Suhail
 *
 */

public class InvalidChildrenGameInputException extends Exception {

    private int numberOfChildren;
    private int removedAt;

    /**
     * @param numberOfChildren
     * @param removedAt
     */
    public InvalidChildrenGameInputException(final int numberOfChildren, final int removedAt) {
        this.numberOfChildren = numberOfChildren;
        this.removedAt = removedAt;
    }

    /**
     * @return
     */
    public int getNumberOfChildren() {
        return numberOfChildren;
    }

    /**
     * @return
     */
    public int getRemovedAt() {
        return removedAt;
    }
}
