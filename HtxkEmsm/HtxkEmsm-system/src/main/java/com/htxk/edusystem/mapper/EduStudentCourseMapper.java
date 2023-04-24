package com.htxk.edusystem.mapper;

import com.htxk.edusystem.domain.EduStudentCourse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EduStudentCourseMapper {

    void addSelectCourse(@Param("studentId") Long studentId,@Param("cid") Long cid);

    List<EduStudentCourse> selectCourseListByStudentId(Long studentId);

    void dropCourseList(@Param("studentId") Long studentId,@Param("courseId") Long courseId);
}
