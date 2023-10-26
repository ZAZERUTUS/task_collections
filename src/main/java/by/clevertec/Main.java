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
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        task1();
        task2();
        task3();
        task4();
        task5();
        task6();
        task7();
        task8();
        task9();
        task10();
        task11();
        task12();
        task13();
        task14();
        task15();
        task16();
        task17();
        task18();
        task19();
        task20();
        task21();
        task22();
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

    public static void task8() {
        List<Animal> animals = Util.getAnimals();
        animals.stream().sorted(Comparator.comparing(Animal::getBread)).limit(100).max(Comparator.comparingInt(Animal::getAge)).ifPresent(System.out::println);
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

        houses.stream()
                .flatMap(house -> house.getPersonList().stream().map(person -> Map.entry(person, house.getBuildingType()))
                ).sorted((o1, o2) -> {
                    Integer highElem1 = o1.getValue().equals("Hospital") ? 2 :
                            18 > getAge(o1.getKey().getDateOfBirth()) || 65 < getAge(o1.getKey().getDateOfBirth()) ?
                                    1 : 0;

                    Integer highElem2 = o2.getValue().equals("Hospital") ? 2 :
                            18 > getAge(o2.getKey().getDateOfBirth()) || 65 < getAge(o2.getKey().getDateOfBirth()) ?
                                    1 : 0;
                    return Integer.compare(highElem2, highElem1);
                })
                .limit(500)
                .forEach(a -> System.out.println(a.getKey()));
    }

    public static void task14() {
        Map<Integer, String> countries = new HashMap<>();
        countries.put(1, "Туркменистан");
        countries.put(2, "Узбекистан");
        countries.put(3, "Казахстан");
        countries.put(4, "Кыргызстан");
        countries.put(5, "Россия");
        countries.put(6, "Монголия");

        List<Car> cars = Util.getCars();

        countries.entrySet().stream()
                .collect(Collectors.toMap(
                        e -> {
                            System.out.println(e.getValue());
                            return e.getValue();
                            },
                        m -> {
                            OptionalInt carSumMass = getCarsForCategory(cars.stream(),
                                    m.getKey()).mapToInt(Car::getMass).reduce(Integer::sum);
                            BigDecimal cost = new BigDecimal(carSumMass.getAsInt())
                                    .multiply(new BigDecimal(7.14))
                                    .divide(new BigDecimal(1000)).setScale(2, RoundingMode.HALF_UP);
                            System.out.printf("Sum cost by 7.14 - %s for %s tonn\n\n", cost, carSumMass.getAsInt());
                            return cost;
                            })
                )
                .values().stream().reduce(BigDecimal::add).ifPresent(i -> System.out.printf("Sum for company - %s", i));

    }

    public static void task15() {
        List<Flower> flowers = Util.getFlowers();
        System.out.println("----" + startFrom("Scwecwecw edw"));
        flowers.stream().filter(Main::verifyFlower)
                .sorted(Comparator.comparing(Flower::getOrigin).reversed()
                        .thenComparing(Flower::getPrice)
                        .thenComparing(Flower::getWaterConsumptionPerDay).reversed())
                .forEach(f -> System.out.printf("%s price for 5 years - %s\n", f, new BigDecimal(f.getPrice())
                        .add(new BigDecimal(f.getWaterConsumptionPerDay()).setScale(15, RoundingMode.HALF_UP)
                                .divide(new BigDecimal(1000))
                                .multiply(new BigDecimal(1826)) //5*365+1 - с учётом висакосного года
                                .multiply(new BigDecimal(1.67))).setScale(2, RoundingMode.HALF_UP)));
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

    public static int getAge(LocalDate dateBirthday) {
        return LocalDate.now().compareTo(dateBirthday);
    }

    public static boolean verifyFlower(Flower flower) {
        return startFrom(flower.getCommonName())
                && flower.isShadePreferred()
                && flower.getFlowerVaseMaterial().stream()
                .anyMatch(m -> Arrays.stream(new String[] {"Glass", "Aluminum", "Steel"}).toList().contains(m));
    }

    public static boolean startFrom(String str) {
        return str.matches("^[C-S]{1}.+");
    }

    public static Stream<Car> getCarsForCategory(Stream<Car> stream, Integer categoryNum) {
        List<String> filter2 = Arrays.stream(new String[] {"BMW", "Lexus", "Chrysler", "Toyota"}).toList();
        List<String> filter3 = Arrays.stream(new String[] {"GMC", "Dodge"}).toList();
        List<String> filter4 = Arrays.stream(new String[] {"Civic", "Cherokee"}).toList();
        List<String> filter5 = Arrays.stream(new String[] {"Yellow", "Red", "Green", "Blue"}).toList();

        switch (categoryNum) {
            case 1:
                return stream.filter(f -> f.getCarMake().equals("Jaguar") || f.getColor().equals("White"));
            case 2:
                return stream.filter(f -> f.getMass() < 1500 || filter2.contains(f.getCarMake()));
            case 3:
                return stream.filter(f -> (f.getMass() > 4000 && f.getColor().equals("Black")) || filter3.contains(f.getCarMake()));
            case 4:
                return stream.filter(f -> f.getReleaseYear() < 1982 || filter4.contains(f.getCarModel()));
            case 5:
                return stream.filter(f -> !filter5.contains(f.getColor()) || f.getPrice() >40000);
            case 6:
                return stream.filter(f -> f.getVin().contains("59"));
            default:
                return Stream.empty();
        }
    }
}
