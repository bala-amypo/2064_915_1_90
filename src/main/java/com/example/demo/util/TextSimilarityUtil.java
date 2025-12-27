package com.example.demo.util;

import java.util.*;

public class TextSimilarityUtil {
    public static double similarity(String a,String b){
        if(a==null || b==null || a.isBlank() || b.isBlank()) return 0.0;

        Set<String> s1 = new HashSet<>(Arrays.asList(a.toLowerCase().split("\\s+")));
        Set<String> s2 = new HashSet<>(Arrays.asList(b.toLowerCase().split("\\s+")));

        if(s1.isEmpty() || s2.isEmpty()) return 0.0;

        Set<String> inter = new HashSet<>(s1);
        inter.retainAll(s2);

        return (double) inter.size() / Math.max(s1.size(),s2.size());
    }
}
