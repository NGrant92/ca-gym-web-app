package models;

import play.db.jpa.Model;

import javax.persistence.Entity;

@Entity
public class Todo extends Model
{
  public String comment;

  public Todo(String comment) {
      this.comment = comment;
  }

  public String getComment() {
        return comment;
  }
}