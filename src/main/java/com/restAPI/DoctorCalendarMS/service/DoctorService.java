package com.restAPI.DoctorCalendarMS.service;

import com.restAPI.DoctorCalendarMS.pojo.dto.DoctorResponseDTO;
import com.restAPI.DoctorCalendarMS.pojo.entity.Doctor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DoctorService {
    DoctorResponseDTO getAllDoctors();
    Doctor getDoctorById(Long id);
}

