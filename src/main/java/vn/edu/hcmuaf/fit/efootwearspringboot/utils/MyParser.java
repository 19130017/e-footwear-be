package vn.edu.hcmuaf.fit.efootwearspringboot.utils;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class MyParser {
    public static String convertToSlug(String textVietnamese) {
        String format = Normalizer
                .normalize(textVietnamese, Normalizer.Form.NFD)
                .replaceAll("\u0111|\u0110", "d")
                .replaceAll("[^\\p{ASCII}]", "")
                .replaceAll("\'|\s", "-")
                .replaceAll("\"|\\]|\\[|\\(|\\)", "").toLowerCase();
        return format;
    }
}
