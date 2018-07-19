package com.wajda.recipeproject.converters;

import com.wajda.recipeproject.commands.NotesCommand;
import com.wajda.recipeproject.model.Notes;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class NotesToNotesCommand implements Converter<Notes, NotesCommand> {

    @Synchronized
    @Nullable
    @Override
    public NotesCommand convert(Notes source) {
        if (source == null) {
            return null;
        }

        final NotesCommand notes = new NotesCommand();
        notes.setRecipeNotes(source.getRecipeNotes());
        notes.setId(source.getId());
        return notes;
    }
}
