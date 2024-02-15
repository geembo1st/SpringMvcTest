package ru.azat.springtest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.azat.springtest.models.Person;

@Repository
public interface PeopleRepository extends JpaRepository<Person,Integer> {
}
