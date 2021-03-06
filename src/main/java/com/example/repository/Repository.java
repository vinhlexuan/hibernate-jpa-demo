package com.example.repository;

import java.util.List;

public interface Repository<T> {
    List<T> findAll();
    T findbyID(Long id);
    void save(T model);
    void remove(Long id);
}
