package service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sasinda on 12/10/15.
 */
public class FileStoreService {

    private static Map<String, List<File>> filesMap = new HashMap<>();

    public static List<File> getFiles(String username){
        return  filesMap.get(username);
    }

    public static void addFile(String username, File file){
        List<File> filesList = filesMap.get(username);
        if(filesList!=null){
            filesList.add(file);
        }else {
            filesList=new ArrayList<>();
            filesList.add(file);
            filesMap.put(username,filesList);
        }
    }

    public static void removeFiles(String username) {
        filesMap.remove(username);
    }
}
