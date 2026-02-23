package tasks;

import common.Company;
import common.Person;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

/*
Задача 3
Отсортировать коллекцию сначала по фамилии, по имени (при равной фамилии), и по дате создания (при равных фамилии и имени)
 */
public class Task3 {

  public static List<Person> sort(Collection<Person> persons) {
    // Создал лист, чтобы я мог использовать на нем компаратор. В компараторе сначала по фамилии, потом по имени, потом по дате создания
    List<Person> listPersons = new ArrayList<>(persons);
    listPersons.sort(Comparator.comparing(Person::secondName).thenComparing(Person::firstName).thenComparing(Person::createdAt));
    return listPersons;
  }
}
