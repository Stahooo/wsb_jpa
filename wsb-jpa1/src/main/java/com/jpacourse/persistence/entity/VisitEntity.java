package com.jpacourse.persistence.entity;

import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
@Table(name = "VISIT")
public class VisitEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String description;

	@Column(nullable = false)
	private LocalDateTime time;

	@ManyToOne // Jednostronna od Doctor
	@JoinColumn(name = "doctor_id", nullable = false)
	private DoctorEntity doctor;

	@ManyToOne // Jednostronna od Patient
	@JoinColumn(name = "patient_id", nullable = false)
	private PatientEntity patient;

	@ManyToOne // Jednostronna od MedicalTreatment
	@JoinColumn(name = "treatment_id", nullable = false)
	private MedicalTreatmentEntity treatment;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

}
