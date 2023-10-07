package org.gazizulin.ChatProject.repositories;

import org.gazizulin.ChatProject.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Timur Gazizulin
 */

@Repository
public interface MyUserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String name);
    List<User> findAllByRole(String role);
    boolean existsByUsername(String username);
}
