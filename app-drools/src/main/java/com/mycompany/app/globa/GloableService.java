package com.mycompany.app.globa;

import com.mycompany.app.bean.People;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author john
 */
@Service
public class GloableService {

    public static List<People> getPeoples() {
        List<People> peoples = new ArrayList<>();
        peoples.add(new People(1, "春", "global"));
        peoples.add(new People(2, "夏", "global"));
        peoples.add(new People(3, "秋", "global"));
        peoples.add(new People(4, "冬", "global"));
        peoples.add(new People(5, "达", "global"));
        return peoples;
    }
}
