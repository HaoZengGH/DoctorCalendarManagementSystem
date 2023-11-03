package com.restAPI.DoctorCalendarMS.pojo.dto;

import com.restAPI.DoctorCalendarMS.pojo.entity.Appointment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class AppointmentResponseDTO {
    private List<AppointmentDTO> appointmentList;

    @Data
    @AllArgsConstructor
    @Builder
    public static class AppointmentDTO {
        private Long id;
        private Long doctorId;
        private String patientFirstName;
        private String patientLastName;
        private LocalDateTime appointmentDateTime;
        private String kind;

        public AppointmentDTO(Appointment appointment) {
            this.id = appointment.getId();
            this.doctorId = appointment.getDoctor().getId();
            this.patientFirstName = appointment.getPatientFirstName();
            this.patientLastName = appointment.getPatientLastName();
            this.appointmentDateTime = appointment.getAppointmentDateTime();
            this.kind = appointment.getKind();
        }
    }
}

