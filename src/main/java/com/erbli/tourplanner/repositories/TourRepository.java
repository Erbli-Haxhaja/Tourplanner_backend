package com.erbli.tourplanner.repositories;

import com.erbli.tourplanner.entities.Tour;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourRepository extends JpaRepository<Tour, String> {

}