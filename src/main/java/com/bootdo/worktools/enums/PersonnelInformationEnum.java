package com.bootdo.worktools.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * @author wyb
 * @date 2022年4月15日
 * 枚举类
 */

public enum PersonnelInformationEnum {
        BUZ_CODE_XS(1, "学生"),
        BUZ_CODE_JS(2, "教师"),
        BUZ_CODE_GLRY(3, "管理人员"),
        BUZ_CODE_XWRY(4, "校外人员"),
        BUZ_CODE_SR(5, "商人"),
        ;
        private Integer code;
        private String name;

        public static String valueByCode(Integer code) {
            if(code==null){
                return "";
            }
            for (PersonnelInformationEnum p : PersonnelInformationEnum.values()) {
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
            for (PersonnelInformationEnum p : PersonnelInformationEnum.values()) {
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

        private PersonnelInformationEnum(Integer code, String name) {
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

