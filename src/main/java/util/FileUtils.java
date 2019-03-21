package util;

import java.io.File;
import java.io.FileOutputStream;

public class FileUtils {
    public static void writeToFile(String data) {
        byte[] sourceByte = data.getBytes();
        String path = "./";
        String fileName = "final.txt";
        if (null != sourceByte) {
            try {
                File file = new File(path + fileName);//文件路径（路径+文件名）
                if (!file.exists()) {   //文件不存在则创建文件，先创建目录
                    File dir = new File(file.getParent());
                    dir.mkdirs();
                    file.createNewFile();
                }
                FileOutputStream outStream = new FileOutputStream(file); //文件输出流将数据写入文件
                outStream.write(sourceByte);
                outStream.close();
            } catch (Exception e) {
                e.printStackTrace();
                // do something
            }
        }
    }
}
