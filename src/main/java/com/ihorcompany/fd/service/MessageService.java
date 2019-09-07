package com.ihorcompany.fd.service;

import com.ihorcompany.fd.model.Message;
import com.ihorcompany.fd.model.User;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface MessageService {

    void send(Message message, User sender, User receiver);
    List<Message> findAll(Pageable pageable);
    List<Message> findTopTen();
    void save(Message message);
    Optional<Message> findById(Long id);

}
