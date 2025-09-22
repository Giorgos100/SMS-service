package org.gch.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.gch.entity.Message;

import java.util.List;

@ApplicationScoped
public class MessageRepository implements PanacheRepository<Message> {

    public List<Message> search(String query) {
        return list("content like ?1", "%" + query + "%");
    }
}
