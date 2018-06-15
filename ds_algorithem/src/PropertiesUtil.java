import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

@SuppressWarnings("all")
public class PropertiesUtil {

    private static Map<String, String> loadFiles(String path, String fileName) {
        File filePath = new File(path);
        File zhFiles[] = filePath.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                boolean isAccept = false;
                if (fileName.equals(name)) {
                    isAccept = true;
                }
                return isAccept;
            }
        });

        Map<String, String> entries = new LinkedHashMap();

        for (File file : zhFiles) {
            // System.out.println(file.getName());

            try (InputStream input = new FileInputStream(file)) {
                InputStreamReader reader = new InputStreamReader(input);
                BufferedReader bufferReader = new BufferedReader(reader);
                bufferReader.lines().forEach(s -> {
                    if (s.contains("=")) {
                        String[] keyValues = s.split("=", 2);
                        if (keyValues.length == 2) {
                            String key = keyValues[0].trim();
                            // if(entries.containsKey(key)) {
                            // System.out.println(key);
                            // }
                            if (fileName.equals("avn_text_cn.properties")) {
                                entries.put(keyValues[0].trim(), " " + keyValues[1]);
                            } else {
                                entries.put(keyValues[0].trim(), keyValues[1]);
                            }
                        } else {
                            entries.put(keyValues[0].trim(), "");
                        }
                    } else {
                        if ("".equals(s)) {
                            String dummyKey = "****" + System.currentTimeMillis();
                            entries.put(dummyKey, "");
                            try {
                                Thread.sleep(3);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        } else {
                            entries.put(s, "");
                        }
                    }
                });

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return entries;
    }

    public static void main(String[] args) {
        Map<String, String> oldEntries = PropertiesUtil.loadFiles("D:/IBM/WCDE_ENT70/workspace/Stores/src/AvnetSAS",
                "AvnetStoreText_zh_TW.properties");
        Map<String, String> newEntries = PropertiesUtil.loadFiles("E:/temp", "avn_text.properties");
        System.out.println("old entries: " + oldEntries.size());
        System.out.println("new entries: " + newEntries.size());

        for (String key : newEntries.keySet()) {
            if (oldEntries.containsKey(key)) {
                // String oldValue = oldEntries.get(key).trim();
                // String newValue = newEntries.get(key).trim();
                // if(!oldValue.equals(newValue)) {
                // System.out.println(key + "," + oldEntries.get(key) + "," +
                // newEntries.get(key));
                // }
                String removedKey = oldEntries.remove(key);
                System.out.println("Removed Key: " + key + ", Removed value: " + removedKey);
            }
        }

        // oldEntries.putAll(newEntries);
        System.out.println("merged entries: " + oldEntries.size());

        try {
            Writer writer = new FileWriter("E:/AvnetStoreText_zh_TW.properties");
            BufferedWriter bWriter = new BufferedWriter(writer);

            oldEntries.forEach((k, v) -> {
                try {
                    if (k.startsWith("****")) {
                        bWriter.write("\n");
                    } else if ("".equals(v)) {
                        bWriter.write(k + "\n");
                    } else {
                        bWriter.write(k + " =" + v + "\n");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            bWriter.flush();
            bWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
