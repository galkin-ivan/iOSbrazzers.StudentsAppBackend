package ru.galkinivan.StudentsAppBackend.controller.testEnv;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;

/**
 * ApiController for test environment for remote app
 *
 * @author Galkin Ivan
 * @version 1.0
 */

@Controller
@RequestMapping(value = "/testEnv/api")
public class ApiController {

    @RequestMapping(value = "")
    @ResponseBody
    public String index(){
        return "He hey, api is online)) Привет лала";
    }

    @RequestMapping(value = "getJSONinitialDataForRemoteApp", produces="application/json; charset=UTF-8")
    @ResponseBody
    public String getInitialDataForRemoteApp(){
        JSONParser jsonParser = new JSONParser();

        try{
            Object object = jsonParser.parse(new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/json/InitialDataForDB.json"))));

            JSONObject jsonObject = (JSONObject) object;
            //System.out.println(jsonObject.toJSONString());
            return jsonObject.toJSONString();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return "";
    }
}
