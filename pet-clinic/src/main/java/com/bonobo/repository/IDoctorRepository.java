package com.bonobo.repository;

import com.bonobo.domain.Doctor;
import org.apache.ibatis.annotations.*;

import java.util.Collection;

public interface IDoctorRepository {
    @Select("SELECT * FROM petclinic.doctors")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "firstName", column = "first_name"),
            @Result(property = "lastName", column = "last_name"),
            @Result(property = "averageRating", column = "avg_rating"),
            @Result(property = "ratingCount", column = "rating_count"),
    })
    Collection<Doctor> findAll() throws Exception;

    @Select("SELECT * FROM petclinic.doctors WHERE id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "firstName", column = "first_name"),
            @Result(property = "lastName", column = "last_name"),
            @Result(property = "averageRating", column = "avg_rating"),
            @Result(property = "ratingCount", column = "rating_count"),
    })
    Doctor findById(@Param("id") Long id) throws Exception;

    @Select(
            {
                    "INSERT INTO petclinic.doctors(id, first_name, last_name)\n" +
                            "VALUES (COALESCE(#{id}, (select nextval('petclinic.vets_id_seq'))), #{firstName}, #{lastName})\n" +
                            "ON CONFLICT (id)\n" +
                            "DO UPDATE SET (first_name, last_name) = (#{firstName}, #{lastName})\n" +
                            "WHERE petclinic.doctors.id = #{id}\n" +
                            "RETURNING id"
            }
    )
    @Options(flushCache = Options.FlushCachePolicy.TRUE)
    Long save(Doctor vet) throws Exception;

    @Select(
            {
                    "INSERT INTO petclinic.doc_specialties(vet_id, specialty_id)\n" +
                            "VALUES (#{vetId}, #{specialtyId})\n" +
                            "ON CONFLICT (vet_id, specialty_id)\n" +
                            "DO NOTHING\n"
            }
    )
    void saveVetSpecialty(@Param("vetId") Long vetId, @Param("specialtyId") Long specialtyId) throws Exception;

    @Delete("DELETE FROM petclinic.doctors WHERE id = #{id}")
    void deleteById(@Param("id") Long id) throws Exception;

    @Delete("DELETE FROM petclinic.doc_specialties WHERE vet_id = #{vetId}")
    void deleteVetSpecialtyById(@Param("vetId") Long vetId) throws Exception;

    @Update("UPDATE petclinic.doctors SET avg_rating = #{averageRating}, rating_count = #{ratingCount} WHERE id = #{id}")
    void updateVetAverageRating(@Param("id") Long id, @Param("averageRating") Double averageRating,
                                @Param("ratingCount") Long ratingCount) throws Exception;
}


