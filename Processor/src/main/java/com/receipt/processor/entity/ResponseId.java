package com.receipt.processor.entity;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseId {
	
	private UUID id;

	public ResponseId(UUID id) {
		this.id = id;
	}
	@JsonProperty("id")
	public UUID getPoints() {
		return id;
	}

	public void setPoints(UUID id) {
		this.id = id;
	}
	
}
