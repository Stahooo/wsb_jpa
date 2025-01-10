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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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

    @Test
    void testFindAllPatientsBySurname_ReturnsPatientList(){
        //given
        String lastName = "Kowalski";
        //when
        List<PatientEntity> result = patientDao.findAllPatientsBySurname(lastName);
        //then
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(5);
        assertThat(result.get(0).getLastName()).isEqualTo(lastName);
    }

    @Test
    void testFindAllPatientsByVisitCountGreaterThan_ReturnsVisitList(){
        //given
        long visitCount = 3;
        //when
        List<PatientEntity> result = patientDao.findAllPatientsByVisitCountGreaterThan(visitCount);
        //then
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0)).isInstanceOf(PatientEntity.class);
    }

    @Test
    void testFindAllPatientsByVisitCountGreaterThan_ReturnsEmptyList(){
        //given
        long visitCount = 100000;
        //when
        List<PatientEntity> result = patientDao.findAllPatientsByVisitCountGreaterThan(visitCount);
        //then
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(0);
    }

    @Test
    void testFindAllPatientsByBirthdateLaterThan_ReturnsPatientList(){
        //given
        LocalDate testBirthDate = LocalDate.parse("1999-08-22");
        // when
        List<PatientEntity> result = patientDao.findAllPatientsByBirthdateLaterThan(testBirthDate);
        // then
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(3);
    }

    @Test
    void testFindAllPatientsByBirthdateLaterThan_ReturnsEmptyList(){
        //given
        LocalDate testBirthDate = LocalDate.parse("9999-08-22");
        // when
        List<PatientEntity> result = patientDao.findAllPatientsByBirthdateLaterThan(testBirthDate);
        // then
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(0);
    }
}