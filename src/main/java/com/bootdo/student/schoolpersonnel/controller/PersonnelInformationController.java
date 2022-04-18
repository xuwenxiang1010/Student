package com.bootdo.student.schoolpersonnel.controller;

import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.student.schoolpersonnel.dto.PersonnelInformationDto;
import com.bootdo.student.schoolpersonnel.pojo.PersonnelInformation;
import com.bootdo.student.schoolpersonnel.service.PersonnelInformationService;
import com.bootdo.system.domain.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

/**
 * @author wyb
 * @date 2022年4月15日
 */
@Controller
@RequestMapping("student/schoolpersonnel")
public class PersonnelInformationController extends BaseController {

    String format = "yyyy-MM-dd'T'HH:mm:ss";
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

    @GetMapping("/add")
    public String add(){
        return prefix + "/add";
    }


    @ResponseBody()
    @PostMapping("/save")
    public R save(PersonnelInformationDto personnelInformationDto) throws ParseException {
        PersonnelInformation personnelInformation = new PersonnelInformation();
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        personnelInformation.setCreateTime(new Date());
        personnelInformation.setCreateBy(getUsername());
        personnelInformation.setInTime(sdf.parse(personnelInformationDto.getInTime()));
        personnelInformation.setOutTime(sdf.parse(personnelInformationDto.getOutTime()));
        personnelInformation.setAddress(personnelInformationDto.getAddress());
        personnelInformation.setHealthy(personnelInformationDto.getHealthy());
        personnelInformation.setName(personnelInformationDto.getName());
        personnelInformation.setNature(personnelInformationDto.getNature());
        if (personnelInformationDto !=null){
            if (personnelInformation.getOutTime().after(personnelInformation.getInTime())){
                personnelInformationService.save(personnelInformation);
                return R.ok();
            }
            return R.error("预计离校时间小于或等于入校时间");
        }
        return R.error("保存失败");
    }
}
