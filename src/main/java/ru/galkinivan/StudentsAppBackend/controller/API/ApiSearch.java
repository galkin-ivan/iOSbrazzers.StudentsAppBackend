package ru.galkinivan.StudentsAppBackend.controller.API;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.galkinivan.StudentsAppBackend.dao.SubjectDao;
import ru.galkinivan.StudentsAppBackend.model.Subject;

import java.text.SimpleDateFormat;

@Controller
@RequestMapping(value = "/api/search", produces="application/json; charset=UTF-8")
@ResponseBody
public class ApiSearch {

    @Autowired
    private SubjectDao subjectDao;

    @RequestMapping(value = "findSubjectsWitchHaveStringInName")
    @SuppressWarnings("unchecked")
    @Transactional
    public String findSubjectsByName( @RequestParam(value = "stringToFindInName") String stringToFindInName){
        JSONObject returnData = new JSONObject();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");

        final Iterable<Subject> subjects = subjectDao.findByNameWithLike(stringToFindInName);

        // compose return data
        JSONArray jsonSubjects = new JSONArray();
        for (Subject subject : subjects){
            JSONObject jsonSubject = new JSONObject();
            jsonSubject.put("id", subject.getId());
            jsonSubject.put("name", subject.getName());

            jsonSubjects.add(jsonSubject);
        }
        returnData.put("fondSubjects", jsonSubjects);

        return returnData.toJSONString();
    }

}
