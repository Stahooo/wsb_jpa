package com.jpacourse.mapper;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.persistence.entity.PatientEntity;

import java.util.stream.Collectors;

public final class PatientMapper {
    public static PatientTO mapToTO(PatientEntity patientEntity) {
        if (patientEntity == null)
        {
            return null;
        }
        final PatientTO patientTO = new PatientTO();
        patientTO.setId(patientEntity.getId());
        patientTO.setFirstName(patientEntity.getFirstName());
        patientTO.setLastName(patientEntity.getLastName());
        patientTO.setTelephoneNumber(patientEntity.getTelephoneNumber());
        patientTO.setEmail(patientEntity.getEmail());
        patientTO.setPatientNumber(patientEntity.getPatientNumber());
        patientTO.setDateOfBirth(patientEntity.getDateOfBirth());
        patientTO.setWoman(patientEntity.isWoman());
        patientTO.setAddress(AddressMapper.mapToTO(patientEntity.getAddress()));
        patientTO.setVisits(
                patientEntity.getVisits() != null ?
                patientEntity.getVisits().stream().
                        map(VisitsMapper::mapToTO).
                        collect(Collectors.toList()) : null);
        return patientTO;
    }

    public static PatientEntity mapToEntity(PatientTO patientTO) {
        if(patientTO == null)
        {
            return null;
        }
        final PatientEntity patientEntity = new PatientEntity();
        patientEntity.setId(patientTO.getId());
        patientEntity.setFirstName(patientTO.getFirstName());
        patientEntity.setLastName(patientTO.getLastName());
        patientEntity.setTelephoneNumber(patientTO.getTelephoneNumber());
        patientEntity.setEmail(patientTO.getEmail());
        patientEntity.setPatientNumber(patientTO.getPatientNumber());
        patientEntity.setDateOfBirth(patientTO.getDateOfBirth());
        patientEntity.setWoman(patientTO.isWoman());
        patientEntity.setAddress(AddressMapper.mapToEntity(patientTO.getAddress()));
        patientEntity.setVisits(patientTO.getVisits() != null ?
                patientTO.getVisits().stream()
                        .map(VisitsMapper::mapToEntity)
                        .collect(Collectors.toList()) : null);

        return patientEntity;
    }
}
