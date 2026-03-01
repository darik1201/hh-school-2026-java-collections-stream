package tasks;

import common.Area;
import common.Person;

import java.util.*;
import java.util.stream.Collectors;

/*
Имеются
- коллекция персон Collection<Person>
- словарь Map<Integer, Set<Integer>>, сопоставляющий каждой персоне множество id регионов
- коллекция всех регионов Collection<Area>
На выходе хочется получить множество строк вида "Имя - регион". Если у персон регионов несколько, таких строк так же будет несколько
 */
public class Task6 {

  public static Set<String> getPersonDescriptions(Collection<Person> persons,
                                                  Map<Integer, Set<Integer>> personAreaIds,
                                                  Collection<Area> areas) {
    // Собираю мапу через стрим для быстрого поиска
    Map<Integer, Area> areaById = areas.stream()
        .collect(Collectors.toMap(Area::getId, area -> area));
    // Собираю через стрим так: через flatMap разделяю персон в поток строк, которые потом собираю в сет. Внутри flatmap работает так: собираю персон, ища их area по их айди и собираю в формате имя - регион
    // По времени будет O(N of areas) + O(N of areas persons)
    //  А по памяти используется два массива размером N of areas и N of persons * N of his areas => O(N + N of persons * N of his areas)
    return persons.stream()
        .flatMap(
            person -> personAreaIds.getOrDefault(person.id(), Collections.emptySet()).stream()
                .map(areaById::get)
                .map(area -> person.firstName() + " - " + area.getName()))
        .collect(Collectors.toSet());
  }
}
