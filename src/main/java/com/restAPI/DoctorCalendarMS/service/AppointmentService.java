package com.restAPI.DoctorCalendarMS.service;

import com.restAPI.DoctorCalendarMS.pojo.dto.AppointmentResponseDTO.*;
import com.restAPI.DoctorCalendarMS.pojo.dto.AppointmentResponseDTO;
import com.restAPI.DoctorCalendarMS.pojo.entity.Appointment;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public interface AppointmentService {
    AppointmentResponseDTO findByDoctorIdAndAppointmentDate(Long doctorId, LocalDate date);
    AppointmentDTO createAppointment(Long doctorId, AppointmentDTO appointment);
    void deleteAppointment(Long appointmentId);
}

