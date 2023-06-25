package com.xyz.online.movie.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xyz.online.movie.model.Movie;
import com.xyz.online.movie.service.MovieService;

@RestController
@RequestMapping("/api/movie/v1")
public class MovieController {

	private MovieService movieService;

	public MovieController(MovieService movieService) {
		this.movieService = movieService;
	}

	@PostMapping("/addMovie")
	public ResponseEntity<String> addMovie(@RequestBody Movie movie) {
		String movieResult = movieService.addMovie(movie);
		return new ResponseEntity<>(movieResult, HttpStatus.CREATED);

	}

	@GetMapping("/{id}")
	public ResponseEntity<Movie> getMovieById(@PathVariable("id") Integer id) {
		Movie movieById = movieService.getMovieById(id);
		return new ResponseEntity<>(movieById, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<Movie>> getMovies(@RequestParam(required = false) String title,
			@RequestParam(required = false) String location, @RequestParam(required = false) String genre,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
		List<Movie> allMovies = movieService.getAllMovies(title, location, genre, date);
		return new ResponseEntity<>(allMovies, HttpStatus.OK);
	}

	@DeleteMapping("/{movieId}")
	public ResponseEntity<String> deleteMovie(@PathVariable("movieId") Integer id) {
		String result = movieService.deleteMovie(id);
		return new ResponseEntity<>(result, HttpStatus.OK);

	}

	@PutMapping("/movie/update/{movieId}")
	public ResponseEntity<Movie> updateMovie(@PathVariable("movieId") Integer id, @RequestBody Movie movie) {
		Movie updateMovie = movieService.updateMovie(id, movie);
		return new ResponseEntity<>(updateMovie, HttpStatus.OK);
	}


}
