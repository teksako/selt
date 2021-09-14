package com.selt.repository;

import com.selt.model.TonerMagazine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MagazineRepo extends JpaRepository<TonerMagazine, Long> {
}
