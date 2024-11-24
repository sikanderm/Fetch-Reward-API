package com.receipt.processor.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.receipt.processor.entity.Receipt;
import com.receipt.processor.entity.Response;
import com.receipt.processor.entity.Score;
import com.receipt.processor.repository.ScoreRepository;

@Service
public class ScoreService {
	
	@Autowired
	 private ScoreRepository scoreRepository;
	
	public UUID saveScore(Score score) {
        scoreRepository.save(score);
        return score.getId();
    }

	public int getScoreById(UUID id) {
        Score score = scoreRepository.findById(id).orElse(null);
        return score.getScore();
	 }
	 
	 
	
	

}
