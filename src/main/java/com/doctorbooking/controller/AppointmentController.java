package com.doctorbooking.controller;

import com.doctorbooking.model.Appointment;
import com.doctorbooking.model.Doctor;
import com.doctorbooking.model.Patient;
import com.doctorbooking.repository.AppointmentRepository;
import com.doctorbooking.repository.DoctorRepository;
import com.doctorbooking.repository.PatientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    // ✅ View all appointments
    @GetMapping
    public String viewAppointments(Model model) {
        model.addAttribute("appointments", appointmentRepository.findAll());
        model.addAttribute("appointment", new Appointment());
        model.addAttribute("doctors", doctorRepository.findAll());
        model.addAttribute("patients", patientRepository.findAll());
        return "appointment_list";
    }

    // ✅ Add or update appointment
    @PostMapping("/add")
    public String saveAppointment(@ModelAttribute("appointment") Appointment appointment) {
        appointmentRepository.save(appointment);
        return "redirect:/appointments";
    }

    // ✅ Edit appointment
    @GetMapping("/edit/{id}")
    public String editAppointment(@PathVariable Long id, Model model) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid appointment ID: " + id));
        model.addAttribute("appointment", appointment);
        model.addAttribute("appointments", appointmentRepository.findAll());
        model.addAttribute("doctors", doctorRepository.findAll());
        model.addAttribute("patients", patientRepository.findAll());
        return "appointment_list";
    }

    // ✅ Delete appointment
    @GetMapping("/delete/{id}")
    public String deleteAppointment(@PathVariable Long id) {
        appointmentRepository.deleteById(id);
        return "redirect:/appointments";
    }
}
