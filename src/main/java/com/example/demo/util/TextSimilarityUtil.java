package com.example.demo.util;

public class TextSimilarityUtil {

    public static double calculateSimilarity(String a, String b) {
        if (a == null || b == null) return 0.0;
        return a.equalsIgnoreCase(b) ? 1.0 : 0.0;
    }
}
