package com.receipt.processor.controller;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.receipt.processor.entity.Item;
import com.receipt.processor.entity.Receipt;
import com.receipt.processor.entity.Response;
import com.receipt.processor.entity.ResponseId;
import com.receipt.processor.entity.Score;
import com.receipt.processor.service.ScoreService;

@RestController
public class ScoreController {
	
	@Autowired
	private ScoreService scoreService;
	
	 @PostMapping("/receipts/process")
	    public ResponseId addScore(@RequestBody Receipt receipt) {
	        //creating a score
		 	int points = ScoreController.calculateScore(receipt);
		 		 	
	        Score score = new Score(); // Score object based on calculated scoreValue
	        score.setScore(points);
	        scoreService.saveScore(score);  // Save the score and return it
	        ResponseId responseId = new ResponseId(score.getId());
	        return responseId;
	    }

	 @GetMapping("/receipts/{id}/points")
	 public Response findScoreById(@PathVariable UUID id) {
		 Response response = new Response(scoreService.getScoreById(id));
		 return response;
	 }
	 
	 private static Integer calculateScore(Receipt receipt) {
		 	
		 	Integer points = 0;
		 	
		 	String retailerLen = receipt.getRetailer();
		 	
		 	int count = 0;

	        // Iterate through each character in the string
	        for (int i = 0; i < retailerLen.length(); i++) {
	            char c = retailerLen.charAt(i);
	            // Check if the character is alphanumeric
	            if (Character.isLetterOrDigit(c)) {
	                count++;
	            }
	        }
	        
		 	points+=count;
		 	points+=(receipt.getItems().size()/2)*5;
		 	
		 	Double total = receipt.getTotal();
		 	DecimalFormat df = new DecimalFormat("0.00");  // Format to 2 decimal places
		 	String totalAmount = df.format(total);
		 	String lastTwo = totalAmount.substring(totalAmount.length() - 2);
		 	
		 	if(lastTwo.equals("00")) {
		 		points+=50;
		 	}
		 	
		 	if(receipt.getTotal() % 0.25 == 0) {
		 		points+=25;
		 	}
		 	
		 	String dateString = receipt.getPurchaseDate();
		 	DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	        LocalDate date = LocalDate.parse(dateString, dayFormatter);
	        int day = date.getDayOfMonth();

	        if(day % 2 != 0) {
	        	points+=6;
	        }
	        
	        String timeString = receipt.getPurchaseTime();  // Time string with hour and minute
	        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

	        LocalTime time = LocalTime.parse(timeString, timeFormatter);
	        
	        LocalTime timeAfter = LocalTime.of(14, 00);  
	        LocalTime timeBefore = LocalTime.of(16, 00);  
	        
	        if(time.isAfter(timeAfter) && time.isBefore(timeBefore)) {
	        	points+=10;
	        }
	  
		 	List<Item> items = receipt.getItems();
		 	for (int i = 0; i < items.size(); i++) {

		 		Double price = items.get(i).getPrice();
		 		String descriptionString = items.get(i).getShortDescription().trim();
		 		if(descriptionString.length() % 3 == 0) {
		 			points += (int)Math.ceil(price*0.2);
		 		}	
			}
		 
		 return points;
	 }

}
