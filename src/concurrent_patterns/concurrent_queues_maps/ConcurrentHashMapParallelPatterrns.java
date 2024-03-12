package concurrent_patterns.concurrent_queues_maps;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import concurrent_patterns.concurrent_queues_maps.model.Movie;
import concurrent_patterns.concurrent_queues_maps.model.MovieReader;
import concurrent_patterns.concurrent_queues_maps.model.Actor;

public class ConcurrentHashMapParallelPatterrns {

	public static void main(String[] args) {

		ConcurrentHashMap<Actor, Set<Movie>> map = new ConcurrentHashMap<>();

		MovieReader reader = new MovieReader();
		reader.addActorsToMap(map);
		
		System.out.println("# Actors = " + map.size());
		
		int maxMoviesForOneActor = map.reduce(10, (actor, movies) -> movies.size(), Integer::max);
		System.out.println("Max movies for one actor = " + maxMoviesForOneActor);
		
		Actor mostSeenActor = 
				map.search(10, (actor, movies) -> movies.size() == maxMoviesForOneActor ? actor : null);
		System.out.println("Most seen actor = " + mostSeenActor);
		
		int numberOfMoviesReferences = map.reduce(10, (actor, movies) -> movies.size(), Integer::sum);
		
		System.out.println("Average movies per actor = " + numberOfMoviesReferences/map.size());
	}
}
