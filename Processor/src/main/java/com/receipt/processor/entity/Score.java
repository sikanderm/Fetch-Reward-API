package com.receipt.processor.entity;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Score {

	 @Id
	 @GeneratedValue(generator = "UUID")
	private UUID id;
    private int score;
    
    public Score() {
    }
    
	public Score(UUID id, int score) {
		this.id = id;
		this.score = score;
	}

	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
    
    

}