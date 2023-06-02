package util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import java.io.*;

public class Base64Utils {
    // 函数1：将数据data转成png目录images/+ipath+id.png
    public static void saveBase64DataToPng(String data,String id,String ipath) {
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            //此为图片存储路径 后字符串为相对于项目的存储路径，前字符串不用管
            String path = Base64Utils.class.getResource("").getPath().split("target/")[0] + "src/main/webapp/images/" + ipath + id + ".png";
            FileOutputStream write = new FileOutputStream(new File(path));
            byte[] decoderBytes = decoder.decodeBuffer(data.split(",")[1]);
            write.write(decoderBytes);
            write.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 函数2：根据ipath和id找到id.png的图片转成base64返回
    public static String PngToBase64(int id,String ipath) {
        InputStream in = null;
        byte[] data = null;
        String path = Base64Utils.class.getResource("").getPath().split("target/")[0] + "src/main/webapp/images/" + ipath + id + ".png";
        try {
            in = new FileInputStream(path);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        BASE64Encoder encoder = new BASE64Encoder();
        return "data:image/png;base64," + encoder.encode(data).replace("\r\n","");
    }

}
