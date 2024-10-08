package lesson6;

import lesson6.database.NotesDatabase;
import lesson6.notes.core.application.ConcreteNoteEditor;
import lesson6.notes.infrastructure.persistance.NotesDbContext;
import lesson6.notes.presentation.queries.controllers.NotesController;
import lesson6.notes.presentation.queries.views.NotesConsolePresenter;

public class Program {
    public static void main(String[] args) {
        NotesController controller = new NotesController(
                new ConcreteNoteEditor(
                        new NotesDbContext(
                                new NotesDatabase()), new NotesConsolePresenter()));
        controller.routeGetAll();
    }
}
