package org.example.domain;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "person")
public class Person
{
  @Column(name = "id")
  @Generated(GenerationTime.INSERT)
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  private Long id;

  @Column(columnDefinition = "CHAR(50)", length = 50, name = "first_name")
  @NotNull
  @Type(type = "org.example.hibernate.type.CharType")
  private String firstName;

  @Column(columnDefinition = "CHAR(50)", length = 50, name = "last_name")
  @NotNull
  private String lastName;

  @Column(columnDefinition = "CHAR(25)", length = 25, name = "middle_name")
  @NotNull
  @Type(type = "org.example.hibernate.type.CharType")
  private String middleName;

  Person()
  {
  }

  public Person(String firstName, String middleName, String lastName)
  {
    this.firstName = firstName;
    this.lastName = lastName;
    this.middleName = middleName;
  }

  public Long getId()
  {
    return id;
  }

  public String getFirstName()
  {
    return firstName;
  }

  public String getLastName()
  {
    return lastName;
  }

  public String getMiddleName()
  {
    return middleName;
  }
}
