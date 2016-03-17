/*
 * 文件名：HttpUtils.java
 * 版权：Copyright by www.cfilmcloud.com
 * 描述：
 * 修改人：mengxy[孟祥元]
 * 修改时间：2015年12月16日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package auto.core.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class HttpUtils {

    private static final String USER_AGENT = "Mozilla/5.0";

    /**
     * Description: 发送GET请求<br>
     * 1、…<br>
     * 2、…<br>
     * Implement: <br>
     * 1、…<br>
     * 2、…<br>
     * 
     * @param url
     * @return
     * @throws Exception 
     * @see 
     */
    public static String sendGet(String url) {
        StringBuffer response = new StringBuffer();
        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // optional default is GET
            con.setRequestMethod("GET");

            //add request header
            con.setRequestProperty("User-Agent", USER_AGENT);

            int responseCode = con.getResponseCode();
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
        } catch (Exception e) {
            throw new RuntimeException("请求失败:"+e.getMessage());
        }
        return response.toString();
    }

    /**
     * Description: 发送POST请求<br>
     * 1、…<br>
     * 2、…<br>
     * Implement: <br>
     * 1、…<br>
     * 2、…<br>
     * 
     * @param url
     * @param urlParameters
     * @return
     * @throws Exception 
     * @see 
     */
    public static <T extends Object> String sendPost(String url, T t) throws Exception {
        StringBuffer response = new StringBuffer();
        try {
            URL obj = new URL(url);
            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

            //add reuqest header
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", USER_AGENT);
            con.setRequestProperty("Cache-Control", "no-cache");
            con.setRequestProperty("Content-Type", "application/json");

            String urlParameters = "";
            if (t instanceof String) {
                urlParameters = (String) t;
            } else {
                urlParameters = JsonUtils.getInstance().toJson(t);
            }

            // Send post request
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
        } catch (Exception e) {
            throw new RuntimeException("请求失败:"+e.getMessage());
        }
        return response.toString();
    }
}
