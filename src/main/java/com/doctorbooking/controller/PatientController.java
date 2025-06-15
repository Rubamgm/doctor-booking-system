package com.doctorbooking.controller;

import com.doctorbooking.model.Patient;
import com.doctorbooking.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @GetMapping
    public String viewPatients(Model model) {
        model.addAttribute("patients", patientRepository.findAll());
        model.addAttribute("patient", new Patient());
        return "patient_list";
    }

    @PostMapping("/add")
    public String savePatient(@ModelAttribute("patient") Patient patient) {
        patientRepository.save(patient);
        return "redirect:/patients";
    }

    @GetMapping("/edit/{id}")
    public String editPatient(@PathVariable("id") Long id, Model model) {
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid patient ID: " + id));
        model.addAttribute("patient", patient);
        model.addAttribute("patients", patientRepository.findAll());
        return "patient_list";
    }

    @GetMapping("/delete/{id}")
    public String deletePatient(@PathVariable("id") Long id) {
        patientRepository.deleteById(id);
        return "redirect:/patients";
    }
}
