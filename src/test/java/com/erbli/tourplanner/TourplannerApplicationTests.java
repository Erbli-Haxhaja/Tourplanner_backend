package com.erbli.tourplanner;

import com.erbli.tourplanner.dtos.TourDto;
import com.erbli.tourplanner.entities.Tour;
import com.erbli.tourplanner.mapper.TourDtoMapper;
import com.erbli.tourplanner.repositories.TourRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TourplannerApplicationTests {
	@Autowired
	TourRepository tourRepository;
	@Autowired
	TourDtoMapper tourDtoMapper;

	@Test
	void test_Tour_RowCount(){
		System.out.println("There are " + tourRepository.count() + " tours!");
	}

	@Test
	void test_Tour_creation(){
		Tour tour = Tour.builder()
				.name("This is a tour")
				.tourDescription("Description")
				.fromm("From")
				.too("To")
				.transportType("Train")
				.tourDistance("2km")
				.estimatedTime("2h")
				.build();
		System.out.println("There were " + tourRepository.count() + " tours!");
		tourRepository.save(tour);
		System.out.println("Now there are " + tourRepository.count() + " tours!");
	}

	@Test
	public void test_Tour_to_TourDto_mapper(){
		Tour tour = Tour.builder()
				.name("This is a tour")
				.tourDescription("Description")
				.fromm("From")
				.too("To")
				.transportType("Train")
				.tourDistance("2km")
				.estimatedTime("2h")
				.build();

		TourDto tourDto = tourDtoMapper.mapTourToTourDto(tour);

		assertEquals(tour.getName(), tourDto.getName());
		assertEquals(tour.getTourDescription(), tourDto.getTourDescription());
	}

	@Test
	public void test_TourDto_to_Tour_mapper(){

		TourDto tourDto = TourDto.builder()
				.id(1)
				.name("This is a tour")
				.tourDescription("Description")
				.fromm("From")
				.too("To")
				.transportType("Train")
				.tourDistance("2km")
				.estimatedTime("2h")
				.routeInformation("picture")
				.build();
		Tour tour = tourDtoMapper.mapTourDtoToTour(tourDto);

		assertEquals(tour.getName(), tourDto.getName());
		assertEquals(tour.getTourDescription(), tourDto.getTourDescription());
	}

	@Test
	public void test_getAllTours(){
		List<Tour> tours = tourRepository.findAll();
		for(Tour tour : tours) {
			System.out.println("--- Tour: " + tour.getName() + " ---");
		}
	}



}
