package ru.galkinivan.StudentsAppBackend.controller.testEnv;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.galkinivan.StudentsAppBackend.dao.UniversityDao;
import ru.galkinivan.StudentsAppBackend.model.University;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * ApiController for test environment for remote app
 *
 * @author Galkin Ivan
 * @version 1.0
 */

@Controller
@RequestMapping(value = "/testEnv/api", produces="application/json; charset=UTF-8")
@ResponseBody
public class ApiController {

    @Autowired
    private UniversityDao universityDao;

    @RequestMapping(value = "")
    public String index(){
        return "He hey, api is online)) Привет лала";
    }

    @RequestMapping(value = "getJSONinitialDataForRemoteApp")
    public String getInitialDataForRemoteApp(){
        JSONParser jsonParser = new JSONParser();

        try{
            Object object = jsonParser.parse(new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/json/InitialDataForDB.json"))));

            JSONObject jsonObject = (JSONObject) object;
            return jsonObject.toJSONString();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return "";
    }

    @RequestMapping(value = "universities")
    @SuppressWarnings("unchecked")
    public String getUniversities(){
        Iterable<University> universities = universityDao.findAll();
        JSONObject jsonObject = new JSONObject();

        JSONArray jsonArray = new JSONArray();

        for(University university : universities) {
/*
            System.out.println("University "+university.getName()+" has faculties:");
            Set<Faculty> faculties = university.getFaculties();
            if(faculties != null){
                for(Faculty faculty : faculties){
                    System.out.println("\t"+faculty.getName()+ (faculty.getDescription() != null ? " descr: "+faculty.getDescription():""));
                    Set<Group> groups = faculty.getGroups();
                    if(groups != null){
                        for(Group group : groups){
                            System.out.println("\t\t"+group.getName());
                        }
                    }
                }
            }
            System.out.println("end.");
            */


            JSONObject un = new JSONObject();
            un.put("name", university.getName());
            un.put("description", university.getDescription());
            jsonArray.add(un);
        }

        jsonObject.put("universities", jsonArray);
        return jsonObject.toJSONString();
    }
}
