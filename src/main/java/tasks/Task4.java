package tasks;

import common.ApiPersonDto;
import common.Person;
import common.PersonConverter;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

/*
Задача 4
Список персон класса Person необходимо сконвертировать в список ApiPersonDto
(предположим, что это некоторый внешний формат)
Конвертер для одной персоны - personConverter.convert()
FYI - DTO = Data Transfer Object - распространенный паттерн, можно погуглить
 */
public class Task4 {

  private final PersonConverter personConverter;

  public Task4(PersonConverter personConverter) {
    this.personConverter = personConverter;
  }

  public List<ApiPersonDto> convert(List<Person> persons) {
    // Создаю лист, куда будут складывать конвертированных персон. Далее прохожусь по всем и добавлению в него конвертированную персону
    // По времени будет O(n), так как линейно прохожусь. По памяти тоже O(n), так как создаю лист размером n
    List<ApiPersonDto> convertedPersons = new ArrayList<>();
    for (Person person : persons) {
      convertedPersons.add(personConverter.convert(person));
    }
    return convertedPersons;
  }
}
