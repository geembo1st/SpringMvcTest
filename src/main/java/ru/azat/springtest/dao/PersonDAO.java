package ru.azat.springtest.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.azat.springtest.models.Person;

import java.util.Date;
import java.util.List;

@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;
    private final SessionFactory sessionFactory;

    @Autowired
    public PersonDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Transactional(readOnly = true)
    public List<Person> index() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select p from Person p", Person.class).getResultList();
    }
    @Transactional(readOnly = true)
    public Person show(String email) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Person WHERE email = :email").setParameter("email",email);
        return (Person) query.uniqueResult();
    }
    @Transactional(readOnly = true)
    public Person show(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Person.class,id);
    }
    @Transactional
    public void save(Person person) {
        person.getCreatedAt(new Date());
        Session session = sessionFactory.getCurrentSession();
        session.save(person);
    }
    @Transactional
    public void update(int id, Person personUpdate) {
        Session session = sessionFactory.getCurrentSession();
        Person personToBeUpdate = session.get(Person.class,id);
        personToBeUpdate.setName(personUpdate.getName());
        personToBeUpdate.setAge(personUpdate.getAge());
        personToBeUpdate.setEmail(personUpdate.getEmail());
    }
    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Person.class, id));
    }

}
