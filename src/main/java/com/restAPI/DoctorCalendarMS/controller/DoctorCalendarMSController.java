package com.restAPI.DoctorCalendarMS.controller;

import com.restAPI.DoctorCalendarMS.pojo.dto.AppointmentResponseDTO;
import com.restAPI.DoctorCalendarMS.pojo.dto.AppointmentResponseDTO.*;
import com.restAPI.DoctorCalendarMS.pojo.dto.DoctorResponseDTO;
import com.restAPI.DoctorCalendarMS.service.AppointmentService;
import com.restAPI.DoctorCalendarMS.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1")
public class DoctorCalendarMSController {

    private final DoctorService doctorService;
    private final AppointmentService appointmentService;

    @Autowired
    public DoctorCalendarMSController(DoctorService doctorService, AppointmentService appointmentService) {
        this.doctorService = doctorService;
        this.appointmentService = appointmentService;
    }

    @GetMapping("/doctors")
    public ResponseEntity<DoctorResponseDTO> getAllDoctors() {
        return new ResponseEntity<>(doctorService.getAllDoctors(), HttpStatus.OK);
    }

    @GetMapping("/doctors/{doctorId}/appointments")
    public ResponseEntity<AppointmentResponseDTO> getAppointmentsForDoctorAndDay(
            @PathVariable Long doctorId,
            @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate date) {
        return new ResponseEntity<>(appointmentService.findByDoctorIdAndAppointmentDate(doctorId, date), HttpStatus.OK);
    }

    @PostMapping("/doctors/{doctorId}/appointments")
    public ResponseEntity<String> addAppointment(@PathVariable Long doctorId,
                                                         @RequestBody AppointmentDTO appointmentRequest) {
        // validate and set the doctorId to the DTO
        appointmentRequest.setDoctorId(doctorId);
        AppointmentDTO createdAppointment=appointmentService.createAppointment(doctorId,appointmentRequest);
        Long appointmentID=createdAppointment.getId();
        return new ResponseEntity<>("successfully created a new appointment with ID: "+appointmentID, HttpStatus.CREATED);
    }

    @DeleteMapping("/appointments/{appointmentId}")
    public ResponseEntity<String> deleteAppointment(@PathVariable Long appointmentId) {
        try {
            appointmentService.deleteAppointment(appointmentId);
            return new ResponseEntity<>("Successfully deleted the appointment",HttpStatus.ACCEPTED);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>("Appointment is not found",HttpStatus.NOT_FOUND);
        }
    }
}