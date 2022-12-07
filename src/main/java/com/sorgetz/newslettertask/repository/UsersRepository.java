package com.sorgetz.newslettertask.repository;

import com.sorgetz.newslettertask.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {
}
