package collectors;

import model.Person;

import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;
import static utils.PersonUtils.getPersons;

public class ExploreCollectors {

    public static void main(String[] args) {

//      1. sum of ages of all persons (using reduce -> reduces a collection or a stream to something concrete)

        int sumOfAges = getPersons()
                .stream()
                .map(Person::age)
                .reduce(0, Integer::sum);

        System.out.println(sumOfAges);

        int ageSum = getPersons()
                .stream()
                .mapToInt(Person::age)
                .sum();

        System.out.println(ageSum);

//        =============================================================================

//        2. find names of people who are older than 29 - (toList()/toSet())

        var peopleOlderThan30 = getPersons().stream()
                .filter(person -> person.age() > 30)
                .map(Person::name)
                .toList();

        System.out.println(peopleOlderThan30);

//        =============================================================================

//        3. Map names as key and ages as values for senior citizens

        var seniorCitizens = getPersons().stream()
                .filter(person -> person.age()>60)
                .collect(toMap(Person::name, Person::age));

        System.out.println(seniorCitizens);

//        =============================================================================

//        4. Output comma separated names of young folks - (joining)

        var youngFolks = getPersons().stream()
                .filter(person -> person.age()<35)
                .map(Person::name)
                .collect(joining(","));

        System.out.println(youngFolks);

//        =============================================================================

//        5. Differentiate persons whose names begin with 'A' and whose don't - (partitioningBy)

        var namesPartitionedBy = getPersons().stream()
                .collect(partitioningBy(person -> person.name().charAt(0)=='A'));

        System.out.println(namesPartitionedBy);

//        ==============================================================================

//        6. Find out the persons who are of the same age - (groupingBy)

        var groupByAge = getPersons().stream()
                .collect(groupingBy(Person::age));

        System.out.println(groupByAge);

//        ==============================================================================

//        7. Find out the max/min age - (use min()/max() terminal operation

        var maxAge = getPersons().stream()
                .mapToInt(Person::age)
                .max();

        System.out.println(maxAge);

//        ==============================================================================

//        8. Concatenate the names of all persons with a ',' whose ages are same - (groupingBy using a collector joining)

        var sameAgedPersons = getPersons().stream()
                .collect(groupingBy(Person::age, mapping(Person::name, joining(", "))));

        System.out.println(sameAgedPersons);

//        ==============================================================================

//        9. Count the number of persons who have the same age - (groupingBy using -> collectingAndThen -> counting())

        var personsWithSameAge = getPersons().stream()
                .collect(groupingBy(Person::age, collectingAndThen(counting(), Long::intValue)));

        System.out.println(personsWithSameAge);

//        ==============================================================================

//        10. Find the name of the person who has the maximum age - (collectingAndThen -> maxBy -> map)

        var maxAgePerson = getPersons().stream()
                .collect(collectingAndThen(maxBy(comparing(Person::age)),
                        person -> person.map(Person::name).orElse("")));

        System.out.println(maxAgePerson);

//        ===============================================================================

//        11. Using teeing - combines all the collectors & merges into one

        var result = getPersons().stream()
                .collect(teeing(filtering(person -> person.age()<30, toSet()),
                        filtering(person -> person.age()>=30, toList()),
                        (c,v) -> {
                    c.addAll(v);
                    return c;
                        }));

        System.out.println(result);

//        =================================================================================

//        12. Use flatMapping()

        var answer = getPersons().stream()
                .collect(groupingBy(Person::age, mapping(Person::name,
                        flatMapping(name -> Stream.of(name.split("")), toList()))));

        System.out.println(answer);
    }

}
