package question_three;

import org.student.Logging;

import java.util.Scanner;

public class GenericSwap {

    public static <T> void printArr(T[] array){
        for(T temp : array){
            Logging.logInfo(temp.toString());
        }
    }
    public static <T> void swapElements(T[] array, int index1, int index2) {
        if (array == null || index1 < 0 || index2 < 0 || index1 >= array.length || index2 >= array.length) {
            throw new IllegalArgumentException("Invalid array or indices");
        }
        T temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Logging.logInfo("Which type of array you want? 1.String 2.Integer");
        int opt = sc.nextInt();

        Logging.logInfo("Enter size of array");
        int sz = sc.nextInt();

        if(opt == 1){
            String[] stringArr = new String[sz];
            Logging.logInfo("Enter Array elements ");
            for(int i = 0; i < sz; i++){
                String temp = sc.next();
                stringArr[i] = temp;
            }
            Logging.logInfo("Array before : ");
            GenericSwap.printArr(stringArr);
            Logging.logInfo("Enter indices to be swapped");
            int first = sc.nextInt();
            int second = sc.nextInt();

            GenericSwap.swapElements(stringArr,first,second);
            Logging.logInfo("Array after being swapped");
            GenericSwap.printArr(stringArr);
        }
        else{
            Integer[] intArr = new Integer[sz];
            Logging.logInfo("Enter Array elements ");
            for(int i = 0; i < sz; i++){
                int temp = sc.nextInt();
                intArr[i] = temp;
            }
            Logging.logInfo("Array before : ");
            GenericSwap.printArr(intArr);
            Logging.logInfo("Enter indices to be swapped");
            int first = sc.nextInt();
            int second = sc.nextInt();

            GenericSwap.swapElements(intArr,first,second);
            Logging.logInfo("Array after being swapped");
            GenericSwap.printArr(intArr);
        }

    }
}
