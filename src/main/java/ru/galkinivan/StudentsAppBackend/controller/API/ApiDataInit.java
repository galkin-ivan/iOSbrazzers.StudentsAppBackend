package ru.galkinivan.StudentsAppBackend.controller.API;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.galkinivan.StudentsAppBackend.dao.GroupDao;
import ru.galkinivan.StudentsAppBackend.dao.TimeTableDao;
import ru.galkinivan.StudentsAppBackend.model.Group;
import ru.galkinivan.StudentsAppBackend.model.Subject;
import ru.galkinivan.StudentsAppBackend.model.Teacher;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Set;

@Controller
@RequestMapping(value = "/api/dataInit", produces="application/json; charset=UTF-8")
@ResponseBody
public class ApiDataInit {

    @Autowired
    private GroupDao groupDao;

    @Autowired
    private TimeTableDao timeTableDao;

    @RequestMapping(value = "")
    @SuppressWarnings("unchecked")
    @Transactional
    public String getInitialData( @RequestParam(value = "groupId") Long groupId){
        JSONObject initialData = new JSONObject();

        Group group = groupDao.findOne(groupId);

        Set<Subject> subjects = group.getSubjects();
        Set<Teacher> teachers = group.getTeachers();

        // add subjects to initialData
        JSONArray jsonSubjects = new JSONArray();
        for (Subject subject : subjects){
            JSONObject jsonSubject = new JSONObject();
            jsonSubject.put("id", subject.getId());
            jsonSubject.put("name", subject.getName());
            jsonSubjects.add(jsonSubject);
        }
        initialData.put("subjects", jsonSubjects);


        return initialData.toJSONString();
    }

}
