package com.epam.dao;

import com.epam.exception.MovieException;

/**
 * Created by Olga Bogutska on 26.02.2016.
 */
public interface Dao<T> {
    void create(T event);

    void remove(T event) throws MovieException;

    T getByName(String name) throws MovieException;
}
