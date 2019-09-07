package com.ihorcompany.fd.repository;

import com.ihorcompany.fd.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {

}
