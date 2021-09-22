package com.selt.repository;

import com.selt.model.Magazine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MagazineRepo extends JpaRepository<Magazine, Long> {
}
