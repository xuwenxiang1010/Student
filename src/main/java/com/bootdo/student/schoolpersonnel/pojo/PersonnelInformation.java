package com.bootdo.student.schoolpersonnel.pojo;

import com.bootdo.worktools.enums.PersonnelInformationEnum;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author wyb
 * @date 2022年4月14日
 */
@Data
public class PersonnelInformation {
    /**
     * 主键ID
     */
    private Integer id;

    /**学校人员信息登记
     *姓名
     */
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
    private Date inTime;
    /**
     * 预计离校时间
     */
    private Date outTime;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 编辑人
     */
    private String updateBy;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 编辑时间
     */
    private Date updateTime;
    /**
     * 身份性质名称
     */
    private String natureName;
    /**
     * 删除（0未删除，1删除）
     */
    private int deleted;

    public String getNatureName() {
        return PersonnelInformationEnum.valueByCode(getNature());
    }
}
