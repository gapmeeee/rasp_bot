package com.dstu.ChatBot.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<K, E> {
    boolean upadate(E e);
    List<E> findAll();
    Optional<E> findById(K k);
    boolean delete(K k);
    E save(E e);
}
