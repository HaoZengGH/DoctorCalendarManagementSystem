package com.restAPI.DoctorCalendarMS.service.impl;

import com.restAPI.DoctorCalendarMS.pojo.dto.DoctorResponseDTO;
import com.restAPI.DoctorCalendarMS.pojo.dto.DoctorResponseDTO.*;
import com.restAPI.DoctorCalendarMS.pojo.entity.Doctor;
import com.restAPI.DoctorCalendarMS.repository.DoctorRepository;
import com.restAPI.DoctorCalendarMS.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public DoctorResponseDTO getAllDoctors() {
        List<Doctor> doctorCollection = doctorRepository.findAll();;
        List<DoctorDTO> doctorDTOS = doctorCollection
                .stream()
                .map(d -> new DoctorDTO(d))
                .collect(Collectors.toList());
        return new DoctorResponseDTO(doctorDTOS);
    }

    @Override
    public Doctor getDoctorById(Long id) {
        return doctorRepository.findById(id).orElse(null);  // return null if not found
    }
}

