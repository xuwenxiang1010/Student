package com.bootdo.worktools.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * @author wyb
 * @date 2022年4月22日
 */

public enum PersonnelStateEnum {
    BUZ_CODE_WRX(1, "未入校"),
    BUZ_CODE_YZXN(2, "已在校内"),
    BUZ_CODE_YLX(3, "已离校"),
    ;
    private Integer code;
    private String name;

    public static String valueByCode(Integer code) {
        if(code==null){
            return "";
        }
        for (PersonnelStateEnum p : PersonnelStateEnum.values()) {
            if (p.getCode().equals(code)) {
                return p.getName();
            }
        }
        return "";
    }

    public static Integer valueByName(String name) {
        if(StringUtils.isBlank(name)){
            return 0;
        }
        for (PersonnelStateEnum p : PersonnelStateEnum.values()) {
            if (p.getName().equals(name)) {
                return p.getCode();
            }
        }
        return 0;
    }

    /**
     * @param code
     * @param name
     */

    private PersonnelStateEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
