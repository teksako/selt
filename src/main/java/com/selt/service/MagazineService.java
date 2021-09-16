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
        Optional<TonerMagazine> actualCounter = magazineRepo.findById(tonerMagazine.getId());
        return actualCounter.get().getCount();

    }



    public void updateInventory(TonerMagazine tonerMagazine, Long temp) {
        Optional<TonerMagazine> toner1 = magazineRepo.findById(tonerMagazine.getId());
        Long add=tonerMagazine.getCount();
        add=add+temp;
        toner1.get().setCount(add);
        magazineRepo.save(toner1.get());

    }

    public void removeInventory(TonerMagazine tonerMagazine, Long temp) {
        Optional<TonerMagazine> toner1 = magazineRepo.findById(tonerMagazine.getId());
        Long add=tonerMagazine.getCount();
        add=temp-add;
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
