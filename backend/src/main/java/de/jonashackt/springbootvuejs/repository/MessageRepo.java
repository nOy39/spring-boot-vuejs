package de.jonashackt.springbootvuejs.repository;

import de.jonashackt.springbootvuejs.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepo extends JpaRepository<Message, Long> {
}
