package com.doctorbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doctorbooking.model.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {}