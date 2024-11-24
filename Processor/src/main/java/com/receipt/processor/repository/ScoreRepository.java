package com.receipt.processor.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import com.receipt.processor.entity.Score;

public interface ScoreRepository extends JpaRepository<Score, UUID> {

}
