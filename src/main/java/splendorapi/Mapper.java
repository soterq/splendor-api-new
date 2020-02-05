package splendorapi;

import org.springframework.stereotype.Component;
import splendorapi.api.viewmodel.NoteViewModel;
import splendorapi.api.viewmodel.NotebookViewModel;
import splendorapi.model.Note;
import splendorapi.model.Notebook;
import splendorapi.repository.NotebookRepository;

@Component
public class Mapper {
    private NotebookRepository notebookRepository;

    public Mapper(NotebookRepository notebookRepository) {
        this.notebookRepository = notebookRepository;
    }

    public NoteViewModel convertToNoteViewModel(Note entity) {
        NoteViewModel viewModel = new NoteViewModel();
        viewModel.setTitle(entity.getTitle());
        viewModel.setId(entity.getId().toString());
        viewModel.setLastModifiedOn(entity.getLastModified());
        viewModel.setText(entity.getText());
        viewModel.setNotebookId(entity.getNoteBook().getId().toString());
        return viewModel;
    }

    public Note convertToNoteEntity(NoteViewModel viewModel) {
        Notebook notebook = this.notebookRepository.findById(Long.valueOf(viewModel.getNotebookId())).get();
        return new Note(Long.valueOf(viewModel.getId()), viewModel.getTitle(), viewModel.getText(), notebook);
    }

    public NotebookViewModel convertToNotebookViewModel(Notebook entity) {
        NotebookViewModel viewModel = new NotebookViewModel();
        viewModel.setId(entity.getId().toString());
        viewModel.setName(entity.getName());
        viewModel.setNbNotes(entity.getNotes().size());

        return viewModel;
    }

    public Notebook convertToNotebookEntity(NotebookViewModel viewModel) {
        return new Notebook(Long.valueOf(viewModel.getId()), viewModel.getName());
    }
}