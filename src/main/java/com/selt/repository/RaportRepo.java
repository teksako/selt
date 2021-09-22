package com.selt.repository;

import com.selt.model.Raport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RaportRepo extends JpaRepository<Raport, Long> {
}
