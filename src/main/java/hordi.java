public class hordi {
    private static final double MIN_RANGE = -3;
    private static final double MAX_RANGE = 3;
    private static final double EPS = 0.0001;

    public static double searchX(double minRange, double maxRange, double x) {
        double a = 12 * Math.pow(minRange, 3) - 12 * Math.pow(minRange, 2) + 12 * minRange;
        double b = 12 * Math.pow(maxRange, 3) - 12 * Math.pow(maxRange, 2) + 12 * maxRange;
        double c = 12 * Math.pow(x, 3) - 12 * Math.pow(x, 2) + 12 * x;
        return a >= b && a >= c ? minRange : b >= a && b >= c ? maxRange : x;
    }

    public static double getLambda(double x) {
        return 1 / (12 * Math.pow(x, 3) - 12 * Math.pow(x, 2) + 12 * x);
    }

    private static double function(double lambda, double x, double eps) {
        double x0;
        double fx;
        int count = 0;
        System.out.printf("%s %8s %15s\n", "Итераций", "x", "f(x)");
        do {
            x0 = x;
            x = x - lambda * (3 * Math.pow(x, 4) + 4 * Math.pow(x, 3) - 12 * Math.pow(x, 2) +1);
            fx = 3* Math.pow(x0, 4) + 4 * Math.pow(x0, 3) - 12 * Math.pow(x0, 2) + 1;
            System.out.printf("%5d %15.8f %15.8f\n", ++count, x0, fx);
        } while (Math.abs(x - x0) >= eps);
        return x0;
    }


    public static void main(String[] args) {
        //дана функция y = 3x^4+4x^3-12x^2+1;
        //y` = 12x^3 + 12x^2 - 24x + 1
        //y`` = 36x^2 + 24x  - 24= 0
        //72x + 24 = 0;
        //x = 3;
        double x = 1;
        System.out.println("Дана функция:\n   y = x^3 - 3*x^2 + 6*x + 3;");
        System.out.printf("   На отрезке: [%.2f, %.2f];\n", MIN_RANGE, MAX_RANGE);
        System.out.printf("   Промежуточный корень: %.2f\n\n", x);

        x = searchX(MIN_RANGE, MAX_RANGE, x);
        double lambda = getLambda(x);
        System.out.printf("Начальный Х для итерации равен: %.2f;\n", x);
        System.out.printf("Лямбда равна: %.5f;\n\n", lambda);

        double result = function(lambda, x, EPS);
        System.out.printf("\nОтвет:\n   x = %.8f;", result);
    }
}