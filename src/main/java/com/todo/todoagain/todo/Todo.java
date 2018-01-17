package com.todo.todoagain.todo;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Todo {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;
  private String title;
  private boolean isurgent;
  private boolean isdone;
  private Date date;

  public Todo(String title, boolean isurgent, boolean isdone) {
    this.title = title;
    this.isurgent = isurgent;
    this.isdone = isdone;
    this.date= new Date();
  }

  public Todo() {
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public boolean getIsurgent() {
    return isurgent;
  }

  public void setIsurgent(boolean isurgent) {
    this.isurgent = isurgent;
  }

  public boolean getIsdone() {
    return isdone;
  }

  public void setIsdone(boolean isdone) {
    this.isdone = isdone;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }
}
