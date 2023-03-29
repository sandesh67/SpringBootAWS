package com.amazon.ec2.demo.springbootec2;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {


  @Id
  @GeneratedValue
  private int id;

  private String name;



  public int getId() {
    return id;
  }

  public User(String name) {
    this.name = name;
  }

  public User() {

  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
