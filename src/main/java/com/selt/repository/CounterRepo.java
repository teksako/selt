package com.selt.repository;

import com.selt.model.Counter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CounterRepo extends JpaRepository<Counter, Long> {
}
