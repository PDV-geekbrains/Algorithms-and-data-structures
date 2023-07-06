import java.util.Arrays;

public class task_02_01 {
    public static void main(String[] args) {
        int arrayLength = 10;
        int[] arrayToSort = GetUnsortedArray(arrayLength);
        
        System.out.println("Unsorted arrey: " + Arrays.toString(arrayToSort));
        
        arrayToSort = HeapSort(arrayToSort);
        System.out.println("Sorted array: " + Arrays.toString(arrayToSort));
    }
    
    static int[] GetUnsortedArray(int arrayLength) {
        int[] arr = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            arr[i] = (int) (Math.random() * 10);
        }
        return arr;
    }
    
    static int[] HeapSort(int[] arrayToSort) {
        int n = arrayToSort.length;
        // Строим кучу (перегруппировываем массив).
        for (int parentIndex = n / 2 - 1; parentIndex >= 0; parentIndex--) {
            // Перемещаем в на позицию родителя наибольшее
            // из значений его потомков.
            Heapify(arrayToSort, n, parentIndex);
        }

        for (int i = n - 1; i >= 0; i--) {
            int tmp = arrayToSort[0];
            arrayToSort[0] = arrayToSort[i];
            arrayToSort[i] = tmp;

            System.out.println("Current root is moved to the end:");
            System.out.println(Arrays.toString(arrayToSort));

            Heapify(arrayToSort, i, 0);
        }

        return arrayToSort;
    }

    static void Heapify(int[] array, int n, int i) {
        int largest = i;
        int l = i * 2 + 1;
        int r = i * 2 + 2;

        // Сравниваем родителя с левым потомком.
        if (l < n && array[largest] < array[l])
            largest = l;

        // Сравниваем родителя с правым потомком.
        if (r < n && array[largest] < array[r])
            largest = r;

        // Если mostLargeIndex изменился.
        if (largest != i) {
            System.out.printf("Swap value[index]: %d[%d] <---> %d[%d]\n",
                    array[i], i,
                    array[largest], largest);

            int tmp = array[i];
            array[i] = array[largest];
            array[largest] = tmp;
            System.out.println(Arrays.toString(array));

            Heapify(array, n, largest);
        }

        System.out.println("heapify: " + Arrays.toString(array));
    }
}