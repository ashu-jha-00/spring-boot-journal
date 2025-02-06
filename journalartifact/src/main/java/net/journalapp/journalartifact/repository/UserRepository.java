package net.journalapp.journalartifact.repository;

import net.journalapp.journalartifact.entity.JournalEntry;
import net.journalapp.journalartifact.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {
   User findByUserName(String userName);
}
