package com.xyz.online.movie.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "movie")
public class Movie {
	@Id
	@SequenceGenerator(name = "movie_id_sequence", sequenceName = "movie_id_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movie_id_sequence")
	private int id;
	private String title;
	private String director;
	private String description;
	private String genre;
	private LocalDate date;
	private String location;
	private int totalSeats;
	private int availableSeats;
	private int price;
}
