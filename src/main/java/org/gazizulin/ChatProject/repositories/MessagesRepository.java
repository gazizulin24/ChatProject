package org.gazizulin.ChatProject.repositories;

import org.gazizulin.ChatProject.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Timur Gazizulin
 */

@Repository
public interface MessagesRepository extends JpaRepository<Message, Integer> {
}
