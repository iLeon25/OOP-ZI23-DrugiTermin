package hr.fer.oop.stats;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Lambdas {

    public static BiConsumer<Stream<String>, Map<String, Map<String, Double>>> loadFromStream = (lines, map) -> {
    	lines.map((string) -> string.split("\t")).forEach((array) -> 
    													map.compute(array[1], (key, value) -> {
    													if (value == null) {
    														value = new HashMap<String, Double>();
    													}
    													value.put(array[0], Double.parseDouble(array[2]));
    													return value;
    												}));
    }; 

    public static BiFunction<Map<String, Map<String, Double>>, String, Entry<String, Double>> getMinForTime = (map, time) -> 
    	map.get(time).entrySet().stream()
    								.min(new Comparator<Entry<String, Double>>() {

										@Override
										public int compare(Entry<String, Double> o1, Entry<String, Double> o2) {
											int r = 0;
											if (Double.compare(o1.getValue(), o2.getValue()) > 0) {
												r = 1;
											} else if (Double.compare(o1.getValue(), o2.getValue()) < 0) {
												r = -1;
											}
											return r;
										}
    									
									}).get();
  

    public static BiFunction<Map<String, Map<String, Double>>, String, Collection<Entry<String, Double>>> getValuesForCountry = (map, country) -> { 
    	Map<String, Double> mapa = map.get(country);
    	
    	System.out.println(map.entrySet());
    	
    
    
//    	map.entrySet().stream()
//    	.map(entry -> new SimpleEntry<>(entry.getKey(), entry.getValue().get(country))).collect(Collectors.toList());
//    	
    	
    	
    	return (mapa != null) ? map.get(country).entrySet().stream().collect(Collectors.toList()) : new LinkedList<Map.Entry<String,Double>>();
    };
    
    
}
