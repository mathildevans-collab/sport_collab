package com.sport.utilisateur_api.services.common;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sport.utilisateur_api.exceptions.BusinessException;

import io.micrometer.common.lang.NonNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstractJpaService<T, ID, R extends JpaRepository<T, ID>>  {

@Getter(AccessLevel.PROTECTED)
@NonNull
private final R repository;

public T save(T t) throws BusinessException {
        try {
            return repository.save(t);
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    public Optional<T> findById(ID id) throws BusinessException {
        try {
            return repository.findById(id);
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    public Page<T> findAll(Pageable pageable) throws BusinessException {
        try {
            return repository.findAll(pageable);
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    public Iterable<T> findAll() throws BusinessException {
        try {
            return repository.findAll();
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    public void delete(ID id) throws BusinessException {
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    public Long count() throws BusinessException {
        try {
            return repository.count();
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

}
