package com.bootdo.student.schoolpersonnel.controller;

import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.R;
import com.bootdo.student.schoolpersonnel.dto.PersonnelInformationDto;
import com.bootdo.student.schoolpersonnel.pojo.PersonnelInformation;
import com.bootdo.student.schoolpersonnel.service.PersonnelInformationService;
import com.bootdo.student.student.pojo.entity.StuDO;
import com.bootdo.student.student.service.StuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
    @Autowired
    private StuService stuService;

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
    public String add(Model model){
        List<StuDO> stuId = stuService.nameList(new HashMap<>(50));

        model.addAttribute("peo",stuId);
        return prefix + "/add";
    }


    @ResponseBody()
    @PostMapping("/save")
    public R save(PersonnelInformationDto personnelInformationDto) throws ParseException {
        if (personnelInformationDto !=null){
            PersonnelInformation personnelInformation = new PersonnelInformation();
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            personnelInformation.setCreateTime(new Date());
            personnelInformation.setCreateBy(getUsername());
            BeanUtils.copyProperties(personnelInformationDto,personnelInformation);
            personnelInformation.setInTime(sdf.parse(personnelInformationDto.getInTime()));
            personnelInformation.setOutTime(sdf.parse(personnelInformationDto.getOutTime()));
            String stuId = personnelInformationDto.getStuId();
            personnelInformation.setStuId(stuId);
            int days = dayNumber(personnelInformationDto.getInTime(),personnelInformationDto.getOutTime());
            if (days >= 1){
                personnelInformation.setState(timeCompare(personnelInformation.getInTime(),personnelInformation.getOutTime()));
                personnelInformationService.save(personnelInformation);
                return R.ok();
            }
            return R.error("预计离校时间需大于入校时间至少1天");
        }
        return R.error("保存失败");
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model){
        PersonnelInformation personnelInformation = personnelInformationService.get(id);
        PersonnelInformationDto personnelInformationDto = new PersonnelInformationDto();
        BeanUtils.copyProperties(personnelInformation,personnelInformationDto);
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        personnelInformationDto.setInTime(sdf.format(personnelInformation.getInTime()));
        personnelInformationDto.setOutTime(sdf.format(personnelInformation.getOutTime()));
        model.addAttribute("per",personnelInformationDto);
        List<StuDO> name = stuService.nameList(new HashMap<>(50));
        model.addAttribute("peo",name);
        return prefix + "/edit";
    }

    @PostMapping("/update")
    @ResponseBody()
    public R update(PersonnelInformationDto personnelInformationDto) throws ParseException {
        if (personnelInformationDto != null){
            PersonnelInformation personnelInformation = new PersonnelInformation();
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            BeanUtils.copyProperties(personnelInformationDto,personnelInformation);
            personnelInformation.setUpdateBy(getUsername());
            personnelInformation.setUpdateTime(new Date());
            personnelInformation.setInTime(sdf.parse(personnelInformationDto.getInTime()));
            personnelInformation.setOutTime(sdf.parse(personnelInformationDto.getOutTime()));
            personnelInformation.setStuId(personnelInformationDto.getStuId());
            int days = dayNumber(personnelInformationDto.getInTime(),personnelInformationDto.getOutTime());
            if (days >=1){
                personnelInformation.setState(timeCompare(personnelInformation.getInTime(),personnelInformation.getOutTime()));
                personnelInformationService.update(personnelInformation);
                return R.ok();
            }
            return R.error("预计离校时间需大于入校时间至少1天");
        }
        return R.error("编辑失败");
    }

    @PostMapping("/remove")
    @ResponseBody()
    public R remove(Integer id){
        PersonnelInformation personnelInformation = personnelInformationService.get(id);
        if (personnelInformation!=null){
            personnelInformation.setDeleted(1);
            personnelInformationService.remove(personnelInformation);
            return R.ok();
        }
        return R.error("删除失败");
    }

    /**
     * 时间比较
     * @param in
     * @param out
     * @return
     */
    public int timeCompare(Date in,Date out){
        int n;
        Date now = new Date();
        if (in.after(now)){
            n=1;
        }else if (now.after(in) && out.after(now)){
            n=2;
        }else {
            n=3;
        }
        return n;
    }

    /**
     * 天数获取
     * @param in
     * @param out
     * @return
     * @throws ParseException
     */
    public int dayNumber(String in,String out) throws ParseException {
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        int days = 0 ;
        Date start = sdf2.parse(in);
        Date end = sdf2.parse(out);
        Calendar st = Calendar.getInstance();
        st.setTime(start);
        Calendar en = Calendar.getInstance();
        en.setTime(end);
        days = ((int)(en.getTime().getTime()/1000)-(int)(st.getTime().getTime()/1000))/(60*60*24);
        return days;
    }

}
