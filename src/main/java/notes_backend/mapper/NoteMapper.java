package notes_backend.mapper;

import notes_backend.config.MapperConfig;
import notes_backend.dto.note.NoteContentResponseDto;
import notes_backend.dto.note.NoteIdResponseDto;
import notes_backend.dto.note.create.NoteCreateRequestDto;
import notes_backend.dto.note.create.NoteCreateResponseDto;
import notes_backend.dto.note.NoteResponseDto;
import notes_backend.dto.note.update.NoteUpdateRequestDto;
import notes_backend.dto.note.update.NoteUpdatedResponseDto;
import notes_backend.entity.note.Note;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface NoteMapper {

    Note toModel(NoteCreateRequestDto requestDto);

    NoteCreateResponseDto toCreateResponseDto(Note note);

    NoteResponseDto toResponseDto(Note note);

    NoteUpdatedResponseDto toUpdatedResponseDto(Note note);

    void updateNoteFromDto(NoteUpdateRequestDto dto, @MappingTarget Note note);

    NoteContentResponseDto toNoteContentResponseDto(Note note);

    NoteIdResponseDto toNoteIdResponseDto(Note note);
}
