package com.crayons_2_0.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crayons_2_0.component.Unit;
import com.crayons_2_0.model.Course;
import com.crayons_2_0.service.database.CourseService;
import com.crayons_2_0.service.database.UnitService;
import com.crayons_2_0.service.database.UnitService2;
import com.crayons_2_0.view.CurrentCourseDummy;

@Component
public class UnitEditor2 {
    @Autowired
	private Course course;
	
    
    @Autowired
	private Unit currentUnit;
	
	@Autowired
	CourseService courseService;
	
//	@Resource(name = "unitService2")
//	UnitService2 unitService2;
	
	public UnitEditor2() {
		
		String courseTitle = CurrentCourseDummy.getInstance().getCourseTitle();
		
//		this.course = courseService.findCourseByTitle(courseTitle); ToDO
		
//		this.setCurrentUnit(course.getUnits2().get(0)); ToDo
		
		
	}

	
	
	public Course getCourse() {
		return course;
	}


	public void setCourse(Course course) {
		this.course = course;
	}
	

	
	/**
	 * @return the currentUnit
	 */
	public Unit getCurrentUnit() {
		return currentUnit;
	}

	/**
	 * @param currentUnit the currentUnit to set
	 */
	public void setCurrentUnit(Unit currentUnit) {
		this.currentUnit = currentUnit;
	}
	
	
	
	
}
