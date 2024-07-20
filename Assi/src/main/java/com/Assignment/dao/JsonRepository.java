package com.Assignment.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.Assignment.entities.JsonModel;

public interface JsonRepository extends JpaRepository<JsonModel, Long> {
}

