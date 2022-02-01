package HomeWorkApp5;

public class person {




    private String name;
    private String surname;
    private String secondName;
    private String position;

    private String email;
    private String phone;
    private int salary;
    private int year;



    public person(
            String name,
            String surname,
            String secondName,
            String position,
            String email,
            String phone,
            int salary,
            int year)

   {

        this.name = name;
        this.surname = surname;
        this.secondName = secondName;
        this.position = position;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.year = year;
    }



    int getAge() {
        return year;
    }


    String getFullInfo() {
        return this.name + " " +
                this.surname+ " " +
                this.secondName  +  ". " + " Возраст: " +
                this.getAge() + ". Должность:" +
                this.position  + ". Номер телефона: " +
                this.phone + ". Адресс электронной почты: " +
                this.email +  ". Зароботная плата " +
                this.salary + " руб." ;
    }
}
