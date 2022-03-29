package io.shubh.moviecatalogservice.resources;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.shubh.moviecatalogservice.models.CatalogItem;
import io.shubh.moviecatalogservice.models.Movie;
import io.shubh.moviecatalogservice.models.Rating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	
	@Autowired
	public RestTemplate restTemplate;

	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
		// get all rated movie IDs

		// for each movie ID call movie info service and get details

		List<Rating> ratings = Arrays.asList(new Rating("1234", 4), new Rating("4567", 6));

		return ratings.stream().map(rating -> {
			Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(), Movie.class);
			return new CatalogItem(movie.getName(), "Test", rating.getRating());
		}).collect(Collectors.toList());

//		return Collections.singletonList(new CatalogItem("SpiderMan", "Marvel Movie", 3));
	}

}
