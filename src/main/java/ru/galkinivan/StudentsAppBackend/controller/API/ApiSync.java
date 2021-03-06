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
        JSONObject returnSyncData = new JSONObject();
        JSONObject returnedDueToIDs = new JSONObject();
        JSONObject returnedToSync = new JSONObject();
        try {
            fullSyncData = (JSONObject) (new JSONParser()).parse(data);
        }
        catch (ParseException pe){
            System.out.println("ApiSync :: doSync : parse exception with massage "+pe.getLocalizedMessage());
            return "{ \"error\" : \"true\" }";
        }

        //JSON Arrays with data to be returned due to being new so that client has all the IDs...
        JSONArray tasksForClientDueToIDs = new JSONArray();
        JSONArray teachersForClientDueToIDs = new JSONArray();
        JSONArray timeTableEventsForClientDueToIDs = new JSONArray();
        JSONArray activitiesForClientDueToIDs = new JSONArray();
        JSONArray subjectsForClientDueToIDs = new JSONArray();

        Long groupIDLong = Long.parseLong(fullSyncData.get("groupID").toString());
        studyGroup = groupDao.findOne(groupIDLong);
        JSONObject toServerData = (JSONObject) fullSyncData.get("toServerData");
        JSONArray tasksFromClient = (JSONArray) toServerData.get("tasks");

        for (Object task : tasksFromClient){
            if (task instanceof JSONObject){
                Long newID = handleTaskFromClient((JSONObject) task);
                if(newID != null){
                    ((JSONObject) task).put("id", newID);
                    tasksForClientDueToIDs.add(task);
                    System.out.println("New task id "+tasksForClientDueToIDs.toJSONString());
                }
            }
        }

        //Put returned due to ids data to return struct
        returnedDueToIDs.put("tasks", tasksForClientDueToIDs);
        returnedDueToIDs.put("teachers", teachersForClientDueToIDs);
        returnedDueToIDs.put("timeTableEvents", timeTableEventsForClientDueToIDs);
        returnedDueToIDs.put("activities", activitiesForClientDueToIDs);
        returnedDueToIDs.put("subjects", subjectsForClientDueToIDs);
        returnSyncData.put("returnedDueToNewIDs", returnedDueToIDs);

        //Put other data to return struct
        //TODO Add data to returnedToSync
        returnSyncData.put("returnedToSync", returnedToSync);
        System.out.println(returnSyncData.toJSONString());
        return returnSyncData.toJSONString();
    }

    //Returns not null if task is new and heeds to be returned to client with real ID
    private Long handleTaskFromClient(JSONObject task){
        Long toReturn = null;
        System.out.println(Long.parseLong(task.get("id").toString()));
        //Prepare data
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

        Task taskDBObject;
        //Check if task is new or not
        if(Integer.parseInt(task.get("id").toString()) != 0){
            toReturn = null;
            taskDBObject = taskDao.findOne(Long.parseLong(task.get("id").toString()));
        }
        else{
            //The case when a task is not present on the server
            //TODO Check if that task does actiualy present
            taskDBObject = new Task();
            toReturn = new Long(0);
        }

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

        if(toReturn != null){
            toReturn = taskDBObject.getId();
        }

        return toReturn;
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
