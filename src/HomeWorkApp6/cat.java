package HomeWorkApp6;



    public class cat extends animal {

        public static int countCat = 0; // колличество котиков
        public static String typeClass = "Котик";

        cat (String name,
             float maxRun,
             float maxSwim) {


            super(typeClass, name, maxRun, maxSwim);
            ++countCat;
        }

        @Override
        protected int swim(float distance) {
            return animal.swimNone;
        }

    }
