package notes_backend.entity;

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

    private String userName;

}
