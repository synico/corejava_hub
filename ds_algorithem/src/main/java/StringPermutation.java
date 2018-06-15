public class StringPermutation {
    
    private static int idx = 0;
    
    public void permutationString(String input) {
        if(input == null || input.trim().length() == 0) {
            return;
        }
        permutation(input.toCharArray(), 0, input.length() - 1);
    }
    
    private void permutation(char[] s, int from, int to) {
        if(to <= 1) {
            return;
        }
        if(from == to) {
            System.out.print(idx + " | ");
            System.out.println(s);
            idx++;
        } else {
            for(int i = from; i <= to; i++) {
                swap(s, i, from);
                permutation(s, from + 1, to);
                swap(s, from, i);
            }
        }
    }
    
    private void swap(char[] s, int from, int to) {
        char temp = s[from];
        s[from] = s[to];
        s[to] = temp;
    }

    public static void main(String[] args) {
        StringPermutation sp = new StringPermutation();
        sp.permutationString("abcd");
    }

}
