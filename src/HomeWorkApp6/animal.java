package HomeWorkApp6;

public abstract class animal {
    static final int swimFail = 0;
    static final int swimLuck = 1;
    static final int swimNone = -1;

    private String type; //вид животного
    protected String name; //кличка
    private float maxRun; //возможность плавать
    private float maxSwim; // возможность бегать

    public static int countAnimal = 0;

    animal(String type,
           String name,
           float maxRun,
           float maxSwim) {


        this.type = type;
        this.name = name;
        this.maxRun = maxRun;
        this.maxSwim = maxSwim;
        ++countAnimal;

    }

    String getName() {
        return this.name;
    }

    String getType() {
        return this.type;
    }

    float getMaxRun() {
        return this.maxRun;
    }

    float getMaxSwim() {
        return this.maxSwim;
    }

    protected boolean run(float distance) {
        return (distance <= maxRun);
    }

    protected int swim(float distance) {
        return (distance <= maxSwim) ? swimLuck : swimFail;
    }

}
