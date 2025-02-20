package com.philippzeppelin.mdalib.repository;

import com.philippzeppelin.mdalib.database.entity.Availability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvailabilityRepository extends JpaRepository<Availability, Integer> {
}
