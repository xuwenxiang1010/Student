package com.bootdo.student.schoolpersonnel.pojo;
import com.bootdo.student.schoolpersonnel.controller.PersonnelInformationController;
import com.bootdo.worktools.enums.PersonnelStateEnum;
import lombok.Data;

import java.util.Date;
import java.util.List;

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
     *人员id
     */
    private String stuId;
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
     * 名称
     */
    private String name;
    /**
     * 删除（0未删除，1删除）
     */
    private int deleted;
    /**
     * 目前状态（1，未入校，2.在校内，3，已离校）
     */
    private Integer state;
    /**
     * 目前状态名称
     */
    private String stateName;

    public String getStateName() {
        return PersonnelStateEnum.valueByCode(getState());
    }

    private List<String> stuIds;
}
