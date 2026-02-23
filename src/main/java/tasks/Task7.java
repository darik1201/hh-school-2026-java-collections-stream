package tasks;

import common.Company;
import common.Vacancy;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/*
Из коллекции компаний необходимо получить всевозможные различные названия вакансий
 */
public class Task7 {

  public static Set<String> vacancyNames(Collection<Company> companies) {
    // Создаю set для вакансий, прохожу 2 циклами по всем компаниям и беру всех вакансии и записываю названия в сет
    // Временная сложность O(N of companies + N of vacancies of this company)
    // По памяти O(N of vacancies of unique vacancies)
    Set<String> vacancyTitlesByCompanies = new HashSet<>();
    for (Company company : companies) {
      for (Vacancy vacancy : company.getVacancies()) {
        vacancyTitlesByCompanies.add(vacancy.getTitle());
      }
    }
    return vacancyTitlesByCompanies;
  }
}
