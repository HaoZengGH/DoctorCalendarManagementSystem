package com.restAPI.DoctorCalendarMS.repository;

import com.restAPI.DoctorCalendarMS.pojo.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}

