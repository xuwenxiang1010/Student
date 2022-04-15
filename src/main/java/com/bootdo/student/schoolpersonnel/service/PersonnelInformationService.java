package com.bootdo.student.schoolpersonnel.service;

import com.bootdo.common.utils.Query;
import com.bootdo.student.schoolpersonnel.pojo.PersonnelInformation;

import java.util.List;
import java.util.Map;

/**
 * @author wyb
 * @date 2022年4月15日
 */
public interface PersonnelInformationService {
    /**
     * 分页查询
     * @return
     */
    List<PersonnelInformation> list();
}
