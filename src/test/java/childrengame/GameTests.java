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

                {new TestDataForGame("Removed: 1 2 3, Winner: 4", 4, 1)},
                {new TestDataForGame("Removed: 2 4 3, Winner: 1", 4, 2)},
                {new TestDataForGame("Removed: 3 2 4, Winner: 1", 4, 3)},
                {new TestDataForGame("Removed: 4 1 3, Winner: 2", 4, 4)},
                {new TestDataForGame("Removed: 1 3 4, Winner: 2", 4, 5)},
                {new TestDataForGame("Removed: 2 1 4, Winner: 3", 4, 6)},
                {new TestDataForGame("Removed: 3 4 1, Winner: 2", 4, 7)},
                {new TestDataForGame("Removed: 4 2 1, Winner: 3", 4, 8)},

                {new TestDataForGame("Removed: 1 2 3 4, Winner: 5", 5, 1)},
                {new TestDataForGame("Removed: 2 4 1 5, Winner: 3", 5, 2)},
                {new TestDataForGame("Removed: 3 1 5 2, Winner: 4", 5, 3)},
                {new TestDataForGame("Removed: 4 3 5 2, Winner: 1", 5, 4)},
                {new TestDataForGame("Removed: 5 1 3 4, Winner: 2", 5, 5)},

                {new TestDataForGame("Removed: 5 10 6 2 9 8 1 4 7, Winner: 3", 10, 5)},
                {new TestDataForGame("Removed: 1 3 6 10 8 9 5 2 4, Winner: 7", 10, 11)},
                {new TestDataForGame("Removed: 10 2 6 4 7 5 3 9 8, Winner: 1", 10, 20)},
                {new TestDataForGame("Removed: 1 2 3 4 5 6 7 8 9, Winner: 10", 10, 1)},

                {new TestDataForGame("Removed: 1, Winner: 2", 2, 1)},
                {new TestDataForGame("Removed: 2, Winner: 1", 2, 2)},
        };
    }


    @Test(dataProvider = "getData", priority = 1)
    public void testPrintSequenceAndWinner(TestDataForGame testDataForGame) throws InvalidChildrenGameInputException {
        Assert.assertEquals(Game.printSequenceAndWinner(testDataForGame.numOfChildren, testDataForGame.removeAt), testDataForGame.result);
    }

    private final class TestDataForGame {
        private String result;
        private int numOfChildren;
        private int removeAt;

        public TestDataForGame(String result, int numOfChildren, int removeAt) {
            this.result = result;
            this.numOfChildren = numOfChildren;
            this.removeAt = removeAt;
        }
    }
}
