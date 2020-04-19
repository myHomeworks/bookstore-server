package com.whu.bookstore_server.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.whu.bookstore_server.domain.Classification;
import com.whu.bookstore_server.service.ClassificationService;
import com.whu.bookstore_server.utils.ClassList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/admin/bookClass")
@Component("AdminBookClassController")
public class BookClassController {
    @Autowired
    private ClassificationService classService;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @PostMapping("/all")
    public HashMap<String, Object> all(@RequestBody String body) {
        HashMap<String, Object> ret = new HashMap<>();
        List<Classification> fathers = classService.getAllFathers();
        List<ClassList> classLists = new ArrayList<>();
        for(Classification father: fathers){
            ClassList classList = new ClassList();
            classList.setId(father.getClassId());
            classList.setName(father.getName());
            List<Classification> children = classService.getAllChildrenByFatherId(father.getClassId());
            List<String> names = new ArrayList<>();
            List<String> ids = new ArrayList<>();
            for(Classification child: children){
                names.add(child.getName());
                ids.add(child.getClassId());
            }
            classList.setPages(names);
            classList.setPagesId(ids);
            classLists.add(classList);
        }
        ret.put("list", classLists);
        ret.put("state", "ok");
        return ret;
    }

    @PostMapping("/add")
    public HashMap<String, Object> add(@RequestBody String body) {
        HashMap<String, Object> ret = new HashMap<>();
        JSONObject object = JSONObject.parseObject(body);
        Classification classification = new Classification();
        String fatherName = object.getString("fatherName");
        if(fatherName == null || fatherName.equals("")){
            classification.setIfFather(1);
        }else{
            Classification fatherClass = classService.getFatherClassByName(fatherName);
            if (fatherClass == null) {
                ret.put("state", "noFather");
                return ret;
            }
            classification.setFatherId(fatherClass.getClassId());
            classification.setIfFather(0);
        }

        classification.setClassId(UUID.randomUUID().toString());
        classification.setName(object.getString("name"));
        classification.setTime(dateFormat.format(new Date()));
        if (classService.addClass(classification) != 1)
            ret.put("state", "failAdd");
        else
            ret.put("state", "ok");
        return ret;
    }

    @PostMapping("/delete")
    public HashMap<String, Object> delete(@RequestBody String body) {
        HashMap<String, Object> ret = new HashMap<>();
        JSONObject object = JSONObject.parseObject(body);
        String classId = object.getString("classId");
        if (classService.deleteClassById(classId) == 1)
            ret.put("state", "ok");
        else
            ret.put("state", "failDelete");
        return ret;
    }
}
