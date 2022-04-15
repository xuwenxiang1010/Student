package com.bootdo.student.schoolpersonnel.service.impl;

import com.bootdo.common.utils.Query;
import com.bootdo.student.schoolpersonnel.dao.PersonnelInformationDao;
import com.bootdo.student.schoolpersonnel.pojo.PersonnelInformation;
import com.bootdo.student.schoolpersonnel.service.PersonnelInformationService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wyb
 * @date 2022年4月15日
 */
@Service
public class PersonnelInformationServiceImpl implements PersonnelInformationService {

    private PersonnelInformationDao personnelInformationDao;
    @Override
    public List<PersonnelInformation> list() {
        return personnelInformationDao.list(new HashMap<>(50));
    }
}
