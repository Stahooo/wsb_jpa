package com.jpacourse.service;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.dto.VisitTO;

import java.util.List;

public interface PatientService {
     PatientTO findById(long id);
     void delete(long id);
     List<VisitTO>  findAllVisitsByPatientId(long patientId);
}
