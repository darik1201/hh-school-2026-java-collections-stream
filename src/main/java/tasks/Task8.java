package tasks;

import common.Person;
import common.PersonService;
import common.PersonWithResumes;
import common.Resume;

import java.util.*;
import java.util.stream.Collectors;

/*
  Еще один вариант задачи обогащения
  На вход имеем коллекцию персон
  Сервис умеет по personId искать их резюме (у каждой персоны может быть несколько резюме)
  На выходе хотим получить объекты с персоной и ее списком резюме
 */
public class Task8 {
  private final PersonService personService;

  public Task8(PersonService personService) {
    this.personService = personService;
  }

  public Set<PersonWithResumes> enrichPersonsWithResumes(Collection<Person> persons) {
    // Собираю айдишники персон через стрим в сет
    Set<Integer> personIds = persons.stream().map(Person::id).collect(Collectors.toSet());
    // Далее собираю все резюме по собранным айди
    Set<Resume> resumes = personService.findResumes(personIds);
    // Тут группирую резюме по айдиникам персоны
    Map<Integer, Set<Resume>> resumesById = resumes.stream().collect(Collectors.groupingBy(Resume::personId, Collectors.toSet()));

    Set<PersonWithResumes> personWithResumes = new HashSet<>();
    // Теперь пробегаю по персонам и если резюме, то записываю в ответ, иначе пустой сет
    for (Person person : persons) {
      Set<Resume> personResumes = resumesById.getOrDefault(person.id(), Set.of());
      personWithResumes.add(new PersonWithResumes(person, personResumes));
    }

    return personWithResumes;

    // По временной сложности будет O(N of persons + N of Resumes)
    // По памяти O(N of persons + N of Resumes)
  }
}
