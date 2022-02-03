package HomeWorkApp6;

public class dog extends animal {

    public static int countDog = 0; // колличество собачек
    public static String typeClass = "Собакен";

    dog(String name,
        float maxRun,
        float maxSwim) {

        super(typeClass, name, maxRun, maxSwim);
        ++countDog;
    }



}
