package tasks;

import common.Area;
import common.Person;

import java.util.*;

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
    // Создаю мапу где будут area по id. Прохожу в цикле по всем areas и записываю в эту мапу
    Map<Integer, Area> areaById = new HashMap<>();
    for (Area area : areas) {
      areaById.put(area.getId(), area);
    }

    Set<String> result = new HashSet<>();
    // Прохожу по всем персонам, получаю его areaIDs и записываю строки формата Имя - area
    for (Person person : persons) {
      Set<Integer> areaIds = personAreaIds.get(person.id());
      for (Integer areaId : areaIds) {
        Area area = areaById.get(areaId);
        result.add(person.firstName() + " - " + area.getName());
      }
    }
    return result;
    // Тут 2 цикла, поэтому сложность будет у 1 O(N of areas), у второго O(N of areas x N of persons), то есть итоговая сложность O(N of areas x N of persons)
    //  А по памяти используется два массива размером N of areas и N of persons * N of his areas => O(N + N of persons * N of his areas)
  }
}
