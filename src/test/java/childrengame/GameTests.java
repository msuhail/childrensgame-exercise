package childrengame;

import childrengame.exceptions.InvalidChildrenGameInputException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @author Mohamed Suwaibith Suhail
 */

public class GameTests {

    @Test(expectedExceptions = InvalidChildrenGameInputException.class)
    public void testInvalidChildrenGameInputException() throws InvalidChildrenGameInputException {
        Game.printSequenceAndWinner(0, 0);
    }

    @DataProvider
    public Object[][] getData() {
        return new Object[][]{

                {new TestDataForGame(new int[]{1, 2, 3, 4}, 4, 1)},
                {new TestDataForGame(new int[]{2, 4, 3, 1}, 4, 2)},
                {new TestDataForGame(new int[]{3, 2, 4, 1}, 4, 3)},
                {new TestDataForGame(new int[]{4, 1, 3, 2}, 4, 4)},
                {new TestDataForGame(new int[]{1, 3, 4, 2}, 4, 5)},
                {new TestDataForGame(new int[]{2, 1, 4, 3}, 4, 6)},
                {new TestDataForGame(new int[]{3, 4, 1, 2}, 4, 7)},
                {new TestDataForGame(new int[]{4, 2, 1, 3}, 4, 8)},

                {new TestDataForGame(new int[]{1, 2, 3, 4, 5}, 5, 1)},
                {new TestDataForGame(new int[]{2, 4, 1, 5, 3}, 5, 2)},
                {new TestDataForGame(new int[]{3, 1, 5, 2, 4}, 5, 3)},
                {new TestDataForGame(new int[]{4, 3, 5, 2, 1}, 5, 4)},

                {new TestDataForGame(new int[]{5, 10, 6, 2, 9, 8, 1, 4, 7, 3}, 10, 5)},
                {new TestDataForGame(new int[]{1, 3, 6, 10, 8, 9, 5, 2, 4, 7}, 10, 11)},
                {new TestDataForGame(new int[]{10, 2, 6, 4, 7, 5, 3, 9, 8, 1}, 10, 20)},
                {new TestDataForGame(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 10, 1)},

                {new TestDataForGame(new int[]{1, 2}, 2, 1)},
                {new TestDataForGame(new int[]{2, 1}, 2, 2)},

                {new TestDataForGame(new int[]{1}, 1, 1)},
        };
    }


    @Test(dataProvider = "getData", priority = 1)
    public void testPrintSequenceAndWinner(TestDataForGame testDataForGame) throws InvalidChildrenGameInputException {
        Assert.assertEquals(testDataForGame.result, Game.printSequenceAndWinner(testDataForGame.numOfChildren, testDataForGame.removeAt).toArray());
    }

    private final class TestDataForGame {
        private int[] result;
        private int numOfChildren;
        private int removeAt;

        public TestDataForGame(int[] result, int numOfChildren, int removeAt) {
            this.result = result;
            this.numOfChildren = numOfChildren;
            this.removeAt = removeAt;
        }
    }
}
