package tasks;

import common.Company;
import common.Vacancy;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/*
Из коллекции компаний необходимо получить всевозможные различные названия вакансий
 */
public class Task7 {

  public static Set<String> vacancyNames(Collection<Company> companies) {
    // Тут через стрим собираю в flatmap все вакансии каждой компании и потом собираю это в сет
    // Временная сложность O(N of companies + N of vacancies of this company)
    // По памяти O(N of vacancies of unique vacancies)
   return companies.stream()
       .flatMap(company -> company.getVacancies().stream())
       .map(Vacancy::getTitle)
       .collect(Collectors.toSet());
  }
}
