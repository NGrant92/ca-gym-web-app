package models;

import play.db.jpa.Model;

import javax.persistence.Entity;

/**
 * This class is to store a string that is the member's personal note
 *
 * @author Niall Grant
 * @version 2017.05.19
 */
@Entity
public class Todo extends Model
{
    public String comment;

    /**
     * Constructor method
     * @param comment Note from member to be stored in their array list
     */
    public Todo(String comment) {
        this.comment = comment;
    }

    /**
     * Returns the note
     * @return the note
     */
    public String getComment() {

        return comment;
    }
}