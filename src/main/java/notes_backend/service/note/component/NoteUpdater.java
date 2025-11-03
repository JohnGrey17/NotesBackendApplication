package notes_backend.service.note.component;

import lombok.RequiredArgsConstructor;
import notes_backend.dto.note.update.NoteUpdateRequestDto;
import notes_backend.entity.note.Note;
import notes_backend.entity.note.Tag;
import notes_backend.exception.type.NoteException;
import org.springframework.stereotype.Component;

import java.util.EnumSet;
import java.util.Optional;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class NoteUpdater {

    public void applyUpdates(Note note, NoteUpdateRequestDto dto) {
        updateTitle(note, dto);
        updateText(note, dto);
        updateTags(note, dto);

        if (dto.getTitle() == null && dto.getText() == null && dto.getTags() == null) {
            throw new NoteException("No fields provided to update");
        }
    }

    private void updateTitle(Note note, NoteUpdateRequestDto dto) {
        Optional.ofNullable(dto.getTitle())
                .filter(t -> !t.isBlank())
                .ifPresentOrElse(
                        note::setTitle,
                        () -> {
                            if (dto.getTitle() != null)
                                throw new NoteException("Title cannot be blank");
                        }
                );
    }

    private void updateText(Note note, NoteUpdateRequestDto dto) {
        Optional.ofNullable(dto.getText())
                .filter(t -> !t.isBlank())
                .ifPresentOrElse(
                        note::setText,
                        () -> {
                            if (dto.getText() != null)
                                throw new NoteException("Text cannot be blank");
                        }
                );
    }

    private void updateTags(Note note, NoteUpdateRequestDto dto) {
        Optional.ofNullable(dto.getTags())
                .filter(tags -> !tags.isEmpty())
                .ifPresent(tags -> {
                    validateTags(tags);
                    note.setTags(tags);
                });
    }

    private void validateTags(Set<Tag> tags) {
        Set<Tag> allowedTags = EnumSet.allOf(Tag.class);

        boolean hasInvalid = tags.stream()
                .anyMatch(tag -> !allowedTags.contains(tag));

        if (hasInvalid) {
            throw new NoteException("Invalid tag detected. Allowed tags are: "
                    + allowedTags);
        }
    }
}
