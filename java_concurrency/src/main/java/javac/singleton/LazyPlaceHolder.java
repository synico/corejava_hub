package javac.singleton;

/**
 * @see http://docs.oracle.com/javase/specs/jls/se7/html/jls-12.html#jls-12.4
 * @author Nick
 *
 */
public class LazyPlaceHolder {

    static class Resource {
        static {
            System.out.println("init resource");
        }
    }

    private static class ResouceHolder {
        public static Resource resource = new Resource();
    }

    public static Resource getResource() {
        return ResouceHolder.resource;
    }

    public static void main(String... args) {
        Resource resource = LazyPlaceHolder.getResource();
    }

}
