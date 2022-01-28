package HomeWorkApp2;

public class HomeWorkApp2 {

    public static void main(String[] args) {
        System.out.println(twoNumbers(5, 8));
        wholeNumbers(6);
        System.out.println(third(9));

//        liner();

    }


    public static boolean twoNumbers(int a, int b) {
        return (a + b) >= 10 && (a + b) <= 20;
    }


    public static boolean wholeNumbers(int c) {
        return c >= 0;
    }

    public static boolean third(int d) {
        return d < 0;
    }

//    public static boolean liner(String value, int f) {
//        for(int i = 1; i <= f; i ++) {
//            System.out.print("String #" + i + ":" + value);
//        }

    }









