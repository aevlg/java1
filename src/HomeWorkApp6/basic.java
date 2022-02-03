package HomeWorkApp6;

public class basic {
    public static void main(String[] args) {

        String tempWinEvent = " получилось";
        String tempLossEvent = " а  это не получилось";
        String eventName;
        String eventResult;

        cat cat1 = new cat("Вася", 2, 200);
        cat cat2 = new cat("Стив", 4, 400);
        dog dog1 = new dog("Ральф", 0.5f, 400);
        dog dog2 = new dog("Тиша", 1.5f, 350);
        dog dog3 = new dog("Жорик", 2.5f, 250);

        animal[] animals = {cat1, cat2, dog1, dog2, dog3};

        float runLength = 150;
        float swimLength = 10;

        for (int i = 0; i < animals.length; i++) {

            String nameString = animals[i].getType() + " " + animals[i].getName() + " может ";
            eventName = "пробежать на " + animals[i].getMaxRun() + " м. >>>>>>> пытается пробежать на ";

            eventResult = animals[i].run(runLength) ? tempWinEvent : tempLossEvent;
            result(nameString, eventName, runLength, eventResult);


            int swimResult = animals[i].swim(swimLength);
            eventName = "проплыть на " + animals[i].getMaxSwim() + " м. >>>>>>> пытается проплыть на ";
            eventResult = (swimResult == animal.swimLuck) ? tempWinEvent : tempLossEvent;

            if (swimResult == animal.swimNone)
                eventResult = " это не получилось, потому что котики плавать не умееют";
            result(nameString, eventName, swimLength, eventResult);
        }
        System.out.println("_________________________________________");

        //подсчет животных
        System.out.println("ВСЕГО ЖИВОТНЫХ > "
                +animal.countAnimal + "  ВСЕГО КОТИКОВ > "
                + cat.countCat
                + "  ВСЕГО СОБАЧЕК > "
                + dog.countDog);
    }



    private static void result(String nameAnimal,
                               String event,
                               float eventLength,
                               String resultEvent) {

        System.out.println(nameAnimal + event + eventLength + " м " + resultEvent);
    }
}
