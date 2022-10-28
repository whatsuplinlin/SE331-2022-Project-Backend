package se331.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se331.rest.entity.Vaccine;
import se331.rest.dao.VaccineDao;

@Service
public class VaccineServiceImpl implements VaccineService{
    @Autowired
    VaccineDao vaccineDao;
    @Override
    public Vaccine save(Vaccine vaccine) {
        return vaccineDao.save(vaccine);
    }
}
