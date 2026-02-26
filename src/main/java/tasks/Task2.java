package tasks;

import common.Person;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
Задача 2
На вход принимаются две коллекции объектов Person и величина limit
Необходимо объеденить обе коллекции
отсортировать персоны по дате создания и выдать первые limit штук.
 */
public class Task2 {

  public static List<Person> combineAndSortWithLimit(Collection<Person> persons1,
                                                     Collection<Person> persons2,
                                                     int limit) {
    // Сначала объединяю 2 потока, потом их сортирую по createdAt c лимитом, а потом собираю в лист
    // По времени O(n log n), так самое тут сортирововка, а она O(n log n)
    // По памяти O(n), так как надо хранить отсорированный массив. n - тут person1.size()+person2.size()
    return Stream.concat(persons1.stream(), persons2.stream()).sorted(Comparator.comparing(Person::createdAt)).limit(limit).collect(Collectors.toList());
  }
}
