import com.alibaba.fastjson.JSONObject;
import nao.CommonResponse;

public class Main {
    public static void main(String[] args) throws Exception {
        String url = "http://localhost:8080/";
        JSONObject json = new JSONObject();
        json.put("plateNumber", "粤AD917挂");
        json.put("password", "123456");
        json.put("client_secret", "secret");

//        String privateKey="MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJHwsX7aEjcrmvYWF0kaRbCRRV3U1909eZEi+g9ETItkXwtUjkmU4AA9LFOjZsT5qGL/QDqJna8H45WykbToIX/Ruxg5njbVmLIImvwqTQ2l6FW8sl5pjUIpS69TRYnkVe+81E6yGHUpxf827UBli6M+njnW23UZCf7LrDW8n7FNAgMBAAECgYBF+5jyDpq7kBKAPXmvGYUftZoA8+cL9cXM/mvRVFwsX9Zf9Y1B5b1BABS4itvmSx4fUu225HUUEQFmsLnEeahODEVjJqezIwDi0TcWxDYDHD/yBfTVTmnYcWQ9fhWqTJg34uuGHTIsC7QX5xM5vRHzHJ5DL3XU/QvBhhgNICTt9QJBAM2rDJlutEGnG67V5rO4sn8Ly3bZzX/ox6Cx0jK5/kH/pHfVy519/a5YDxWRPoJwKbUbCu+mtyZoEL+8Efp90pcCQQC1p7v1d0CbY4qN/FAcaBjTeMq1GFzAsSefgoCcp3dho3CHDcTaOlGz4Q78R0T/nLtXy5GmSIp5nqR3Xn9+1Ku7AkBJ96SWYfwuSH1qSb+pD1oS2xkX64k+4Wc6YfmpCL9q2Rg7iQar464iz8TNCumiW9iEK++o9RqVk4PCkU8d+baNAkEArxu05UeCgQfwfTRW6Tszi07qYK3OY3WiU3ISlnAcdYN+qsGMHC2RqsttjaUKDzk/or86dMeCrQBPXD48ur85lwJAOMeyZ0eAxnIWx2EZTXPttH94dSLDUtT4HuZabFiE+kD0ZMll2cqhjMZ2vr4Q/BR5jnyvXx43FpTFoCiFAEJ88Q==";
String privateKey="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCR8LF+2hI3K5r2FhdJGkWwkUVd1NfdPXmRIvoPREyLZF8LVI5JlOAAPSxTo2bE+ahi/0A6iZ2vB+OVspG06CF/0bsYOZ421ZiyCJr8Kk0NpehVvLJeaY1CKUuvU0WJ5FXvvNROshh1KcX/Nu1AZYujPp451tt1GQn+y6w1vJ+xTQIDAQAB";
        DFYZClient client = new DFYZClient(url,"dfyz_project",privateKey);
        CommonResponse commonResponse = client.queryList(url, json.toJSONString());
        System.out.println(commonResponse);

    }
}
