package com.example.ftpatika1stcasepostgresql.repository;

import com.example.ftpatika1stcasepostgresql.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
