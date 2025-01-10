package com.jpacourse.service;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.dto.VisitTO;
import com.jpacourse.persistence.dao.AddressDao;
import com.jpacourse.persistence.dao.DoctorDao;
import com.jpacourse.persistence.dao.VisitDao;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
class PatientServiceTest {
    @Autowired
    private PatientService patientService;

    @Autowired
    private VisitDao visitDao;

    @Autowired
    private DoctorDao doctorDao;

    @Autowired
    private AddressDao addressDao;

    @Transactional
    @Test
    public void testShouldDeletePatientAndHisVisits() {
        // given
        PatientTO patientBefore = patientService.findById(1L);
        assertThat(patientBefore).isNotNull();
        assertThat(visitDao.findByPatient(1L)).isNotEmpty();
        int doctorCount = doctorDao.findAll().size();

        //when
        patientService.delete(1L);

        // then
        PatientTO patientAfter = patientService.findById(1L);
        assertThat(patientAfter).isNull();
        assertThat(visitDao.findByPatient(1L)).isEmpty();
        assertThat(doctorDao.findAll().size()).isEqualTo(doctorCount);
    }

    @Transactional
    @Test
    public void testFindPatientByID_ReturnsCorrectStructure() {
        // given

        // when
        PatientTO patient = patientService.findById(1L);

        // then
        assertThat(patient).isNotNull();
        assertThat(patient.getFirstName()).isEqualTo("Piotr");
        assertThat(patient.getLastName()).isEqualTo("Zielinski");
        assertThat(patient.getTelephoneNumber()).isEqualTo("111222333");
        assertThat(patient.getEmail()).isEqualTo("piotr.zielinski@test.com");
        assertThat(patient.getPatientNumber()).isEqualTo("PAT123");
        assertThat(patient.getDateOfBirth()).isEqualTo(patient.getDateOfBirth());
        assertThat(patient.getAddress()).isEqualToComparingFieldByField(addressDao.findOne(1L));
        assertThat(patient.getVisits()).size().isEqualTo(1);
        assertThat(patient.isWoman()).isEqualTo(false);
    }

    @Test
    public void testFindAllVisitsByPatientID_ReturnsVisitList(){
        //given
        long patientId = 2L;
        //when
        List<VisitTO> result = patientService.findAllVisitsByPatientId(patientId);
        //then
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(6);
        assertThat(result.get(0)).isInstanceOf(VisitTO.class);
    }
}