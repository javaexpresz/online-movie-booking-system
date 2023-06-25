package com.xyz.online.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xyz.online.movie.model.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer>{

}
