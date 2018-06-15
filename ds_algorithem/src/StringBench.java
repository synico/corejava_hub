
public class StringBench {

    public static void main(String[] args) {
        String str = "\"015AZ3.9-Z(TPL3,F)\"";
        String[] stra = str.split("\",");
        for (String s : stra) {
            System.out.println(s);
        }
        System.out.println(str.replaceAll("\"", ""));

    }

}
