package ru.galkinivan.StudentsAppBackend.controller.API;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/api/sync", produces="application/json; charset=UTF-8")
@ResponseBody
public class ApiSync {

    @RequestMapping(value = "")
    @SuppressWarnings("unchecked")
    @Transactional
    public String doSync( @RequestParam(value = "data") String data){
        System.out.println(data);

        JSONObject initialData = new JSONObject();

        // add subjects to initialData
        JSONArray jsonSubjects = new JSONArray();

        initialData.put("subjects", jsonSubjects);

        // add teachers to initialData
        JSONArray jsonTeachers = new JSONArray();

        initialData.put("teachers", jsonTeachers);

        // add activities to initialData
        JSONArray jsonActivities = new JSONArray();

        initialData.put("activities", jsonActivities);

        // add tasks to initialData
        JSONArray jsonTasks = new JSONArray();

        initialData.put("tasks", jsonTasks);

        // add time table events to initialData
        JSONArray jsonEvents = new JSONArray();

        initialData.put("timeTableEvents", jsonEvents);

        return initialData.toJSONString();
    }

}
