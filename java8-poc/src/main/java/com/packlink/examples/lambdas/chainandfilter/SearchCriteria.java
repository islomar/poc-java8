package com.packlink.examples.lambdas.chainandfilter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/**
 *
 */
public class SearchCriteria {

    private final Map<String, Predicate<Person>> searchMap = new HashMap<>();

    private SearchCriteria() {

        super();
        initSearchMap();
    }

    private void initSearchMap() {

        Predicate<Person> allDrivers = p -> p.getAge() >= 16;
        Predicate<Person> allDraftees = p -> p.getAge() >= 18 && p.getAge() <= 25 && p.getGender() == Person.Gender.MALE;
        Predicate<Person> allPilots = p -> p.getAge() >= 23 && p.getAge() <= 65;

        searchMap.put("allDrivers", allDrivers);
        searchMap.put("allDraftees", allDraftees);
        searchMap.put("allPilots", allPilots);

    }

    public Predicate<Person> getCriteria(String PredicateName) {

        Predicate<Person> target;

        target = searchMap.get(PredicateName);

        if (target == null) {

            System.out.println("Search Criteria not found... ");
            System.exit(1);

        }

        return target;

    }

    public static SearchCriteria getInstance() {

        return new SearchCriteria();
    }


    public static void main(String[] args) {
//
//        List<com.packlink.examples.lambdas.person.Person> pl = com.packlink.examples.lambdas.chainandfilter.Person.createShortList();
//
//        SearchCriteria search = SearchCriteria.getInstance();
//
//        System.out.println("\n=== Western Pilot Phone List ===");

//        pl.stream().filter(search.getCriteria("allPilots"))
//                .forEach(com.packlink.examples.lambdas.chainandfilter.Person::printWesternName);
//
//
//        System.out.println("\n=== Eastern Draftee Phone List ===");
//
//        pl.stream().filter(search.getCriteria("allDraftees"))
//                .forEach(com.packlink.examples.lambdas.person.Person::printEasternName);
    }
}
