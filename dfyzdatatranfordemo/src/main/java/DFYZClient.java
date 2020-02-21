import com.alibaba.fastjson.JSON;
import nao.CommonResponse;
import nao.LocationInfoMsg;
import org.apache.commons.lang3.StringUtils;
import util.OkHttpClientUtil;

import java.security.InvalidParameterException;
import java.util.List;

public class DFYZClient {
    private static final String a = "EastPostClient";
    private static final String b = "POST";
    private static final String c = "http";
    private static final String d = "https";
    private static final String e = "/gps/queryList";
    private String l;
    private String m = "https";
    private String key;

    public DFYZClient(String endPoint, String project, String privateKey) throws Exception {
        this.l = endPoint;
        if(StringUtils.isNotBlank(privateKey)) this.key=privateKey;
        if (!this.l.startsWith("http")) {
            throw new InvalidParameterException("endpoint invalid!");
        } else if (!this.l.startsWith("https")) {
            this.m = "http";

        }
    }

    public CommonResponse queryList(String var1, String var2) {
        String var3 = this.l + e;
        try {
            String s = OkHttpClientUtil.getInstance().postJson(var3, var2,this.key);
            List<LocationInfoMsg> locationInfoMsgs = JSON.parseArray(s, LocationInfoMsg.class);
            CommonResponse commonResponse = new CommonResponse(locationInfoMsgs);
            return commonResponse;

        } catch (Exception var8) {
            var8.printStackTrace();
        }
        return null;
    }
}
