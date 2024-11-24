package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import entity.Receipt;
import entity.Score;
import service.ScoreService;

@RestController
public class ScoreController {
	
	@Autowired
	private ScoreService scoreService;
	
	 @PostMapping("/receipts/process")
	    public Score addScore(@RequestBody Receipt receipt) {
	        // Example logic for creating a score

	        Score score = new Score();  // Creating a Score object based on calculated scoreValue
	        score.setId(2557777);
	        score.setScore(28);
	        return scoreService.saveScore(score);  // Save the score and return it
	    }

	 @GetMapping("/receipts/{id}/points")
	 public Score findScoreById(@PathVariable int id) {
		 return scoreService.getScoreById(id);
	 }
	

}
