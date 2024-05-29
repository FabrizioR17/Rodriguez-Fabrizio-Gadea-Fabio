package com.backend.clinicaOdontologica.repository;

import java.util.List;

public interface IDao<T> {

    public T registrar(T t);
    public T buscarPorId(Long id);
    public List<T> listarTodos();

}
