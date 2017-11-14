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
import ru.galkinivan.StudentsAppBackend.model.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
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
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");

        Group group = groupDao.findOne(groupId);

        Set<Subject> subjects = group.getSubjects();
        Set<Teacher> teachers = group.getTeachers();
        Set<Activity> activities = group.getActivities();
        Set<Task> tasks = group.getTasks();
        Set<TimeTable> timeTableEvents = group.getTimeTableEvents();

        // add subjects to initialData
        JSONArray jsonSubjects = new JSONArray();
        for (Subject subject : subjects){
            JSONObject jsonSubject = new JSONObject();
            jsonSubject.put("id", subject.getId());
            jsonSubject.put("name", subject.getName());
            jsonSubjects.add(jsonSubject);
        }
        initialData.put("subjects", jsonSubjects);

        // add teachers to initialData
        JSONArray jsonTeachers = new JSONArray();
        for (Teacher teacher : teachers){
            JSONObject jsonTeacher = new JSONObject();
            jsonTeacher.put("id", teacher.getId());
            jsonTeacher.put("name", teacher.getName());
            jsonTeacher.put("familyName", teacher.getFamilyName());
            jsonTeacher.put("fatherName", teacher.getFatherName());
            jsonTeachers.add(jsonTeacher);
        }
        initialData.put("teachers", jsonTeachers);

        // add activities to initialData
        JSONArray jsonActivities = new JSONArray();
        for (Activity activity : activities){
            JSONObject jsonActivity = new JSONObject();
            jsonActivity.put("id", activity.getId());
            jsonActivity.put("shortName", activity.getShortName());
            jsonActivity.put("date", simpleDateFormat.format(activity.getDate()));
            jsonActivity.put("subject", activity.getSubject().getName());
            jsonActivities.add(jsonActivity);
        }
        initialData.put("activities", jsonActivities);

        // add tasks to initialData
        JSONArray jsonTasks = new JSONArray();
        for (Task task : tasks){
            JSONObject jsonTask = new JSONObject();
            jsonTask.put("id", task.getId());
            jsonTask.put("shortName", task.getShortName());
            jsonTask.put("date", simpleDateFormat.format(task.getDate()));
            jsonTask.put("subject", task.getSubject().getName());
            jsonTask.put("description", task.getDescription());
            jsonTask.put("priority", task.getPriority());
            jsonTask.put("status", task.getStatus());
            jsonTasks.add(jsonTask);
        }
        initialData.put("tasks", jsonTasks);

        // add time table events to initialData
        JSONArray jsonEvents = new JSONArray();
        for (TimeTable timeTableEvent : timeTableEvents){
            JSONObject jsonEvent = new JSONObject();
            jsonEvent.put("id", timeTableEvent.getId());
            jsonEvent.put("beginDate", timeTableEvent.getBeginDate() == null ? null : simpleDateFormat.format(timeTableEvent.getBeginDate()));
            jsonEvent.put("endDate", timeTableEvent.getEndDate() == null ? null : simpleDateFormat.format(timeTableEvent.getEndDate()));
            jsonEvent.put("date", timeTableEvent.getDate() == null ? null : simpleDateFormat.format(timeTableEvent.getDate()));
            jsonEvent.put("dayOfWeek", timeTableEvent.getDayOfWeek());
            jsonEvent.put("startTime", timeTableEvent.getStartTime());
            jsonEvent.put("endTime", timeTableEvent.getEndTime());
            jsonEvent.put("parity", timeTableEvent.getParity());
            jsonEvent.put("place", timeTableEvent.getPlace());
            jsonEvent.put("type", timeTableEvent.getType());
            jsonEvent.put("subject", timeTableEvent.getSubject().getName());
            if (timeTableEvent.getTeacher() != null) {
                JSONObject jsonTeacher = new JSONObject();
                jsonTeacher.put("teacherName", timeTableEvent.getTeacher().getName());
                jsonTeacher.put("teacherFamilyName", timeTableEvent.getTeacher().getFamilyName());
                jsonTeacher.put("teacherFatherName", timeTableEvent.getTeacher().getFatherName());
                jsonEvent.put("teacher", jsonTeacher);
            }else{
                jsonEvent.put("teacher", null);
            }
            jsonEvents.add(jsonEvent);
        }
        initialData.put("timeTableEvents", jsonEvents);

        return initialData.toJSONString();
    }

}
