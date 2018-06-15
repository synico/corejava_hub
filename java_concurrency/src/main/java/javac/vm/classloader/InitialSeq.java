package javac.vm.classloader;

public class InitialSeq {

    public InitialSeq() {
        System.out.println("Constructor");
    }

    static {
        System.out.println("InitialSeq");
    }

    public static void main(String[] args) {
        new InitialSeq();
    }

}
