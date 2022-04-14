package com.bootdo.work.schoolpersonnel.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author WuYubang
 * @date 2022年4月14日
 */
@Data
@TableName(value = "sch_personnel_information")
public class PersonnelInformation {
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
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
    @TableField(exist = false)
    private String natureName;


}
