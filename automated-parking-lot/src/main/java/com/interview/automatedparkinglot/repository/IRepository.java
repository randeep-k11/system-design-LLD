package com.interview.automatedparkinglot.repository;

import java.sql.SQLException;
import java.util.UUID;

public interface IRepository<T> {
    void save(T entity) throws SQLException;
    T findById(UUID id) throws SQLException;
    void update(T entity) throws SQLException;
}