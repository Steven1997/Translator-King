package translate;

import com.baidu.aip.ocr.AipOcr;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.JSONObject;

import java.util.HashMap;

public class OCRUtils {
    public static String doOCR(AipOcr client,String path) {
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("language_type", "CHN_ENG");
        options.put("detect_direction", "true");
        options.put("detect_language", "true");
        options.put("probability", "true");
        JSONObject res = client.basicGeneral(path, options);
        return res.toString(2);
    }

    public static String parseJson(String json){
        JsonElement element =new JsonParser().parse(json);
        JsonObject obj = element.getAsJsonObject();
        JsonArray array = obj.getAsJsonArray("words_result");
        int size = 0;
        if(array != null)
            size = array.size();
        String res = "";
        String temp = "";
        for(int i = 0;i < size;i++){
            temp = array.get(i).getAsJsonObject().get("words").toString();
            int len = temp.length();
            temp = temp.substring(1,len - 1);
            res += temp + "\n";
        }

        return res;


    }
}
