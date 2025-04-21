package guru.qa;

public class FirstClass {
    public static void main(String[] args) {
        Integer a = 11;
        Integer b = 5;
        Double c = 2.5;

        // Арифметические операторы + - / * % ++ --
        System.out.println("a+b=" + (a + b));
        System.out.println("a-b=" + (a - b));
        System.out.println("a*b=" + (a * b));
        System.out.println("a/b=" + (a / b));
        System.out.println("a%b=" + (a % b));
        System.out.println("a++=" + a++ + ", ++a=" + ++a);
        System.out.println("b--=" + b-- + ", --b=" + --b);

        System.out.println("a+c=" + (a + c));
        System.out.println("a/c=" + (a / c));
        System.out.println("a%c=" + (a % c));
        System.out.println("c++=" + c++ + ", ++c=" + ++c);

        System.out.println(a > b && b <= 10);
        System.out.println(a != b || b >= a);

        System.out.println("Максимальное значение float: " + Float.MAX_VALUE);
        System.out.println("Минимальное положительное значение float (ближайшее к нулю): " + Float.MIN_VALUE);

        System.out.println("Максимальное значение double: " + Double.MAX_VALUE);
        System.out.println("Минимальное положительное значение double (ближайшее к нулю): " + Double.MIN_VALUE);

        double d = Double.MAX_VALUE;
        System.out.println("Переполнение выглядит так: " + d * 2);
    }
}
