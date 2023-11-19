package com.example.codetranslator.repository;

import com.example.codetranslator.entity.ExceptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExceptionRepository extends JpaRepository<ExceptionEntity, Integer> {
}
