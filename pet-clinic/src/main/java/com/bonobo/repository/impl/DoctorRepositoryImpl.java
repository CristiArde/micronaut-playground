package com.bonobo.repository.impl;

import com.bonobo.domain.Doctor;
import com.bonobo.repository.DoctorRepository;
import jakarta.inject.Singleton;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.Collection;

@Singleton
public class DoctorRepositoryImpl implements DoctorRepository {
    private final SqlSessionFactory sqlSessionFactory;

    public DoctorRepositoryImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    private DoctorRepository getDoctorRepository(SqlSession sqlSession) {
        return sqlSession.getMapper(DoctorRepository.class);
    }

    @Override
    public Collection<Doctor> findAll() throws Exception {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return getDoctorRepository(sqlSession).findAll();
        }
    }

    @Override
    public Doctor findById(Long id) throws Exception {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return getDoctorRepository(sqlSession).findById(id);
        }
    }

    @Override
    public Long save(Doctor vet) throws Exception {
        Long id;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            id = getDoctorRepository(sqlSession).save(vet);
            sqlSession.commit();
            return id;
        }
    }

    @Override
    public void saveVetSpecialty(Long vetId, Long specialtyId) throws Exception {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            getDoctorRepository(sqlSession).saveVetSpecialty(vetId, specialtyId);
            sqlSession.commit();
        }
    }

    @Override
    public void deleteById(Long id) throws Exception {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            getDoctorRepository(sqlSession).deleteById(id);
            sqlSession.commit();
        }
    }

    @Override
    public void deleteVetSpecialtyById(Long vetId) throws Exception {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            getDoctorRepository(sqlSession).deleteVetSpecialtyById(vetId);
            sqlSession.commit();
        }
    }

    @Override
    public void updateVetAverageRating(Long id, Double averageRating, Long ratingCount) throws Exception {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            getDoctorRepository(sqlSession).updateVetAverageRating(id, averageRating, ratingCount);
            sqlSession.commit();
        }
    }
}
