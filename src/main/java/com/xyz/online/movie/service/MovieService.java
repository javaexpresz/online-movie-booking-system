package com.xyz.online.movie.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xyz.online.movie.model.Movie;
import com.xyz.online.movie.repository.MovieRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MovieService {
	
	private final Logger Logger=LoggerFactory.getLogger(MovieService.class);
	
	@Autowired
	private MovieRepository movieRepository;
	
	public String addMovie(Movie movie) {
		this.Logger.info("inside addMovie() with input : "+movie.toString());
		Movie movieResult = movieRepository.save(movie);
		this.Logger.info("Successfully save movie for ID : "+movieResult.getId());
		return "Saved movie for movie id "+movieResult.getId();
	}
	
	public Movie getMovieById(Integer id) {
		this.Logger.info("inside addMovie() with input : {}",id);
		return movieRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid movie ID : "+id));
	}
	
	public List<Movie> getAllMovies(String title, String location, String genre,LocalDate date){
		if(title ==null && location == null && genre == null && date ==null) {
			return movieRepository.findAll();
		}else {
			return filterMovie(title, location, genre,date);
		}
		

	}
	
	public String deleteMovie(Integer id) {
		Movie movie = movieRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid movie ID : "+id));
		movieRepository.delete(movie);
		return "movie removed for ID : "+movie.getId();
	}
	
	public Movie updateMovie(Integer id, Movie movie) {
		this.Logger.info("inside updateMovie() with id : {} input data movie : {}",id,movie.toString());
		Movie movieRes = movieRepository.findById(id).get();
		this.Logger.info("fetching movie with with id : {} movie : {}",id,movieRes.toString());
		BeanUtils.copyProperties(movie, movieRes);
		movieRes.setId(id);
		Movie savedMovie = movieRepository.save(movieRes);
		this.Logger.info("updated movie data : {}",savedMovie.toString());
		return savedMovie;
	}
	
	public List<Movie> filterMovie(String title, String location, String genre,LocalDate date){
		List<Movie> movies = movieRepository.findAll();
		List<Movie> filteredMovies=new ArrayList<>();
		for(Movie movie : movies) {
			boolean match=true;
			
			if(title!=null && !movie.getTitle().toLowerCase().contains(title.toLowerCase())) {
				match=false;
			}
			
			if(location!=null && !movie.getLocation().toLowerCase().contains(location.toLowerCase())) {
				match=false;
			}
			
			if(genre!=null && !movie.getGenre().toLowerCase().contains(genre.toLowerCase())) {
				match=false;
			}
			
			if(date !=null && !movie.getDate().isEqual(date)) {
				match=false;
			}
			
			if(match) {
				filteredMovies.add(movie);
			}
		}
		return filteredMovies;
	}

}
