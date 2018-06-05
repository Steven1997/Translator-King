package translate;

import com.google.gson.*;

import java.util.HashMap;
import java.util.Map;

public class TransService {
    private static final String TRANS_API_HOST = "http://api.fanyi.baidu.com/api/trans/vip/translate";

    private String appid;
    private String securityKey;

    public TransService(String appid, String securityKey) {
        this.appid = appid;
        this.securityKey = securityKey;
    }

    public String getTransResult(String query, String from, String to) {
        Map<String, String> params = buildParams(query, from, to);
        return HttpGet.get(TRANS_API_HOST, params);
    }

    private Map<String, String> buildParams(String query, String from, String to) {
        Map<String, String> params = new HashMap<String, String>();

        params.put("q", query);
        params.put("from", from);
        params.put("to", to);

        params.put("appid", appid);

        // 随机数
        String salt = String.valueOf(System.currentTimeMillis());
        params.put("salt", salt);

        // 签名
        String src = appid + query + salt + securityKey; // 加密前的原文
        params.put("sign", MD5.md5(src));
        return params;
    }


    public static String parseJson(String json){
        JsonElement element =new JsonParser().parse(json);
        JsonObject obj = element.getAsJsonObject();
        JsonArray array = obj.getAsJsonArray("trans_result");
        int size = 0;
        if (array != null) {
            size = array.size();
        }

        String res = "";
        String temp = "";
        for(int i = 0;i < size;i++){
            temp = array.get(i).getAsJsonObject().get("dst").toString();
            int len = temp.length();
            temp = temp.substring(1,len - 1);
            res += temp + "\n";
        }

        return res;

    }

}
