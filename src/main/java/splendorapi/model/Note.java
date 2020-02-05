package splendorapi.model;

import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String text;

    @ManyToOne
    private Notebook noteBook;

    private Date lastModified;

    public Note() {
        this.lastModified = new Date();
    }

    public Note(Long id, String title, String text, Notebook noteBook) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.noteBook = noteBook;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Notebook getNoteBook() {
        return noteBook;
    }

    public void setNoteBook(Notebook noteBook) {
        this.noteBook = noteBook;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public Long getId() {
        return id;
    }
}
