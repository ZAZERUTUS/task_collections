package by.clevertec;

import by.clevertec.comporators.AnimalAgeComparator;
import by.clevertec.model.Animal;
import by.clevertec.model.Car;
import by.clevertec.model.Examination;
import by.clevertec.model.Flower;
import by.clevertec.model.House;
import by.clevertec.model.Person;
import by.clevertec.model.Student;
import by.clevertec.util.Util;

import javax.swing.text.html.parser.Entity;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
//        task1();
//        task2();
//        task3();
//        task4();
//        task5();
//        task6();
//        task7();
//        task8();
//        task9();
//        task10();
//        task11();
//        task12();
        task13();
        task14();
        task15();
//        task16();
//        task17();
//        task18();
//        task19();
//        task20();
//        task21();
//        task22();
    }

    public static void task1() {
        List<Animal> animals = Util.getAnimals();
        animals.stream().filter(a->(10<=a.getAge() && a.getAge()<=20))
                .sorted(new AnimalAgeComparator()).skip(14).limit(7)
                .forEach(a->{
                    System.out.println(a.getId());
                });
    }

    public static void task2() {
        List<Animal> animals = Util.getAnimals();
        animals.stream().filter(a->(a.getOrigin().equals("Japanese")))
                .map(a -> a.getGender().equals("Female") ?
                        a.getBread().toUpperCase() :
                        a.getBread()).forEach(System.out::println);
//                .forEach(a->{
//                    System.out.println(
//                            a.getGender().equals("Female") ?
//                                    a.getBread().toUpperCase() :
//                                    a.getBread()
//                    );
//                });
    }

    public static void task3() {
        List<Animal> animals = Util.getAnimals();
        animals.stream().filter(a->(a.getAge()>=30 && a.getOrigin().startsWith("A")))
                .map(Animal::getOrigin).distinct()
                .forEach(
                        a->{System.out.println(a);}
                );
    }

    public static void task4() {
        List<Animal> animals = Util.getAnimals();
        System.out.println(animals.stream().filter(a->a.getGender().equals("Female")).count());
    }

    public static void task5() {
        List<Animal> animals = Util.getAnimals();
        System.out.println(animals.stream().filter(a -> (20<=a.getAge() && a.getAge()<=30)).anyMatch(a -> a.getOrigin().equals("Hungarian")));
    }

    public static void task6() {
        List<Animal> animals = Util.getAnimals();
        System.out.println(animals.stream().allMatch(a -> (a.getGender().equals("Male") || a.getGender().equals("Female"))));
    }

    public static void task7() {
        List<Animal> animals = Util.getAnimals();
        System.out.println(animals.stream().allMatch(a -> !a.getOrigin().equals("Oceania")));
    }

    public static void task8() { //TODO - не проверено запуском
        List<Animal> animals = Util.getAnimals();
        System.out.println(animals.stream().sorted().limit(100).max(new AnimalAgeComparator()));
    }

    public static void task9() {
        List<Animal> animals = Util.getAnimals();
        System.out.println(animals.stream().map(Animal::getOrigin).map(String::getBytes).max(new Comparator<byte[]>() {
            @Override
            public int compare(byte[] o1, byte[] o2) {
                return Integer.compare(o1.length, o2.length);
            }
        }).get().length);
    }

    public static void task10() {
        List<Animal> animals = Util.getAnimals();
        System.out.println(animals.stream().map(Animal::getAge).reduce(Integer::sum).get());
    }

    public static void task11() {
        List<Animal> animals = Util.getAnimals();
        System.out.println(animals.stream().filter(a -> a.getOrigin().equals("Indonesian")).mapToInt(Animal::getAge).average());
    }

    public static void task12() {
        List<Person> persons = Util.getPersons();
        persons.stream().sorted(Comparator.comparingInt(Person::getRecruitmentGroup)).limit(200).forEach(p -> System.out.println(p.toString()));
    }

    public static void task13() {
        List<House> houses = Util.getHouses();
//        houses.stream().map(h -> {
//            return new HashMap<String, Person> ()
//        }
    }

    public static void task14() {
        List<Car> cars = Util.getCars();
//        cars.stream() Продолжить ...
    }

    public static void task15() {
        List<Flower> flowers = Util.getFlowers();
//        flowers.stream() Продолжить ...
    }

    public static void task16() {
        // Минимальный взраст был поднят до 19 в связи с отсутствием совпадений с указанным в задании
        List<Student> students = Util.getStudents();
        students.stream().filter(st -> st.getAge() < 19)
                .sorted(Comparator.comparing(Student::getSurname))
                .forEach(System.out::println);
    }

    public static void task17() {
        List<Student> students = Util.getStudents();
        students.stream().map(Student::getGroup).distinct().forEach(System.out::println);
    }

    public static void task18() {
        List<Student> students = Util.getStudents();
        students.stream()
                .collect(Collectors.groupingBy(Student::getFaculty, Collectors.averagingDouble(Student::getAge)))
                .entrySet().stream().sorted(Map.Entry.comparingByValue())
                .forEach(ent -> System.out.printf("%s - %.2f\n",ent.getKey(), ent.getValue()));
    }

    public static void task19() {
        String groupName = "C-3";
        List<Student> students = Util.getStudents();
        List<Examination> examinations = Util.getExaminations();
        students.stream()
                .filter(st -> examinations.stream().filter(ex -> ex.getExam3() > 4).map(Examination::getStudentId).toList().contains(st.getId())
                        && st.getGroup().equals(groupName)).forEach(System.out::println);
    }

    public static void task20() {
        List<Student> students = Util.getStudents();
        List<Examination> examinations = Util.getExaminations();
//        examinations.stream().collect(Collectors.toMap(Examination::getStudentId, Examination::getExam1))
        System.out.println(students.stream().limit(20).collect(Collectors.groupingBy(Student::getFaculty, Collectors.averagingDouble(
                st -> examinations.stream().collect(Collectors.toMap(Examination::getStudentId, Examination::getExam1)).get(st.getId()))))
                .entrySet().stream().max(Comparator.comparing(Map.Entry::getValue)).get());
    }

    public static void task21() {
        List<Student> students = Util.getStudents();
        students.stream()
                .collect(
                        Collectors.groupingBy(Student::getFaculty, Collectors.counting()))
                .forEach((g, count) -> System.out.println(String.format("Faculty - %s, count - %s", g, count.toString())));
    }

    public static void task22() {
        List<Student> students = Util.getStudents();
        students.stream()
                .collect(
                        Collectors.groupingBy(Student::getFaculty, Collectors.minBy(Comparator.comparing(Student::getAge))))
                .forEach((g, min) -> System.out.printf("Faculty - %s, min - %d%n", g, min.get().getAge()));
    }
}
