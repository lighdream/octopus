package com.htxk.edusystem.domain;

import com.htxk.ruoyi.common.core.domain.BaseEntity;

public class EduStudentCourse extends BaseEntity {

    public Long id;

    public Long studentId;

    public Long courseId;

    public EduStudentCourse() {
    }

    public EduStudentCourse(Long id, Long studentId, Long courseId) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
}
