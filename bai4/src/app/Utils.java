package app;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Untils
 */
public class Utils {

    public static int validateChar(int c) {
        // dua theo bang ma asscii

        if (c < 48) {
            // ky tu khong hop le
            return -1;
        } else if (c < 58) {
            // ky tu la chu so
            return c;
        } else if (c < 65) {
            // ky tu khong hop le
            return -1;
        } else if (c < 91) {
            // ky tu la chu cai in hoa
            // chuyen ve chu cai thuong
            return c + 32;
        } else if (c < 97) {
            // ky tu khong hop le
            return -1;
        } else if (c < 123) {
            // ky tu la chu cai thuong
            return c;
        } else {
            // ky tu khong hop le
            return -1;
        }
    }

    public static List<Map.Entry<String, Integer>> sortByValue(HashMap<String, Integer> before) {
        // tao list tu hashmap
        List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(before.entrySet());

        // sap xep list
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        return list;
    }
}