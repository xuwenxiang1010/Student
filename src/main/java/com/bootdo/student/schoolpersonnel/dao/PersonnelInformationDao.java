package com.bootdo.student.schoolpersonnel.dao;

import com.bootdo.student.schoolpersonnel.dto.PersonnelInformationDto;
import com.bootdo.student.schoolpersonnel.pojo.PersonnelInformation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author wyb
 * @date 2022年4月15日
 */
@Mapper
public interface PersonnelInformationDao{
    /**
     * 分页查询
     * @param map
     * @return
     */
    List<PersonnelInformation> list(Map<String, Object> map);

    int save(PersonnelInformation personnelInformation);

    PersonnelInformation get(Integer id);

    int update(PersonnelInformation personnelInformation);

    int remove(PersonnelInformation personnelInformation);
}
