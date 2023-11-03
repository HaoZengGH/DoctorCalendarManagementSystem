package com.restAPI.DoctorCalendarMS.service.impl;

import com.restAPI.DoctorCalendarMS.pojo.dto.AppointmentResponseDTO;
import com.restAPI.DoctorCalendarMS.pojo.dto.AppointmentResponseDTO.*;
import com.restAPI.DoctorCalendarMS.pojo.entity.Appointment;
import com.restAPI.DoctorCalendarMS.pojo.entity.Doctor;
import com.restAPI.DoctorCalendarMS.repository.AppointmentRepository;
import com.restAPI.DoctorCalendarMS.repository.DoctorRepository;
import com.restAPI.DoctorCalendarMS.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;
    private DoctorRepository doctorRepository;

    public AppointmentServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public AppointmentResponseDTO findByDoctorIdAndAppointmentDate(Long doctorId, LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.plusDays(1).atStartOfDay();
        List<Appointment> appointmentsCollection=appointmentRepository.findByDoctorIdAndAppointmentDate(doctorId, startOfDay,endOfDay);
        List<AppointmentDTO> appointmentDTOs = appointmentsCollection
                .stream()
                .map(a -> new AppointmentDTO(a))
                .collect(Collectors.toList());
        return new AppointmentResponseDTO(appointmentDTOs);
    }

    public AppointmentDTO createAppointment(Long doctorId, AppointmentDTO appointmentRequest) {

        if (appointmentRequest.getAppointmentDateTime().getMinute() % 15 != 0) {
            throw new IllegalArgumentException("Appointment times must be in 15-minute intervals.");
        }

        int existingAppointments = countAppointmentsAtSameTime(doctorId, appointmentRequest.getAppointmentDateTime());
        if (existingAppointments > 3) {
            throw new IllegalArgumentException("Cannot have more than 3 appointments at the same time for a doctor.");
        }

        Appointment appointment = new Appointment();
        Doctor doctor= doctorRepository.findById(doctorId).get();
        appointment.setDoctor(doctor);
        appointment.setPatientFirstName(appointmentRequest.getPatientFirstName());
        appointment.setPatientLastName(appointmentRequest.getPatientLastName());
        appointment.setAppointmentDateTime(appointmentRequest.getAppointmentDateTime());
        appointment.setKind(appointmentRequest.getKind());

        Appointment savedAppointment = appointmentRepository.save(appointment);

        return new AppointmentDTO(savedAppointment);
    }

    @Override
    public void deleteAppointment(Long appointmentId) {
        appointmentRepository.findById(appointmentId).orElseThrow(() -> new IllegalArgumentException("Appointment not found."));
        appointmentRepository.deleteById(appointmentId);
    }

    private int countAppointmentsAtSameTime(Long doctorId, LocalDateTime dateTime) {
        LocalDateTime startOfDay = dateTime.toLocalDate().atStartOfDay();
        LocalDateTime endOfDay = dateTime.toLocalDate().plusDays(1).atStartOfDay();
        List<Appointment> existingAppointments = appointmentRepository.findByDoctorIdAndAppointmentDate(doctorId, startOfDay,endOfDay);
        return existingAppointments.size();
    }
}
