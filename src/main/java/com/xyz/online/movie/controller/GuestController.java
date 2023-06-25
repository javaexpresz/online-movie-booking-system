package com.xyz.online.movie.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xyz.online.movie.model.Movie;
import com.xyz.online.movie.service.MovieService;

@RestController
@RequestMapping("/api/guest/v1")
public class GuestController {
	
	private MovieService movieService;
	
	public GuestController(MovieService movieService) {
		this.movieService = movieService;
	}
	
	@GetMapping
	public ResponseEntity<List<Movie>> searchMovies(@RequestParam(required = false) String title,
			@RequestParam(required = false) String location, @RequestParam(required = false) String genre,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
		List<Movie> allMovies = movieService.getAllMovies(title, location, genre, date);
		return new ResponseEntity<>(allMovies, HttpStatus.OK);
	}

}
