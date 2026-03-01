package tasks;

import common.Person;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/*
Далее вы увидите код, который специально написан максимально плохо.
Постарайтесь без ругани привести его в надлежащий вид
P.S. Код в целом рабочий (не везде), комментарии оставлены чтобы вам проще понять чего же хотел автор
P.P.S Здесь ваши правки необходимо прокомментировать (можно в коде, можно в PR на Github)
 */
public class Task9 {

  private long count;

  // Костыль, эластик всегда выдает в топе "фальшивую персону".
  // Конвертируем начиная со второй
  public List<String> getNames(List<Person> persons) {
    // Понял, что проверка не нужна, так как не делаем больше удаления
    // persons.remove(0); лучше не удалять из изначльного листа 1 элемент, так как мы так теряем его для других функций. Лучше в stream добавить skip(1)
    return persons.stream()
        .skip(1)
        .map(Person::firstName)
        .collect(Collectors.toList());
  }

  // Зачем-то нужны различные имена этих же персон (без учета фальшивой разумеется)
  public Set<String> getDifferentNames(List<Person> persons) {
    //можн переписать под сет, который сам удалит дубликаты
    return new HashSet<>(getNames(persons));
  }

  // Тут фронтовая логика, делаем за них работу - склеиваем ФИО
  public String convertPersonToString(Person person) {
    // Тут стрим по 3 частям ФИО и с помощью фильтра убираю пустые
    return Stream.of(person.secondName(), person.firstName(), person.middleName())
        .filter(Objects::nonNull)
        .collect(Collectors.joining(" "));
  }

  // словарь id персоны -> ее имя
  public Map<Integer, String> getPersonNames(Collection<Person> persons) {
    return persons.stream()
        .collect(Collectors.toMap(
            Person::id,
            this::convertPersonToString,
            (existing, replace) -> existing,
            () -> new HashMap<>(persons.size())
        ));
  }

  // есть ли совпадающие в двух коллекциях персоны?
  public boolean hasSamePersons(Collection<Person> persons1, Collection<Person> persons2) {
    // Решил ускорить через HashSet, для начала записываю туда это O(N of persons1), далее прохожу по persons2 это O(N of persons2) => Итог по времени O(n+m)
    // По памяти O(N of persons 1)
    Set<Person> setOfPersons1 = new HashSet<>(persons1);
    return persons2.stream()
        .anyMatch(setOfPersons1::contains);
  }

  // Посчитать число четных чисел
  public long countEven(Stream<Integer> numbers) {
    //  Можно просто использовать count без лишних переменных
    return numbers.filter(num -> num % 2 == 0)
        .count();
  }

  // Загадка - объясните почему assert тут всегда верен
  // Пояснение в чем соль - мы перетасовали числа, обернули в HashSet, а toString() у него вернул их в сортированном порядке
  void listVsSet() {
    // Предположу что это из-за того что все числа идут по порядку и поэтому их хеши выстраиваются как сортированные
    List<Integer> integers = IntStream.rangeClosed(1, 10000).boxed().collect(Collectors.toList());
    List<Integer> snapshot = new ArrayList<>(integers);
    Collections.shuffle(integers);
    Set<Integer> set = new HashSet<>(integers);
    assert snapshot.toString().equals(set.toString());
  }
}
