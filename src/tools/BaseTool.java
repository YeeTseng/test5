package tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.UUID;

public class BaseTool {

    public String uploadFile (File file,String file1Name,String contentType) {
        String filePath = "";
        try {
            Properties properties = new Properties();
            properties.load(this.getClass().getClassLoader().getResourceAsStream("global.properties"));
            String path = properties.getProperty("uploadPath");
            String iconUrl = properties.getProperty("iconUrl");
            filePath = fileRename(iconUrl,file1Name == null? file.getName() : file1Name);
            FileInputStream inputStream = new FileInputStream(file);
            FileOutputStream outputStream = new FileOutputStream(new File(path,filePath));

            byte[] buf = new byte[1024];
            int length = 0 ;

            while(-1 != (length = inputStream.read(buf) ) )
            {
                outputStream.write(buf, 0, length) ;
            }
            //关闭输入输出流
            inputStream.close();
            outputStream.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        return filePath;
    }

    public String fileRename (String iconUrl,String fileName) {
        String fileType = fileName.substring(fileName.length() - 4);
        return iconUrl + getUUID() + fileType;
    }

    //随机生成16位UUID作为用户临时用户名
    public static String getUUID(){
        UUID id=UUID.randomUUID();
        String[] idd=id.toString().split("-");
        return idd[0];
    }

}
