package com.selt.service;

import com.selt.model.Toner;
import com.selt.model.TonerMagazine;
import com.selt.repository.MagazineRepo;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Data
public class MagazineService {

    private final MagazineRepo magazineRepo;

    public void addNew(TonerMagazine tonerMagazine) {
        Optional<TonerMagazine> toner1 = magazineRepo.findById(tonerMagazine.getId());
//        Long add=tonerMagazine.getCount();
//        add=add+4l;
        toner1.get().setCount(tonerMagazine.getCount());
        magazineRepo.save(toner1.get());

//        public void update(Toner toner) {
//            Optional<Toner> toner1 = tonerRepo.findById(toner.getId());
//            toner1.get().setTonerName(toner.getTonerName());
//            tonerRepo.save(toner1.get());
//
//        }
    }

    public void save(TonerMagazine tonerMagazine) {

        magazineRepo.save(tonerMagazine);
    }

}
