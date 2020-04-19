package com.whu.bookstore_server.controller.user;

import com.whu.bookstore_server.domain.Classification;
import com.whu.bookstore_server.service.ClassificationService;
import com.whu.bookstore_server.utils.ClassList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/user/bookClass")
@Component("BookClassController")
public class BookClassController {
    @Autowired
    private ClassificationService classService;

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
}
