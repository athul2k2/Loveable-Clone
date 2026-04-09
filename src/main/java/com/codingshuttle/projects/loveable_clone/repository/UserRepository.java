package com.codingshuttle.projects.loveable_clone.repository;

import com.codingshuttle.projects.loveable_clone.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
