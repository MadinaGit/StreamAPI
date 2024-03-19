import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        List<Integer> intList = Arrays.asList(1, 2, 5, 16, -1, -2, 0, 32, 3, 5, 8, 23, 4);
        List<Integer> even = new ArrayList<>();
        for (int i : intList) {
            if (i > 0 && i % 2 == 0) {
                even.add(i);
            }
        }
        Collections.sort(even);
        System.out.println(even);

        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)]));

        }
        Stream<Person> stream = persons.stream();
        long peopleless18 = stream.filter(person -> person.getAge() <= 18)
                .count();
        System.out.println(peopleless18);

        Stream<Person> stream1 = persons.stream();
        Stream<Person> conscript = stream1.filter(person -> person.getSex().equals(Sex.MAN));
        Stream<Person> conscript2 = conscript.filter(person -> person.getAge() >= 18 && person.getAge() <= 27);
        Stream<String> consFam = conscript2.map(Person::getFamily);
        List<String> consFam1 = consFam.collect(Collectors.toList());
        System.out.println(consFam1);

        Stream<Person> stream2 = persons.stream();
        Stream<Person> sortedListWom = stream2.filter(person -> person.getSex().equals(Sex.WOMAN));
        Stream<Person> sortedListWom1 = sortedListWom.filter(person -> person.getAge() >= 18 && person.getAge() <= 60);
        Stream<Person> sortedListWomEdu = sortedListWom1.filter(person -> person.getEducation().equals(Education.HIGHER));
        Stream<Person> womenWorkList = sortedListWomEdu.sorted(Comparator.comparing(Person::getFamily));

        List<Person> womenList = womenWorkList.collect(Collectors.toList());
        System.out.println(womenList);

        Stream<Person> stream3 = persons.stream();
        Stream<Person> sortedListMen = stream3.filter(person -> person.getSex().equals(Sex.MAN));
        Stream<Person> sortedListMen1 = sortedListMen.filter(person -> person.getAge() >= 18 && person.getAge() <= 65);
        Stream<Person> sortedListMenEdu = sortedListMen1.filter(person -> person.getEducation().equals(Education.HIGHER));
        Stream<Person> menWorkList = sortedListMenEdu.sorted(Comparator.comparing(Person::getFamily));

        List<Person> menList = menWorkList.collect(Collectors.toList());
        System.out.println(menList);
    }
}