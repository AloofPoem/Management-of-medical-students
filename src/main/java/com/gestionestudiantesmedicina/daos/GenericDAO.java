package com.gestionestudiantesmedicina.daos;

import java.util.List;

public interface GenericDAO<T,K> {

    T save(T entity);

    T findById(K id);

    List<T> findAll();

    T update(T entity);

    void delete(K id);
}
