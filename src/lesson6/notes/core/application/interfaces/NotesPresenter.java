package lesson6.notes.core.application.interfaces;

import lesson6.notes.core.domain.Note;

import java.util.Collection;

public interface NotesPresenter {
    void printAll(Collection<Note> notes);
}
