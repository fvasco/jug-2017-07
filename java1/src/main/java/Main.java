import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

public class Main {

    public static void main(String[] args) throws IOException {
        int[] numbers = loadFile("../source");

        quicksort(numbers, 0, numbers.length - 1);

        Dictionary occurrences = occurrences(numbers);

        Enumeration enumeration = occurrences.keys();
        while (enumeration.hasMoreElements()) {
            Object key = enumeration.nextElement();
            Object value = occurrences.get(key);
            System.out.println(key + "=" + value);
        }
    }

    private static int[] loadFile(String path) throws IOException {
        Vector intVector = new Vector();
        StringBuffer stringBuffer = new StringBuffer();
        InputStream inputStream = new FileInputStream(new File(path));
        while (inputStream.available() > 0) {
            int b = inputStream.read();
            if (b == '\n') {
                if (stringBuffer.length() > 0) {
                    intVector.addElement(stringBuffer.toString());
                }
                stringBuffer.setLength(0);
            } else {
                stringBuffer.append((char) b);
            }
        }

        int[] ints = new int[intVector.size()];
        for (int i = 0, max = ints.length; i < max; i++) {
            ints[i] = Integer.parseInt((String) intVector.elementAt(i));
        }
        return ints;
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

    private static Dictionary occurrences(int[] orderedNumbers) {
        Dictionary occurrences = new Hashtable();
        if (orderedNumbers.length > 0) {
            int i = 0;
            while (i < orderedNumbers.length) {
                int number = orderedNumbers[i];
                int count = 1;
                while (++i < orderedNumbers.length && number == orderedNumbers[i]) {
                    count++;
                }
                occurrences.put(new Integer(number), new Integer(count));
            }
        }
        return occurrences;
    }
}
