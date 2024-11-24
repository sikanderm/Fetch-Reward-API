package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String shortDescription;
    private Double price;

    @ManyToOne // Foreign key in Item table
    private Receipt receipt;  // Many Items belong to one Purchase

    // Getters and Setters
    // ...   
}
