package com.bootdo.student.schoolpersonnel.controller;

import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.student.schoolpersonnel.pojo.PersonnelInformation;
import com.bootdo.student.schoolpersonnel.service.PersonnelInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author wyb
 * @date 2022年4月15日
 */
@Controller
@RequestMapping("student/schoolpersonnel")
public class PersonnelInformationController extends BaseController {

    String prefix = "student/schoolpersonnel";
    @Autowired
    private PersonnelInformationService personnelInformationService;

    @GetMapping()
    String per(){
        return prefix + "/per";
    }

   @ResponseBody()
    @GetMapping("/list")
    public List<PersonnelInformation> list(){
       List<PersonnelInformation> list = personnelInformationService.list();
       return list;
    }
}
