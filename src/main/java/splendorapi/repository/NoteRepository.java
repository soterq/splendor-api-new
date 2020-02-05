package splendorapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import splendorapi.model.Note;
import splendorapi.model.Notebook;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findAllByNotebook(Notebook notebook);
}
