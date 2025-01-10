package com.jpacourse.persistence.dao.impl;

import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class PatientDaoImpl extends AbstractDao<PatientEntity, Long> implements PatientDao
{
    @Transactional
    @Override
    public VisitEntity createVisit(Long patientId, Long doctorId, LocalDateTime visitDate, String visitDescription) {
        PatientEntity patientEntity = entityManager.find(PatientEntity.class, patientId);
        DoctorEntity doctorEntity = entityManager.find(DoctorEntity.class, doctorId);
        VisitEntity visitEntity = new VisitEntity();
        visitEntity.setPatient(patientEntity);
        visitEntity.setDoctor(doctorEntity);
        visitEntity.setDescription(visitDescription);
        visitEntity.setTime(visitDate);

        patientEntity.getVisits().add(visitEntity);
        entityManager.persist(visitEntity);
        entityManager.merge(patientEntity);

        return visitEntity;
    }

    @Override
    public List<PatientEntity> findAllPatientsBySurname(String lastName) {
        return entityManager.createNamedQuery("PatientEntity.findByLastName", PatientEntity.class)
                .setParameter("lastName", lastName).getResultList();
    }

    @Override
    public List<PatientEntity> findAllPatientsByVisitCountGreaterThan(long visitCount) {
        return entityManager.createQuery("SELECT p FROM PatientEntity p JOIN p.visits v GROUP BY p HAVING COUNT(v) > :visitCount", PatientEntity.class)
                .setParameter("visitCount", visitCount).getResultList();
    }

    @Override
    public List<PatientEntity> findAllPatientsByBirthdateLaterThan(LocalDate birthdate) {
        return entityManager.createQuery("SELECT p FROM PatientEntity p WHERE p.dateOfBirth > :birthdate", PatientEntity.class)
                .setParameter("birthdate", birthdate).getResultList();
    }
}
