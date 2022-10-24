package se331.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se331.rest.entity.People;
import se331.rest.dao.PeopleDao;


@Service
public class PeopleServiceImpl implements PeopleService{
    @Autowired
    PeopleDao peopleDao;
    @Override
    @Transactional
    public People save(People people) {
        return peopleDao.save(people);
    }

    @Override
    public Page<People> getPeople(Integer perPage, Integer page) {
        return peopleDao.getPeople(perPage, page);
    }

    @Override
    public Page<People> getPeople(String name, Pageable pageable) {
        return peopleDao.getPeople(name,pageable);
    }

    @Override
    public People getPeople(Long id) {
        return peopleDao.getPeople(id);
    }

}
