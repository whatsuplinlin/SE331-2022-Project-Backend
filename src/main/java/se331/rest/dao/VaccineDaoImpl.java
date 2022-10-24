package se331.rest.dao;

import org.springframework.beans.factory.annotation.Autowired;
import se331.rest.entity.Vaccine;
import se331.rest.repository.VaccineRepository;

public class VaccineDaoImpl implements VaccineDao{
    @Autowired
    VaccineRepository vaccineRepository;
    @Override
    public Vaccine save(Vaccine vaccine) {
        return vaccineRepository.save(vaccine);
    }
}
