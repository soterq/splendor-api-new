package splendorapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import splendorapi.model.Notebook;

public interface NotebookRepository extends JpaRepository<Notebook, Long> {
}
