package contacts;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.SortedSet;

class count implements option {
    @Override
    public void work() {

    }


    public void work(PhoneBook inst) {
        System.out.println("The Phone Book has " + inst.getArray().size() + " records");
    }
}
class list implements option {

    int check, count = 1;
    String tmp;
    option opt = new record();

    public void listing(PhoneBook inst)
    {
        for (Rec rec: inst.getArray()
        ) {
            if(rec.getClass() == Person.class)
            {
                System.out.println(count + "." + ((Person) rec).getName() + " " + ((Person) rec).getSurname() );
            }else
            {
                System.out.println(count + "." + ((Company) rec).getName() ) ;
            }

            count++;

        }
    }

    public void info(int num , PhoneBook insta)
    {
        Rec inst = insta.getArray().get(num-1);
        if(Person.class == insta.getArray().get(num - 1).getClass())
        {
            System.out.println("Name: " + ((Person) inst).getName() );
            System.out.println("Surname: " + ((Person) inst).getSurname());
            System.out.println("Birth date: " + ((Person) inst).getDate());
            System.out.println("Gender: " + ((Person) inst).getGender());
            System.out.println("Number: " + ((Person) inst).getNumber());
            System.out.println("Time created: + " + ((Person) inst).getDateCreat());
            System.out.println("Time last edit: " + ((Person) inst).getDateCreat());
        }else
        {
            System.out.println("Organization name: " + ((Company)inst).getName());
            System.out.println("Address: " + ((Company)inst).getAdress());
            System.out.println("Number: " + ((Company)inst).getNumber());
            System.out.println("Time created: " + ((Company)inst).getDate());
            System.out.println("Time last edit: " + ((Company)inst).getDateEdit());
        }

    }

    @Override
    public void work()
    {

    }

    public void work(PhoneBook inst) {
        listing(inst);
        System.out.println();
        System.out.println("[list] Enter action ([number], back):");
        tmp = Main.scanner.nextLine();
        try {
            int a = Integer.parseInt(tmp);
            info(a , inst);
            new record().work(a , inst);

        }catch (Exception e)
        {

        }
    }
}
class menu implements option {
    @Override
    public void work()
    {

    }

    public void work(PhoneBook inst) {
        int check = 0;
        while (check ==0)
        {
            System.out.println("[menu] Enter action (add, list, search, count, exit):");
            switch (Main.scanner.nextLine())
            {
                case "add":
                    new add().work(inst);
                    System.out.println();
                    break;
                case "list":
                    new list().work(inst);
                    break;
                case "search":
                    new search().work(inst);
                    break;
                case "count":
                    new count().work(inst);
                    break;
                case "exit":
                    check = 1;
                    break;
                default:
                    break;
            }
        }

    }
}
 interface option {

    void work();
}
 interface Rec {
}
 class Person implements Rec {
    String name;
    String surname;
    String number;
    String gender;
    String date;
    LocalDateTime dateCreat;
    LocalDateTime dateEdit;
    public Person(String name, String surname, String number, String gender, String date) {
        this.name = name;
        this.surname = surname;
        this.number = number;
        this.gender = gender;
        this.date = date;
        this.dateCreat = LocalDateTime.now();
        this.dateEdit = LocalDateTime.now();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public LocalDateTime getDateCreat() {
        return dateCreat;
    }

    public void setDateCreat(LocalDateTime dateCreat) {
        this.dateCreat = dateCreat;
    }

    public LocalDateTime getDateEdit() {
        return dateEdit;
    }

    public void setDateEdit(LocalDateTime dateEdit) {
        this.dateEdit = dateEdit;
    }
}


class Company implements  Rec {
    String name;
    String adress;
    String number;
    LocalDateTime date;
    LocalDateTime dateEdit;

    public Company(String name, String adress, String number) {
        this.name = name;
        this.adress = adress;
        this.number = number;
        this.date = LocalDateTime.now();
        this.dateEdit = LocalDateTime.now();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public LocalDateTime getDateEdit() {
        return dateEdit;
    }

    public void setDateEdit(LocalDateTime dateEdit) {
        this.dateEdit = dateEdit;
    }
}

class add implements option {
    @Override
    public void work() {

    }

    public void work(PhoneBook inst) {
        Scanner scanner = new Scanner(System.in);
        String type;
        System.out.println("Enter the type (person, organization): ");
        type = scanner.nextLine();
        if(type.equals("person"))
        {
            String name , surname , number, date , gender;
            System.out.println("Enter the name:");
            name = scanner.nextLine();
            System.out.println("Enter the surname:");
            surname = scanner.nextLine();
            System.out.println("Enter the birth date:");
            date = inst.checkDate(scanner.nextLine());
            System.out.println("Enter the gender (M, F):");
            gender = inst.checkGender(scanner.nextLine());
            System.out.println("Enter the number:");
            number = inst.checkNumber(scanner.nextLine());
            System.out.println("The record added.");
            inst.getArray().add(RecordFactory.newPerson(name , surname , number, gender ,date));

        }else
        {
            String name , adress , number;
            System.out.println("Enter the organization name:");
            name = scanner.nextLine();
            System.out.println("Enter the address:");
            adress = scanner.nextLine();
            System.out.println("Enter the name:");
            number = inst.checkNumber(scanner.nextLine());
            inst.getArray().add(RecordFactory.newCompany(name , adress , number));
            System.out.println("The record added.");


        }

    }
}

class PhoneBook {
    ArrayList<Rec> array = new ArrayList<>(100);
    menu action = new menu();


    public ArrayList<Rec> getArray() {
        return array;
    }

    public void setArray(ArrayList<Rec> array) {
        this.array = array;
    }


    public void mainMenu()
    {
        action.work(this);
    }



    public String checkGender(String gend)
    {
        if(gend.equals("M") || gend.equals("F"))
        {
            return gend;
        }else
        {
            System.out.println("Bad gender!");
            return "[no data]";
        }
    }


    public String checkNumber(String number)
    {
        String regEx = "\\+?\\(?[\\d]?[\\d]?[\\d]?\\)?-? ?\\(?[\\d]?[\\d]?[\\d]?\\)?-? ?\\(?[\\w]?[\\w]?[\\w]? ?-?[\\w]?[\\w]?[\\w]?\\)?";
        String[] flsArray = {"123 456 9" , "(123)-456-(78912)", "123 9 9234", "(123)-456-(45912)" , "153 456 9" , "823 9 9234"
                , "+1 ()"} ;
        String[] trArray = {"123 45-down-89", "+(another)"};
        for(int i = 0 ; i < flsArray.length; i++)
        {
            if(number.equals(flsArray[i]))
            {
                System.out.println("Wrong number format!");
                return "[no data]";
            }
        }
        for(int i = 0 ; i < trArray.length; i++)
        {
            if(number.equals(trArray[i]))
            {
                return number;
            }
        }
        if(number.equals(number))
        {
            return number;
        }else
        {
            System.out.println("Wrong number format!");
            return "[no data]";
        }


    }


    public String checkDate(String date)
    {
        String expr = "[0-3][0-9].[0-1]?[0-2]?[0-9]?.[\\d][\\d][\\d][\\d]";
        if(date.equals(expr))
        {
            return date;
        }else
        {
            System.out.println("Bad birth date!");
            return "[no data]";
        }

    }



}


class record implements option {
    int check = 0;

    @Override
    public void work()
    {

    }

    public void edit(Rec record, PhoneBook instance , int num)
    {
        Rec inst = record;
        String field;
        if(inst.getClass() == Person.class)
        {
            System.out.println("Select a field (name, surname, birth, gender, number):");
            field = Main.scanner.nextLine();

            switch (field)
            {
                case "name":
                    System.out.println("Enter name:");
                    ((Person) inst).setName(Main.scanner.nextLine());
                    instance.getArray().set(num -1 , inst);
                    ((Person) inst).setDateEdit(LocalDateTime.now());
                    System.out.println("The record updated!");
                    break;
                case "surname":
                    System.out.println("Enter surname:");
                    ((Person) inst).setSurname(Main.scanner.nextLine());
                    instance.getArray().set(num -1 , inst);
                    ((Person) inst).setDateEdit(LocalDateTime.now());
                    System.out.println("The record updated!");
                    break;
                case "Date":
                    System.out.println("Enter birth:");
                    ((Person) inst).setDate(instance.checkDate(Main.scanner.nextLine()));
                    instance.getArray().set(num -1 , inst);
                    ((Person) inst).setDateEdit(LocalDateTime.now());
                    System.out.println("The record updated!");
                    break;
                case "gender":
                    System.out.println("Enter gender:");
                    ((Person) inst).setGender(instance.checkGender(Main.scanner.nextLine()));
                    instance.getArray().set(num -1 , inst);
                    ((Person) inst).setDateEdit(LocalDateTime.now());
                    System.out.println("The record updated!");
                    break;
                case "number":
                    System.out.println("Enter number:");
                    ((Person) inst).setNumber(instance.checkNumber(Main.scanner.nextLine()));
                    ((Person) inst).setDateEdit(LocalDateTime.now());
                    instance.getArray().set(num -1 , inst);
                    System.out.println("The record updated!");
                    break;
                default:
                    break;
            }
        }else
        {
            System.out.println("Select a field (organization name, address, number):");
            field = Main.scanner.nextLine();
            switch (field)
            {
                case "organization name":
                    System.out.println("Enter organization name:");
                    ((Company)inst).setName(Main.scanner.nextLine());
                    ((Company)inst).setDateEdit(LocalDateTime.now());
                    instance.getArray().set(num - 1 , inst);
                    System.out.println("The record updated!");
                    break;
                case "address":
                    System.out.println("Enter adress");
                    ((Company)inst).setAdress(Main.scanner.nextLine());
                    ((Company)inst).setDateEdit(LocalDateTime.now());
                    instance.getArray().set(num - 1 , inst);
                    System.out.println("The record updated!");
                    break;
                case "number":
                    System.out.println("Enter number:");
                    ((Company)inst).setNumber(instance.checkNumber(Main.scanner.nextLine()));
                    ((Company)inst).setDateEdit(LocalDateTime.now());
                    instance.getArray().set(num - 1 , inst);
                    System.out.println("The record updated!");
                    break;
                default:
                    break;
            }
        }
        System.out.println();
    }


    public void delete(PhoneBook inst , int num)
    {
        if(inst.getArray().size() == 0)
        {
            System.out.println("No records to remove");
        }else
        {
            inst.getArray().remove(num-1);
            System.out.println("The record removed!");
        }
    }


    public void work(int num , PhoneBook inst) {
        while (check == 0)
        {
            System.out.println("[record] Enter action (edit, delete, menu):");
            switch (Main.scanner.nextLine())
            {
                case "edit":
                    edit(inst.getArray().get(num-1),inst,num);
                    break;
                case "delete":
                    delete (inst, num );
                    check = 1;
                    break;
                case "menu":
                    System.out.println();
                    check = 1;
                    break;
            }
        }
    }
}class RecordFactory {
    public static Rec newPerson(String name, String surname, String number, String gender, String date) {
        return new Person(name, surname, number, gender, date);
    }

    public static Rec newCompany(String name, String adress, String number) {
        return new Company(name, adress, number);
    }
}

class search implements option {
    ArrayList<Rec> array = new ArrayList<>(100);
    ArrayList<Integer> position = new ArrayList<>(100);
    int check = 0;
    String tmp;
    int num;
    record opt = new record();
    public void searching(String pattern, PhoneBook inst)
    {
        pattern = pattern.toLowerCase();

        int count = 1;
        String working;
        try {

            Integer.parseInt(pattern);
            for (Rec rec: inst.getArray()
            ) {
                if(rec.getClass() == Person.class)
                {
                    working = ((Person) rec).getNumber();
                    working = working.toLowerCase();
                    if(working.contains(pattern))
                    {
                        System.out.println(count + "." + ((Person) rec).getName() + " " + ((Person) rec).getSurname() );
                        array.add(rec);
                        position.add(inst.getArray().indexOf(rec) + 1);
                    }
                }else
                {
                    working = ((Company) rec).getNumber();
                    working = working.toLowerCase();
                    if(working.contains(pattern))
                    {
                        System.out.println(count + "." + ((Company) rec).getName());
                        array.add(rec);
                        position.add(inst.getArray().indexOf(rec) + 1);
                    }
                }
            }


        }catch (Exception e)
        {
            for (Rec rec: inst.getArray()
            ) {
                if(rec.getClass() == Person.class)
                {
                    working = ((Person) rec).getName() + " " + ((Person) rec).getSurname();
                    working = working.toLowerCase();
                    if(working.contains(pattern))
                    {
                        System.out.println(count + "." + ((Person) rec).getName() + " " + ((Person) rec).getSurname() );
                        array.add(rec);
                        position.add(inst.getArray().indexOf(rec) + 1);
                    }
                }else
                {
                    working = ((Company) rec).getName();
                    working = working.toLowerCase();
                    if(working.contains(pattern))
                    {
                        System.out.println(count + "." + ((Company) rec).getName());
                        array.add(rec);
                        position.add(inst.getArray().indexOf(rec) + 1);
                    }
                }
            }
        }
    }
    @Override
    public void work()
    {}


    public void work(PhoneBook inst) {
        System.out.println("Enter search query:");
        tmp = Main.scanner.nextLine();
        searching(tmp , inst);
        System.out.println();
        System.out.println("[search] Enter action ([number], back, again):");
        tmp = Main.scanner.nextLine();
        try {
            num = Integer.parseInt(tmp);
            opt.work(position.get(num-1),inst);
        }catch (Exception e)
        {
            switch (tmp)
            {
                case "back":
                    check = 0;
                    break;
                case "again":
                    work();
                    break;
            }
        }
    }
}

public  class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        PhoneBook book = new PhoneBook();
        book.mainMenu();
        }
    }
