package com.example.cinemaProject.model;

import java.lang.reflect.Field;
import java.util.Comparator;

public class MovieComparator implements Comparator<Movie> {
    private String fieldName;

    public MovieComparator(String fieldName) {
        this.fieldName = fieldName;
    }

    @Override
    public int compare(Movie movie1, Movie movie2) {
        try {
            Field field = Movie.class.getDeclaredField(fieldName);
            field.setAccessible(true);
            Comparable fieldValue1 = (Comparable) field.get(movie1);
            Comparable fieldValue2 = (Comparable) field.get(movie2);
            return fieldValue1.compareTo(fieldValue2);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}

