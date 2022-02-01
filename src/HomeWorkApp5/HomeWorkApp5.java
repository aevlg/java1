package HomeWorkApp5;

public class HomeWorkApp5 {

    public static void main(String[] args) {

        person mainEmployee = new person ("Ангелина", "Горшунова", "Вадимовна",
                " manager", "fglkhf@gmail.com", "8 999 788 08 99", 50000, 22);

        person[] office = {
                new person("Алла", "Горшунова", "Вадимовна",
                        " manager", "wserdfsdf@gmail.com", "8 999 788 00 89", 540000, 20),

                new person("Галина", "Горшунова", "Александровна",
                        " manager", "ljkhu@gmail.com", "8 999 788 99 99", 760000, 44),

                new person("Вадим", "Горшунов", "Васильевич",
                        " manager", "ertey@gmail.com", "8 999 788 66 99", 760567, 45),

                new person("Илья", "Горшунов", "Вадимович",
                        " manager", "luyykf@gmail.com", "8 999 777 08 99", 50000, 18)



        };



        getAllPersonsInOffice(office);
        System.out.println("----------------------");
        getOldPerson(office, 40);

    }

    private static void getOldPerson(person[] office, int year) {
        for (int i = 0; i < office.length; i++)
            if (office[i].getAge() > year) {
                System.out.println(office[i].getFullInfo());
            }
    }


    private static void getAllPersonsInOffice(person[] office) {
        System.out.println("ВСЕ СОТРУДНИКИ ОФИСА: ");
        for (int i = 0; i < office.length; i++)
            System.out.println((i + 1) + " " + office[i].getFullInfo());
    }
}
