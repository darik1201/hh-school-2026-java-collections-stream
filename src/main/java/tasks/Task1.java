package tasks;

import common.Person;
import common.PersonService;

import java.util.*;

/*
Задача 1
Метод на входе принимает List<Integer> id людей, ходит за ними в сервис
(он выдает несортированный Set<Person>, внутренняя работа сервиса неизвестна)
нужно их отсортировать в том же порядке, что и переданные id.
Оценить асимптотику работы
 */
public class Task1 {

  private final PersonService personService;

  public Task1(PersonService personService) {
    this.personService = personService;
  }

  public List<Person> findOrderedPersons(List<Integer> personIds) {
    Set<Person> persons = personService.findPersons(personIds);
    // Создаю мапу для того чтобы быстро доставать персону по айди
    Map<Integer, Person> personByID = new HashMap<>();
    // Прохожу по персонам и записываю это в мапу. По сложности это O(n), так как линейно проходимся
    for (Person person : persons) {
      personByID.put(person.id(), person);
    }
    // Тут я создаю список для ответа, где будут персоны в нужном порядке. Он тоже будет O(n), так как проходимся линейно, а в мапе поиск O(1)
    List<Person> personsInOrder = new ArrayList<>();
    for (Integer id : personIds) {
      personsInOrder.add(personByID.get(id));
    }
    return personsInOrder;
    // По памяти будет тоже O(n), так как создаю два массива размера N
  }
}
