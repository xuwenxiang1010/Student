package com.bootdo.student.schoolpersonnel.dto;

import lombok.Data;

@Data
public class PersonnelInformationDto {
    private String name;
    /**
     * 身份性质（1：学生，2：老师，3：管理人员，4：校外人员，5：商人）
     */
    private Integer nature;
    /**
     *健康情况
     */
    private String healthy;
    /**
     * 住址
     */
    private String address;
    /**
     * 入校时间
     */
    private String inTime;
    /**
     * 预计离校时间
     */
    private String outTime;
}
