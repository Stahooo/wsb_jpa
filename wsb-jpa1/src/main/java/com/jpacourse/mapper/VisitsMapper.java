package com.jpacourse.mapper;

import com.jpacourse.dto.VisitTO;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;

import java.util.ArrayList;
import java.util.stream.Collectors;

public final class VisitsMapper {

    public static VisitTO mapToTO(VisitEntity visitEntity) {
        if (visitEntity == null) {
            return null;
        }

        VisitTO visitTO = new VisitTO();
        visitTO.setId(visitEntity.getId());
        visitTO.setDescription(visitEntity.getDescription());
        visitTO.setTime(visitEntity.getTime());
        visitTO.setDoctorFirstName(visitEntity.getDoctor().getFirstName());
        visitTO.setDoctorLastName(visitEntity.getDoctor().getLastName());
        visitTO.setTreatmentTypes(
                visitEntity.getTreatments() != null
                        ? visitEntity.getTreatments().stream()
                        .map(treatment -> treatment.getType().name())
                        .collect(Collectors.toList())
                        : null
        );

        return visitTO;
    }

    public static VisitEntity mapToEntity(VisitTO visitTO) {
        if (visitTO == null) {
            return null;
        }

        VisitEntity visitEntity = new VisitEntity();
        visitEntity.setId(visitTO.getId());
        visitEntity.setDescription(visitTO.getDescription());
        visitEntity.setTime(visitTO.getTime());
        visitEntity.setDoctor(new DoctorEntity());
        visitEntity.setPatient(new PatientEntity());
        visitEntity.setTreatments(new ArrayList<>());

        return visitEntity;

    }
}
