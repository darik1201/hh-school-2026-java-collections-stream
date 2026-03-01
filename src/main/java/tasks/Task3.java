package tasks;

import common.Company;
import common.Person;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/*
Задача 3
Отсортировать коллекцию сначала по фамилии, по имени (при равной фамилии), и по дате создания (при равных фамилии и имени)
 */
public class Task3 {

  public static List<Person> sort(Collection<Person> persons) {
    // Теперь создал стрим по persons, где сортирую сначала по secondName, потом по firstName, потом по createdAt и собираю в лист
    return persons.stream()
        .sorted(Comparator.comparing(Person::secondName)
            .thenComparing(Person::firstName)
            .thenComparing(Person::createdAt))
        .collect(Collectors.toList());
  }
}
