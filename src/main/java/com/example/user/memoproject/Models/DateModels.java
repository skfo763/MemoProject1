package com.example.user.memoproject.Models;

import java.util.HashMap;
import java.util.Map;

public class DateModels {
    public Map<String, Dates> dates = new HashMap<>();

    public static class Dates {
        public String startyear;
        public String startmonth;
        public String startday;
        public String eventname;
        public String location;
        public String enddate;
        public String fatality;
    }
}
