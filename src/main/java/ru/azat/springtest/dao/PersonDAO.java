package ru.azat.springtest.dao;

import org.springframework.stereotype.Component;
import ru.azat.springtest.models.Person;

import java.util.ArrayList;
import java.util.List;
@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;
    private List<Person> people;

    {
        people = new ArrayList<>();
        people.add(new Person(++PEOPLE_COUNT,"Tom",24,"tom@mail.ru"));
        people.add(new Person(++PEOPLE_COUNT,"Bob",52,"bob@mail.ru"));
        people.add(new Person(++PEOPLE_COUNT,"Mike",18,"mike@mail.ru"));
        people.add(new Person(++PEOPLE_COUNT,"Katy",34,"katy@mail.ru"));
    }

    public List<Person> index() {
        return people;
    }

    public Person show(int id) {
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    public void save(Person person) {
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }

    public void update(int id, Person personUpdate) {
        Person personToBeUpdate = show(id);
        personToBeUpdate.setName(personUpdate.getName());
        personToBeUpdate.setAge(personUpdate.getAge());
        personToBeUpdate.setEmail(personUpdate.getEmail());
    }

    public void delete(int id) {
        people.removeIf(person -> person.getId() == id);
    }
}
