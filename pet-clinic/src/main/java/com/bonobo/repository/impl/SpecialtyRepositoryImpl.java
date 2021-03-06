package com.bonobo.repository.impl;

import com.bonobo.domain.Specialty;
import com.bonobo.repository.SpecialtyRepository;
import jakarta.inject.Singleton;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.Collection;
import java.util.Set;

@Singleton
public class SpecialtyRepositoryImpl implements SpecialtyRepository {
    private final SqlSessionFactory sqlSessionFactory;

    public SpecialtyRepositoryImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    private SpecialtyRepository getSpecialtyRepository(SqlSession sqlSession) {
        return sqlSession.getMapper(SpecialtyRepository.class);
    }

    @Override
    public Collection<Specialty> findAll() throws Exception {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return getSpecialtyRepository(sqlSession).findAll();
        }
    }

    @Override
    public Specialty findById(Long id) throws Exception {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return getSpecialtyRepository(sqlSession).findById(id);
        }
    }

    @Override
    public Specialty findByName(String name) throws Exception {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return getSpecialtyRepository(sqlSession).findByName(name);
        }
    }

    @Override
    public Long save(Specialty specialty) throws Exception {
        Long specialtyId;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            specialtyId = getSpecialtyRepository(sqlSession).save(specialty);
            sqlSession.commit();
        }
        return specialtyId;
    }

    @Override
    public void deleteById(Long id) throws Exception {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            getSpecialtyRepository(sqlSession).deleteById(id);
            sqlSession.commit();
        }
    }

    @Override
    public Set<Specialty> findByVetId(Long vetId) throws Exception {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return getSpecialtyRepository(sqlSession).findByVetId(vetId);
        }
    }
}
