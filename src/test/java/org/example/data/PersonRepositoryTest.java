package org.example.data;

import org.example.domain.Person;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration(locations = "classpath:springContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@TransactionConfiguration
public class PersonRepositoryTest
{
  private static final String FIRST_NAME  = "William";
  private static final String LAST_NAME   = "Gates";
  private static final String MIDDLE_NAME = "Henry";

  @Autowired
  PersonRepository personRepository;

  @Before
  public void before()
  {
    personRepository.deleteAllInBatch();
    personRepository.save(new Person(FIRST_NAME, MIDDLE_NAME, LAST_NAME));
  }

  @Test
  public void test()
  {
    Assert.assertEquals(1, personRepository.findAll().size());
    Assert.assertNotNull(personRepository.findByFirstName(FIRST_NAME));
    Assert.assertNull(personRepository.findByLastName(LAST_NAME));
    Assert.assertNotNull(personRepository.findByMiddleName(MIDDLE_NAME));
  }
}
