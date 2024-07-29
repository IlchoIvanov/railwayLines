package com.example.railwayapplines.repository;

import com.example.railwayapplines.model.entity.RailwayLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RailwayLineRepository extends JpaRepository<RailwayLine, Long> {
}
