package javac.generic;

public class IdentityFunction {

    @SuppressWarnings("unchecked")
    private static <T> T identityFunction() {
        return (T) new UnaryFunction<T>() {

            @Override
            public T apply(T arg) {
                return arg;
            }

        };
    }

    public static void main(String[] args) {
        String[] strings = { "jute", "hemp", "nylon" };
        UnaryFunction<String> sameString = identityFunction();
        for (String s : strings) {
            System.out.println(sameString.apply(s));
        }

        Number[] numbers = { 1, 2.0, 3L };
        UnaryFunction<Number> sameNumber = identityFunction();
        for (Number n : numbers) {
            System.out.println(sameNumber.apply(n));
        }
    }

}
