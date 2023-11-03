package com.restAPI.DoctorCalendarMS.pojo.dto;

import com.restAPI.DoctorCalendarMS.pojo.entity.Doctor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class DoctorResponseDTO {
    private List<DoctorDTO> doctorList;

    @Data
    @AllArgsConstructor
    @Builder
    public static class DoctorDTO {
        private Long id;
        private String firstName;
        private String lastName;

        public DoctorDTO(Doctor doctor) {
            this.id = doctor.getId();
            this.firstName = doctor.getFirstName();
            this.lastName = doctor.getLastName();
        }
    }
}

