package notes_backend.entity.note;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Document(collection = "notes")
@Getter
@Setter
@Builder
public class Note {

    @MongoId
    private String id;

    @Field("user_id")
    private String userId;

    @NotBlank
    private String title;

    @NotBlank
    private String text;

    private Set<Tag> tags = new HashSet<>();

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

}
