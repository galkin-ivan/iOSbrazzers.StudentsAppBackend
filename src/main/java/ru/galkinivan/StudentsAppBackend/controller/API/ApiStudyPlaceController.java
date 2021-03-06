package ru.galkinivan.StudentsAppBackend.controller.API;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.galkinivan.StudentsAppBackend.dao.FacultyDao;
import ru.galkinivan.StudentsAppBackend.dao.UniversityDao;
import ru.galkinivan.StudentsAppBackend.model.Faculty;
import ru.galkinivan.StudentsAppBackend.model.Group;
import ru.galkinivan.StudentsAppBackend.model.University;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * ApiStudyPlaceController for test environment for remote app
 *
 * @author Galkin Ivan
 * @version 1.0
 */

@Controller
@RequestMapping(value = "/api/studyPlace", produces="application/json; charset=UTF-8")
@ResponseBody
public class ApiStudyPlaceController {

    @Autowired
    private UniversityDao universityDao;

    @Autowired
    private FacultyDao facultyDao;

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
    @Transactional
    public String getUniversities(){
        Iterable<University> universities = universityDao.findAll();
        JSONObject jsonObject = new JSONObject();

        JSONArray jsonArray = new JSONArray();

        for(University university : universities) {
            JSONObject un = new JSONObject();
            un.put("id", university.getId());
            un.put("name", university.getName());
            un.put("description", university.getDescription());
            jsonArray.add(un);
        }

        jsonObject.put("universities", jsonArray);
        return jsonObject.toJSONString();
    }

    @RequestMapping(value = "faculties", method = RequestMethod.GET)
    @SuppressWarnings("unchecked")
    @Transactional
    public String getFaculties( @RequestParam(value = "university", required = false) Long universityName) {

        Iterable<Faculty> faculties = null;

        if (universityName == null) {
            faculties = facultyDao.findAll();
        } else {
            University university = universityDao.findOne(universityName);
            if (university != null)
                faculties = university.getFaculties();
        }

        JSONObject jsonObject = new JSONObject();
        if (faculties != null) {

            JSONArray jsonArray = new JSONArray();

            for (Faculty faculty : faculties) {
                JSONObject tmpJSON = new JSONObject();

                tmpJSON.put("id", faculty.getId());
                tmpJSON.put("name", faculty.getName());
                tmpJSON.put("description", faculty.getDescription());
                jsonArray.add(tmpJSON);
            }

            jsonObject.put("faculties", jsonArray);
        }else {
            jsonObject.put("faculties", null);
        }
        return jsonObject.toJSONString();
    }

    @RequestMapping(value = "groups", method = RequestMethod.GET)
    @SuppressWarnings("unchecked")
    @Transactional
    public String getGroups( @RequestParam(value = "university") Long universityId,
                                @RequestParam(value = "faculty") Long facultyId) {

        Iterable<Faculty> faculties = null;
        Faculty selectedFaculty = null;

        University university = universityDao.findOne(universityId);
        if (university != null)
            faculties = university.getFaculties();

        JSONObject jsonObject = new JSONObject();

        if (faculties != null) {

            JSONArray jsonArray = new JSONArray();
            //--- Finding the selected faculty
            for (Faculty faculty : faculties) {
                if (faculty.getId().equals(facultyId)) {
                    selectedFaculty = faculty;
                    break;
                }
            }
            Iterable<Group> groups = selectedFaculty.getGroups();
            if(groups != null) {
                for (Group group : groups) {
                    JSONObject un = new JSONObject();
                    un.put("id", group.getId());
                    un.put("name", group.getName());
                    jsonArray.add(un);
                }

                jsonObject.put("groups", jsonArray);
            }
            else{
                jsonObject.put("groups", null);
            }
        }else {
            jsonObject.put("groups", null);
        }

        return jsonObject.toJSONString();
    }
}
