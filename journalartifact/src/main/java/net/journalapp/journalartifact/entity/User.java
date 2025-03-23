package net.journalapp.journalartifact.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
@Data
@NoArgsConstructor
public class User {
    @Id // unique key
    private ObjectId id;
    @Indexed(unique = true)
    @NonNull
    private String username;
    private String password;
    @DBRef // link to journal entry
    private List<JournalEntry> journalEntries = new ArrayList<>();
    private List<String> roles ;

}
