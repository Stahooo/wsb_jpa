package com.jpacourse.service;

import com.jpacourse.dto.PatientTO;

public interface PatientService {
     PatientTO findById(long id);
     void delete(long id);
}
