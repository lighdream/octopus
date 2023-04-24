package com.htxk.edusystem.service.impl;

import com.htxk.edusystem.domain.EduStudent;
import com.htxk.edusystem.mapper.EduStudentCourseMapper;
import com.htxk.edusystem.mapper.EduStudentMapper;
import com.htxk.edusystem.service.IEduStudentCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EduStudentCourseServiceImpl implements IEduStudentCourseService {

    @Autowired
    private EduStudentCourseMapper eduStudentCourseMapper;

    @Autowired
    private EduStudentMapper eduStudentMapper;

    /**
     * 选课
     * @param userId 选课用户
     * @param cid 选课id
     */
    @Override
    public void select(Long userId, Long cid) {
        EduStudent eduStudent = eduStudentMapper.selectEduStudentByUserId(userId);
        eduStudentCourseMapper.addSelectCourse(eduStudent.getStudentId(),cid);
    }


}
