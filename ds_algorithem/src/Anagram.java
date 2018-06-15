/**
 * examle of recursive
 * @author Nick
 *
 */
public class Anagram {
    
    static int size;
    static int count;
    static char[] arrChar = new char[100];
    
    public static void doAnagram(int newSize) {
        
    }
    
    private static void rotate(int newSize) {
        int j;
        int position = size - newSize;
        char temp = arrChar[position];// save first letter
        for(j = position + 1; j < size; j++) {// shift others left
            arrChar[j - 1] = arrChar[j];
        }
        arrChar[j - 1] = temp;// put first on right
    }
    
    private static void displayWord() {
        if(count < 99) {
            System.out.println(" ");
        }
        if(count < 9) {
            System.out.println(" ");
        }
        System.out.println(++count + " ");
        for(int j = 0; j < size; j++) {
            System.out.println(arrChar[j]);
        }
        System.out.println("    ");
        System.out.flush();
        if(count % 6 == 0) {
            System.out.println("");
        }
    }

    public static void main(String[] args) {
    }

}
