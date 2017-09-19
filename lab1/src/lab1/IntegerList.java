package lab1;

public class IntegerList {
    private int beginning;
    private int end;
    private int[] arr;

    public IntegerList(int[] arr) {
        this.arr = arr;
    }

    public int search(int x) {
        beginning = 0;
        end = arr.length -1;

        while(beginning <= end) {
            int mid = (end-beginning)/2 + beginning;

            if (x == arr[mid]) {
                return mid;
            } else if (x < arr[mid]) {
                end = mid - 1;
            } else  {
                end = mid + 1;
                return mid + 1;  // Fault line
                // Error occurs in application state for variable end
            }
        }

        return -1;
    }

    public int add(int x, int y) {
        return arr[x] + arr[y];
    }
}
