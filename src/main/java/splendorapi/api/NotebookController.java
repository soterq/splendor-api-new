package splendorapi.api;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import splendorapi.Mapper;
import splendorapi.api.viewmodel.NotebookViewModel;
import splendorapi.model.Notebook;
import splendorapi.repository.NotebookRepository;


import javax.validation.ValidationException;
import java.util.List;

@RestController
@RequestMapping("/api/notebooks")
@CrossOrigin
public class NotebookController {
    private NotebookRepository notebookRepository;
    private Mapper mapper;

    public NotebookController(NotebookRepository notebookRepository, Mapper mapper) {
        this.notebookRepository = notebookRepository;
        this.mapper = mapper;
    }

    @GetMapping("/all")
    public List<Notebook> getAll() {
        return this.notebookRepository.findAll();
    }

    @PostMapping
    public Notebook save(@RequestBody NotebookViewModel notebookViewModel,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException();
        }
        Notebook notebookEntity = this.mapper.convertToNotebookEntity(notebookViewModel);
        return notebookRepository.save(notebookEntity);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        this.notebookRepository.deleteById(Long.valueOf(id));
    }
}