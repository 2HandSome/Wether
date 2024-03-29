package com.complexica.weather.repository;



import com.complexica.weather.persistent.common.Identifiable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AbstractDAO<T extends Identifiable> extends JpaRepository<T, String> {

    default void delete(String id) {
        if (findById(id).isPresent()) deleteById(id);
    }
}
