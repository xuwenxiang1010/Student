package com.bootdo.student.schoolpersonnel.dto;

import lombok.Data;

/**
 * @author Wyb
 */
@Data
public class PersonnelInformationDto {

    private Integer id;

    private String stuId;
    /**
     * 入校时间
     */
    private String inTime;
    /**
     * 预计离校时间
     */
    private String outTime;

    private String stateName;
    private String name;
}
