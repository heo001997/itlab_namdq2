package app;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App {
    public final static String pathDir = "/home/namquoc/ITLAB/BTVN_BAI4_NAMDQ2/data/";
    public final static String inputFileName = "input.txt";
    public final static String outputFilename = "output.txt";

    public static void main(String[] args) {
        String input = pathDir + inputFileName;
        String output = pathDir + outputFilename;

        try {
            countWords(input, output);
        } catch (Exception e) {
            System.out.println("Tep tin bi loi");
        }

    }

    private static void countWords(String input, String output) throws Exception {
        HashMap<String, Integer> list = null;
        FileInputStream in = null;
        FileOutputStream out = null;
        int total = 0;

        try {

            // khoi tao input, output
            in = new FileInputStream(input);
            out = new FileOutputStream(output);

            // khoi tao bien
            list = new HashMap<String, Integer>();
            String tmp = new String();
            int c;

            while ((c = in.read()) != -1) {
                // chuyen sang chu cai thuong, dong thoi kiem tra ky tu hop le
                int a = Utils.validateChar(c);

                if (a > -1) {
                    // neu ky tu hop le => cong vao chuoi
                    tmp += (char) a;
                } else if (!tmp.isEmpty()) {
                    // neu ky tu khong hop le
                    // va chuoi khong trong
                    total++;

                    // lay so lan lap cua tu trong mang
                    Integer count = list.get(tmp);

                    if (count == null) {
                        // neu chua lap => tao moi tu trong mang
                        list.put(tmp, 1);
                    } else {
                        // nguoc lai => tang so lan lap => cap nhat so lan lap
                        count++;
                        list.put(tmp, count);
                    }

                    // khoi tao bien tam
                    tmp = "";
                }
            }

            // sap xep mang theo so lan xuat hien cua tu giam dan
            List<Map.Entry<String, Integer>> sorted = Utils.sortByValue(list);

            // in so tu hop le vao tep tin
            byte[] a = new String("TOTAL " + total).getBytes();
            out.write(a);
            out.write(10);

            // in danh sach tu da sap xep
            for (Map.Entry<String, Integer> item : sorted) {
                a = new String(item.getKey() + " " + item.getValue()).getBytes();
                out.write(a);
                out.write(10);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Tep tin khong ton tai!");
        } catch (IOException e) {
            System.out.println("Tep tin bi loi!");
        } catch (Exception e) {
            System.out.println("Co loi xay ra");
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }
}