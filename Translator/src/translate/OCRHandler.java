package translate;

import com.baidu.aip.ocr.AipOcr;

public class OCRHandler {
    //设置APPID/AK/SK
    public static final String APP_ID = "11216689";
    public static final String API_KEY = "QFNUXjhLBEc02ngZaLL8vaLF";
    public static final String SECRET_KEY = "ShSo1rKXMEvyN8xp54DZ0TrpQm1ZOekT";

    public static String OCRService(String path){
        // 初始化一个AipOcr
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 调用接口
        return OCRUtils.parseJson(OCRUtils.doOCR(client,path));

    }
}
