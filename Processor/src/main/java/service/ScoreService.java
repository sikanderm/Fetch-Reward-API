package service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entity.Receipt;
import entity.Score;
import repository.ScoreRepository;

@Service
public class ScoreService {
	
	@Autowired
	 private ScoreRepository scoreRepository;
	
	public Score saveScore(Score score) {
        return scoreRepository.save(score);
    }

	public Score getScoreById(Integer id) {
        return scoreRepository.findById(id).orElse(null);
	 }
	 
	 
	
	

}
