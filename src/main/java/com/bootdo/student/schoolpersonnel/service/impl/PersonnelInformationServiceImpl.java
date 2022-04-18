package com.bootdo.student.schoolpersonnel.service.impl;

import com.bootdo.common.utils.R;
import com.bootdo.student.schoolpersonnel.dao.PersonnelInformationDao;
import com.bootdo.student.schoolpersonnel.pojo.PersonnelInformation;
import com.bootdo.student.schoolpersonnel.service.PersonnelInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @author wyb
 * @date 2022年4月15日
 */
@Service
public class PersonnelInformationServiceImpl implements PersonnelInformationService {

    @Autowired
    private PersonnelInformationDao personnelInformationDao;


    @Override
    public List<PersonnelInformation> list() {
        List<PersonnelInformation> per = personnelInformationDao.list(new HashMap<>(50));
        return per;
    }

    @Override
    public int save(PersonnelInformation personnelInformation) {
        int p = personnelInformationDao.save(personnelInformation);
        return p;
    }
}
