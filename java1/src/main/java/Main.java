import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        BufferedReader sourceReader = new BufferedReader(new FileReader(new File("../source")));

        List numberVector = new Vector();
        String line = sourceReader.readLine();
        while (line != null) {
            numberVector.add(line);
            line = sourceReader.readLine();
        }

        int[] numbers = new int[numberVector.size()];
        for (int i = 0, size = numberVector.size(); i < size; i++) {
            numbers[i] = Integer.parseInt((String) numberVector.get(i));
        }
        quicksort(numbers, 0, numbers.length - 1);

        Dictionary occurrences = new Hashtable();
        for (int i = 0; i < numbers.length; i++) {
            Integer key = new Integer(numbers[i]);
            Integer oldValue = (Integer) occurrences.get(key);
            int count = 1 + (oldValue == null ? 0 : oldValue.intValue());
            occurrences.put(key, new Integer(count));
        }

        Enumeration enumeration = occurrences.keys();
        while (enumeration.hasMoreElements()) {
            Integer key = (Integer) enumeration.nextElement();
            Integer value = (Integer) occurrences.get(key);
            System.out.println(key + "=" + value);
        }
    }

    private static void quicksort(int arr[], int left, int right) {
        int index = partition(arr, left, right);
        if (left < index - 1)
            quicksort(arr, left, index - 1);
        if (index < right)
            quicksort(arr, index, right);
    }

    private static int partition(int arr[], int left, int right) {
        int i = left, j = right;
        int pivot = arr[(left + right) / 2];

        while (i <= j) {
            while (arr[i] < pivot)
                i++;

            while (arr[j] > pivot)
                j--;

            if (i <= j) {
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                i++;
                j--;
            }
        }
        return i;
    }
}
