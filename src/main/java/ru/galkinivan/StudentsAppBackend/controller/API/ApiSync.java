package ru.galkinivan.StudentsAppBackend.controller.API;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.galkinivan.StudentsAppBackend.dao.GroupDao;
import ru.galkinivan.StudentsAppBackend.dao.SubjectDao;
import ru.galkinivan.StudentsAppBackend.dao.TaskDao;
import ru.galkinivan.StudentsAppBackend.model.Group;
import ru.galkinivan.StudentsAppBackend.model.Subject;
import ru.galkinivan.StudentsAppBackend.model.Task;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping(value = "/api/sync", produces="application/json; charset=UTF-8")
@ResponseBody
public class ApiSync {

    @Autowired
    private TaskDao taskDao;

    @Autowired
    private GroupDao groupDao;

    @Autowired
    private SubjectDao subjectDao;

    private Group studyGroup;

    @RequestMapping(value = "")
    @SuppressWarnings("unchecked")
    @Transactional
    public String doSync( @RequestParam(value = "data") String data){
        System.out.println(data);
        JSONObject fullSyncData;
        try {
            fullSyncData = (JSONObject) (new JSONParser()).parse(data);
        }
        catch (ParseException pe){
            System.out.println("ApiSync :: doSync : parse exception with massage "+pe.getLocalizedMessage());
            return "{ \"error\" : \"true\" }";
        }

        Long groupIDLong = Long.parseLong(fullSyncData.get("groupID").toString());
        studyGroup = groupDao.findOne(groupIDLong);
        JSONObject toServerData = (JSONObject) fullSyncData.get("toServerData");
        JSONArray tasksFromClient = (JSONArray) toServerData.get("tasks");

        for (Object task : tasksFromClient){
            if (task instanceof JSONObject){
                handleTaskFromClient((JSONObject) task);
            }
        }

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

    private void handleTaskFromClient(JSONObject task){
        System.out.println(Long.parseLong(task.get("id").toString()));
        if(Integer.parseInt(task.get("id").toString()) != 0){

            String dateString = task.get("date").toString();
            DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            Date date = null;
            try {
                 date = format.parse(dateString);
            }
            catch (java.text.ParseException pe){
                System.out.println("HandleTaskFromClient :: date parse exception");
            }

            String description = task.get("description").toString();
            String shortName = task.get("shortName").toString();
            Integer status = Integer.parseInt(task.get("status").toString());
            Integer priority = Integer.parseInt(task.get("priority").toString());
            String subjectString = task.get("subject").toString();

            Task taskDBObject = taskDao.findOne(Long.parseLong(task.get("id").toString()));
            taskDBObject.setDescription(description);
            taskDBObject.setShortName(shortName);
            taskDBObject.setStatus(status);
            taskDBObject.setPriority(priority);
            taskDBObject.setDate(date);
            taskDBObject.setGroup(studyGroup);
            Subject subject;
            if(doesSubjectExists(subjectString)){
                subject = subjectDao.findByName(subjectString);
            }else {
                subject = new Subject();
                subject.setName(subjectString);
                subjectDao.save(subject);
            }
            taskDBObject.setSubject(subject);

            taskDao.save(taskDBObject);
        }
    }

    private boolean doesSubjectExists(String subjectName){
        Subject subject = subjectDao.findByName(subjectName);
        if(subject == null){
            return false;
        }else {
            return true;
        }
    }

}
