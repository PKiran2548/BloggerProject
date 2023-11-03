package com.bikkadit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bikkadit.entity.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
