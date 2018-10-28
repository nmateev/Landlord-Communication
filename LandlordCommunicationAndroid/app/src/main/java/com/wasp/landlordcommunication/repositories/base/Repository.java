package com.wasp.landlordcommunication.repositories.base;

import java.io.IOException;
import java.util.List;

public interface Repository<T> {

    T add(T item) throws IOException;

    T delete(int id) throws IOException;

    T update(T item, int id) throws IOException;

    T post(T item) throws IOException;

    T getById(int id) throws IOException;

    T getByParameter(String parameter) throws IOException;

    List<T> getAll() throws IOException;

    List<T> getAllByParameter(String parameter) throws IOException;
}
