package lab1;

import org.junit.Test;
import static org.junit.Assert.*;


public class IntegerListTest {

    /*
        One passes whenever the fault is not reached.
     */
    @Test
    public void testSearchIndexReturn() {
        int[] arr = {1, 2, 3, 4};
        IntegerList list = new IntegerList(arr);
        assertEquals(0, list.search(1));
        assertEquals(1, list.search(2));
    }

    /*
        One passes only if the fault is reached, but does not infect.
     */
    @Test
    public void testSummationOfTwoElements() {
        int[] arr = {1,2,3};
        IntegerList list = new IntegerList(arr);
        int oneIndex = list.search(1);
        int threeIndex = list.search(3);
        assertEquals(4, list.add(oneIndex, threeIndex));
    }

    /*
        One passes only if the fault is reached, infects, but does not propagate.
     */
    @Test
    public void testSummationWithDuplicates() {
        int[] arr = {1,2, 3, 3};
        IntegerList list = new IntegerList(arr);
        int oneIndex = list.search(1);
        int threeIndex = list.search(3);
        assertEquals(4, list.add(oneIndex, threeIndex));
    }

    /*
       One passes only if the fault is reached, infects, and propagates – i.e., you detect a failure in the output.
    */
    @Test
    public void testSummationError() {
        int[] arr = {1,2, 3, 4, 5};
        IntegerList list = new IntegerList(arr);
        int oneIndex = list.search(1);
        int fiveIndex = list.search(5);
        assertEquals(5, list.add(oneIndex, fiveIndex)); // 5 + 1 != 5
    }

    /*
        One passes only if the fault is not reached and the result is correct.(!R no other failure)
     */
    @Test
    public void testSummationWithElementsLessThanMiddleNumber() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        IntegerList list = new IntegerList(arr);
        int twoIndex = list.search(2);
        int threeIndex = list.search(3);
        assertEquals(5, list.add(twoIndex, threeIndex));
    }
}
