package com.bookRealm.api_v1.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookRealm.api_v1.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer>{

}
