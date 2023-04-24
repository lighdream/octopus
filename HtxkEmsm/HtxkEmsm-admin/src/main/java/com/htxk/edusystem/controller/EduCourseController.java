package com.htxk.edusystem.controller;

import com.htxk.edusystem.domain.EduCourse;
import com.htxk.edusystem.service.IEduCourseService;
import com.htxk.edusystem.service.IEduStudentCourseService;
import com.htxk.ruoyi.common.annotation.Log;
import com.htxk.ruoyi.common.core.controller.BaseController;
import com.htxk.ruoyi.common.core.domain.AjaxResult;
import com.htxk.ruoyi.common.core.page.TableDataInfo;
import com.htxk.ruoyi.common.enums.BusinessType;
import com.htxk.ruoyi.common.utils.poi.ExcelUtil;
import com.htxk.ruoyi.framework.util.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 课程Controller
 *
 * @author maple
 * @date 2020-01-03
 */
@Controller
@RequestMapping("/edusystem/course")
public class EduCourseController extends BaseController {
    private String prefix = "edusystem/course";

    @Autowired
    private IEduCourseService eduCourseService;

    @Autowired
    private IEduStudentCourseService studentCourseService;

    @RequiresPermissions("edusystem:course:view")
    @GetMapping()
    public String course() {
        return prefix + "/course";
    }

    /**
     * 查询课程列表
     */
    @RequiresPermissions("edusystem:course:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(EduCourse eduCourse) {
        startPage();
        List<EduCourse> list = eduCourseService.selectEduCourseList(null,eduCourse);
        return getDataTable(list);
    }

    /**
     * 导出课程列表
     */
    @RequiresPermissions("edusystem:course:export")
    @Log(title = "课程", businessType = BusinessType.EXPORT)
    @RequestMapping("/export")
    @ResponseBody
    public AjaxResult export(EduCourse eduCourse) {
        List<EduCourse> list = eduCourseService.selectEduCourseList(ShiroUtils.getUserId(),eduCourse);
        ExcelUtil<EduCourse> util = new ExcelUtil<EduCourse>(EduCourse.class);
        return util.exportExcel(list, "course");
    }

    /**
     * 新增课程
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存课程
     */
    @RequiresPermissions("edusystem:course:add")
    @Log(title = "课程", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(EduCourse eduCourse) {
        eduCourse.setCreateBy(ShiroUtils.getLoginName());
        eduCourse.setCreateTime(new Date());
        return toAjax(eduCourseService.insertEduCourse(eduCourse));
    }

    /**
     * 修改课程
     */
    @GetMapping("/edit/{courseId}")
    public String edit(@PathVariable("courseId") Long courseId, ModelMap mmap) {
        EduCourse eduCourse = eduCourseService.selectEduCourseById(courseId);
        mmap.put("eduCourse", eduCourse);
        return prefix + "/edit";
    }

    /**
     * 修改保存课程
     */
    @RequiresPermissions("edusystem:course:edit")
    @Log(title = "课程", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(EduCourse eduCourse) {
        eduCourse.setUpdataBy(ShiroUtils.getLoginName());
        eduCourse.setUpdateTime(new Date());
        return toAjax(eduCourseService.updateEduCourse(eduCourse));
    }

    /**
     * 删除课程
     */
    @RequiresPermissions("edusystem:course:remove")
    @Log(title = "课程", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(eduCourseService.deleteEduCourseByIds(ids));
    }


    /**
     * 选课
     */
    @RequiresPermissions("edusystem:course:select")
    @Log(title = "选课", businessType = BusinessType.OTHER)
    @PostMapping("/select")
    @ResponseBody
    public AjaxResult select(@RequestParam("id") Long id) {
        Long userId = ShiroUtils.getUserId();
        studentCourseService.select(userId,id);
        return AjaxResult.success();
    }

    @GetMapping("/mycourses")
//    @RequiresPermissions("edusystem:course:viewcourse")
    public String getMyCourses() {
        return prefix + "/mycourse.html";
    }

    /**
     * 我的课表
     */
//    @RequiresPermissions("edusystem:course:mycourse")
    @Log(title = "选课", businessType = BusinessType.OTHER)
    @RequestMapping("/mycourse")
    @ResponseBody
    public TableDataInfo myCourses(EduCourse eduCourse) {
        Long userId = ShiroUtils.getUserId();
        startPage();
        List<EduCourse> list = eduCourseService.selectEduCourseList(userId,eduCourse);
        return getDataTable(list);
    }

    @Log(title = "退课", businessType = BusinessType.OTHER)
    @RequestMapping("/drop")
    @ResponseBody
    public AjaxResult dropCourses(EduCourse eduCourse) {
        Long userId = ShiroUtils.getUserId();
        eduCourseService.dropCourseList(userId,eduCourse);
        return AjaxResult.success();
    }




}
