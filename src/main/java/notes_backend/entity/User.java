package notes_backend.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "users")
@Getter
@Setter
public class User {
    @MongoId
    private String id;

    @NotBlank
    private String userName;

}
