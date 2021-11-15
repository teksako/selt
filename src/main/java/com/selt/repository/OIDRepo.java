package com.selt.repository;

import com.selt.model.OID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OIDRepo extends JpaRepository<OID, Long> {
String findAllByOidName(String oidName);
}

