package com.bookRealm.api_v1.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookRealm.api_v1.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

}
