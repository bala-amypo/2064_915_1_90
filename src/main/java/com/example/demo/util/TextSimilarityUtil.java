package com.example.demo.util;

public class TextSimilarityUtil {

    public static double similarity(String a, String b) {
        if (a == null || b == null) return 0.0;
        a = a.trim().toLowerCase();
        b = b.trim().toLowerCase();

        if (a.equals(b)) return 1.0;
        return 0.0; // simple match; replace with advanced logic if needed
    }
}
