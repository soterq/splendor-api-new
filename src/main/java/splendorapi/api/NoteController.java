package splendorapi.api;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import splendorapi.Mapper;
import splendorapi.api.viewmodel.NoteViewModel;
import splendorapi.model.Note;
import splendorapi.model.Notebook;
import splendorapi.repository.NoteRepository;
import splendorapi.repository.NotebookRepository;

import javax.persistence.EntityNotFoundException;
import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/notes")
@CrossOrigin
public class NoteController {
    private NoteRepository noteRepository;
    private NotebookRepository notebookRepository;
    private Mapper mapper;

    public NoteController(NoteRepository noteRepository, NotebookRepository notebookRepository, Mapper mapper) {
        this.noteRepository = noteRepository;
        this.notebookRepository = notebookRepository;
        this.mapper = mapper;
    }

    @GetMapping("/all")
    public List<NoteViewModel> all() {
        List<Note> notes = this.noteRepository.findAll();

        return notes.stream()
                .map(note -> this.mapper.convertToNoteViewModel(note))
                .collect(Collectors.toList());
    }

    @GetMapping("/byId/{id}")
    public NoteViewModel byId(@PathVariable String id) {
        Note note = this.noteRepository.findById(Long.valueOf(id)).orElse(null);

        if (note == null) {
            throw new EntityNotFoundException();
        }

        return this.mapper.convertToNoteViewModel(note);
    }

    @GetMapping("/byNotebook/{notebookId}")
    public List<NoteViewModel> byNotebook(@PathVariable String notebookId) {
        List<Note> notes = new ArrayList<>();

        Optional<Notebook> notebook = this.notebookRepository.findById(Long.valueOf(notebookId));

        if (notebook.isPresent()) {
            notes = this.noteRepository.findAllByNotebook(notebook.get());
        }

        return notes.stream()
                .map(note -> this.mapper.convertToNoteViewModel(note))
                .collect(Collectors.toList());
    }

    @PostMapping
    public Note save(@RequestBody NoteViewModel noteCreateViewModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException();
        }

        Note noteEntity = this.mapper.convertToNoteEntity(noteCreateViewModel);

        return noteRepository.save(noteEntity);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        this.noteRepository.deleteById(Long.valueOf(id));
    }
}