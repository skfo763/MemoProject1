package com.example.user.memoproject.Models;

import java.util.HashMap;
import java.util.Map;

public class MemoModels {
    public Map<String, Memos> memos = new HashMap<>();

    public static class Memos {
        public String title;
        public String memo;
        public String time;
    }
}
