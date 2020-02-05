package splendorapi.repository;



import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import splendorapi.model.Note;
import splendorapi.model.Notebook;


/**
 * This component will only execute (and get instantiated) if the
 * property noteit.db.recreate is set to true in the
 * application.properties file
 */

@Component
@ConditionalOnProperty(name = "splendor.db.recreate", havingValue = "true")
public class DbSeeder implements CommandLineRunner {
    private NotebookRepository notebookRepository;
    private NoteRepository noteRepository;

    public DbSeeder(NotebookRepository notebookRepository,
                    NoteRepository noteRepository) {
        this.notebookRepository = notebookRepository;
        this.noteRepository = noteRepository;
    }


    @Override
    public void run(String... args) {
        // Remove all existing entities
        this.notebookRepository.deleteAll();
        this.noteRepository.deleteAll();


        // Save a default notebook
        Notebook defaultNotebook = new Notebook("Default");
        notebookRepository.save(defaultNotebook);

        Notebook quotesNotebook = new Notebook("Quotes");
        this.notebookRepository.save(quotesNotebook);

        // Save the welcome note
        Note note = new Note("Hello", "Welcome to Note It", defaultNotebook);
        noteRepository.save(note);

        // Save a quote note
        Note quoteNote = new Note("Latin Quote", "Carpe Diem", quotesNotebook);
        noteRepository.save(quoteNote);

        System.out.println("Initialized database");
    }
}