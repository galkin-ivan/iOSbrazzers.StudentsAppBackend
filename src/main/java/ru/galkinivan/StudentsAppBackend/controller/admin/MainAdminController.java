package ru.galkinivan.StudentsAppBackend.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.galkinivan.StudentsAppBackend.dao.UniversityDao;
import ru.galkinivan.StudentsAppBackend.model.University;

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
}
