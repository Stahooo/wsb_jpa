package com.jpacourse.persistence.dao;

import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class PatientDaoTest {
    @Autowired
    private PatientDao patientDao;

    @Autowired
    private DoctorDao doctorDao;

    @Test
    void createVisit() {
        //given
        LocalDateTime visitDate = LocalDateTime.now();
        DoctorEntity doctor = doctorDao.findOne(1L);
        PatientEntity patient = patientDao.findOne(1L);
        assertThat(patient).isNotNull();
        assertThat(doctor).isNotNull();
        int patientVisitsCount = patient.getVisits().size();

        //when
        VisitEntity result = patientDao.createVisit(1L, 1L, visitDate, "some description");

        //then
        assertThat(result).isNotNull();
        assertThat(patient.getVisits().size()).isEqualTo(patientVisitsCount + 1);
        assertThat(result.getPatient().getId()).isEqualTo(patient.getId());
        assertThat(result.getDoctor().getId()).isEqualTo(doctor.getId());
        assertThat(result.getDescription()).isEqualTo("some description");
        assertThat(result.getTime()).isEqualTo(visitDate);
    }
}