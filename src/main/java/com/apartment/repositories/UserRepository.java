package com.apartment.repositories;

import com.apartment.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    com.apartment.entity.User findByUsername(String username);
}
