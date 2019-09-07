package com.ihorcompany.fd.serviceImplement;

import com.ihorcompany.fd.model.Message;
import com.ihorcompany.fd.model.User;
import com.ihorcompany.fd.repository.MessageRepository;
import com.ihorcompany.fd.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImplement implements MessageService {

    private MessageRepository messageRepository;

    @Autowired
    public void setMessageRepository(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public void send(Message message, User sender, User receiver) {
        message.setReceiver(receiver);
        message.setSender(sender);
        LocalDate date = LocalDate.now();
        message.setSentDate(date);
        messageRepository.save(message);
    }

    @Override
    public List<Message> findAll(Pageable pageable) {
        return messageRepository.findAll(pageable).getContent();
    }

    @Override
    public List<Message> findTopTen() {
        return messageRepository.findAll(PageRequest.of(1, 10, Sort.by("sentDate"))).getContent();
    }

    @Override
    public void save(Message message) {
        messageRepository.save(message);
    }

    @Override
    public Optional<Message> findById(Long id) {
        return messageRepository.findById(id);
    }
}
