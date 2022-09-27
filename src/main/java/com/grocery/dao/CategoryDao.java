package com.grocery.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grocery.model.Category;

public interface CategoryDao extends JpaRepository<Category, Long>  {


}
