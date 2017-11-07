package ru.galkinivan.StudentsAppBackend.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author galkinivan
 * @version 1.0
 */

@RequestMapping(value = "/adminka", method = RequestMethod.GET)
@Controller
public class MainAdminController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(){
        return "admin/mainindex";
    }

    @RequestMapping(value = "showUniversities", method = RequestMethod.GET)
    public ModelAndView showUniversities(){

        List<String> testArr = Arrays.asList("igor", "pidor", "Ivan");

        ModelAndView modelAndView = new ModelAndView("admin/dbManagement/showUniversities");
        modelAndView.addObject("testArr", testArr);

        return modelAndView;
    }
}
