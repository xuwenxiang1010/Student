package com.bootdo.student.schoolpersonnel.service;

import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.student.schoolpersonnel.dto.PersonnelInformationDto;
import com.bootdo.student.schoolpersonnel.pojo.PersonnelInformation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author wyb
 * @date 2022年4月15日
 */
@Service
public interface PersonnelInformationService {
    /**
     * 分页查询
     * @return
     */
    List<PersonnelInformation> list();

    int save(PersonnelInformation personnelInformation);

    PersonnelInformation get(Integer id);

    int update(PersonnelInformation personnelInformation);

    int remove(PersonnelInformation personnelInformation);
}
