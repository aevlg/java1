package HomeWorkApp2;

public class HomeWorkApp2 {

    public static void main(String[] args) {
        System.out.println(twoNumbers(5, 8));
        if (wholeNumbers(6)) {
            System.out.println("Число положительное");
        } else {
            System.out.println("Число отрицательное");
        }

        System.out.println(third(9));

        liner();

    }


    public static boolean twoNumbers(int a, int b) {
        return (a + b) >= 10 && (a + b) <= 20;
    }


    public static boolean wholeNumbers(int c) {
        return c >= 0;
    }

    public static boolean third(int d) {
        return d >= 0;
    }

    public static void liner() {
        for (int f = 0; f < 6; f++) {
            System.out.print('r');
        }
    }

}







