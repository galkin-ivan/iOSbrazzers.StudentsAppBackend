package ru.galkinivan.StudentsAppBackend.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.galkinivan.StudentsAppBackend.dao.*;
import ru.galkinivan.StudentsAppBackend.model.*;

/**
 *
 * @author galkinivan
 * @version 1.0
 */

@RequestMapping(value = "/adminka", method = RequestMethod.GET)
@Controller
public class MainAdminController {

    @Autowired
    private UniversityDao universityDao;

    @Autowired
    private SubjectDao subjectDao;

    @Autowired
    private TeacherDao teacherDao;

    @Autowired
    private ActivityDao activityDao;

    @Autowired
    private TaskDao taskDao;

    @Autowired
    private TimeTableDao timeTableDao;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(){
        return "admin/mainindex";
    }

    @RequestMapping(value = "showUniversities", method = RequestMethod.GET)
    @Transactional
    public ModelAndView showUniversities(){
        Iterable<University> universities = universityDao.findAll();

        //List<String> testArr = Arrays.asList("igor", "pidor", "Ivan");
        for(University university : universities){
            university.getFaculties();
        }

        ModelAndView modelAndView = new ModelAndView("admin/dbManagement/showUniversities");
        //modelAndView.addObject("testArr", testArr);
        modelAndView.addObject("data", universities);

        return modelAndView;
    }

    @RequestMapping(value = "showSubjects", method = RequestMethod.GET)
    public ModelAndView showSubjects(){
        ModelAndView modelAndView = new ModelAndView("admin/dbManagement/showSubjects");

        Iterable<Subject> subjects = subjectDao.findAll();

        modelAndView.addObject("subjects", subjects);

        return modelAndView;
    }

    @RequestMapping(value = "showTeachers", method = RequestMethod.GET)
    public ModelAndView showTeachers(){
        ModelAndView modelAndView = new ModelAndView("admin/dbManagement/showTeachers");

        Iterable<Teacher> teachers = teacherDao.findAll();
        modelAndView.addObject("teachers", teachers);
        return modelAndView;
    }

    @RequestMapping(value = "showActivities", method = RequestMethod.GET)
    public ModelAndView showActivities(){
        ModelAndView modelAndView = new ModelAndView("admin/dbManagement/showActivities");
        Iterable<Activity> activities = activityDao.findAll();
        modelAndView.addObject("activities", activities);
        return modelAndView;
    }

    @RequestMapping(value = "showTasks", method = RequestMethod.GET)
    public ModelAndView showTasks(){
        ModelAndView modelAndView = new ModelAndView("admin/dbManagement/showTasks");
        Iterable<Task> tasks = taskDao.findAll();
        modelAndView.addObject("tasks", tasks);
        return modelAndView;
    }

    @RequestMapping(value = "showTimeTable", method = RequestMethod.GET)
    public ModelAndView showTimeTable(){
        ModelAndView modelAndView = new ModelAndView("admin/dbManagement/showTimeTable");
        Iterable<TimeTable> timeTables = timeTableDao.findAll();
        modelAndView.addObject("timeTables", timeTables);
        return modelAndView;
    }
}
