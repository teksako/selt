package com.selt.service;

import com.selt.model.Toner;
import com.selt.model.TonerMagazine;
import com.selt.repository.MagazineRepo;
import com.selt.repository.TonerRepo;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
public class TonerService {

    private final TonerRepo tonerRepo;
    private final MagazineService magazineService;

    public void save(Toner toner) {
        TonerMagazine magazine = new TonerMagazine();
        tonerRepo.save(toner);
        magazine.setCount(0l);
        magazine.setToner(toner);
        magazineService.save(magazine);
    }

    public void delete(Toner toner) {
        tonerRepo.delete(toner);

    }

    public void update(Toner toner) {
        Optional<Toner> toner1 = tonerRepo.findById(toner.getId());
        toner1.get().setTonerName(toner.getTonerName());
        tonerRepo.save(toner1.get());

    }

    public List<Toner> findAll() {
        return tonerRepo.findAll();
    }


}
