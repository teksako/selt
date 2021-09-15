package com.selt.service;

import com.selt.model.Toner;
import com.selt.model.TonerMagazine;
import com.selt.repository.MagazineRepo;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
public class MagazineService {

    private final MagazineRepo magazineRepo;

    public Long getActualCount(TonerMagazine tonerMagazine){
        return
    }

    public void addNew(TonerMagazine tonerMagazine, int addCount) {
        Optional<TonerMagazine> toner1 = magazineRepo.findById(tonerMagazine.getId());
        //Long add2= Long.valueOf(addCount);
        Long add=tonerMagazine.getCount()+add2;
        toner1.get().setCount(add);
        magazineRepo.save(toner1.get());

    }

    public void save(TonerMagazine tonerMagazine) {

        magazineRepo.save(tonerMagazine);
    }

    public List<TonerMagazine> findAll() {
        return magazineRepo.findAll();
    }
}
