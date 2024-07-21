package com.example.railwayapplines.Repository;

import com.example.railwayapplines.Model.Entity.RailwayLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RailwayLineRepository extends JpaRepository<RailwayLine, Long> {
}
