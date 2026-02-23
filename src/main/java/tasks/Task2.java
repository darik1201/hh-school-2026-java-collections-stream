package tasks;

import common.Person;

import java.util.*;

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
    // Нахожу длину, чтобы сразу создать массив нужного размера
    int totalLength = persons1.size() + persons2.size();
    List<Person> mergePersons = new ArrayList<>(totalLength);
    // Добавляю все элементы из списков
    mergePersons.addAll(persons1);
    mergePersons.addAll(persons2);
    // Сортирую по дате создания у Person
    mergePersons.sort(Comparator.comparing(Person::createdAt));
    // Возвращаю массив, который берет элемент из mergePersons от 0 до минимума из размера массива и limit(в 1 тесте такой случай есть)
    return new ArrayList<>(mergePersons.subList(0, Math.min(limit, mergePersons.size())));

    // Сложность: по времени будет объединение O(len(persons1))+O(len(persons2))=O(n),
    // сортировка за O(nlogn), кусок списка берется за O(1). Беру наибольшее O(nlogn)
    // По памяти я использую массив, куда копирую все элементы из person1 и persons2, это
    // O(len(persons1))+O(len(persons2))=O(n), для ответа O(limit). Тут тоже беру наибольшее O(n)
    // Итог: по времени O(nlogn), по памяти O(n)
  }
}
