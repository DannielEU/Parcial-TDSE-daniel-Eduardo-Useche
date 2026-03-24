package exercice.tdse.spring.Service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service()
public class HttpConnection {

    private String USER_AGENT = "Mozilla/5.0";
    @Value("${enviroment.server1}")
    private String server1;
    @Value("${enviroment.server2}")
    private String server2;

    public String pathfix(HttpServletRequest request) throws Exception {

        String path = request.getRequestURI();
        String query = request.getQueryString();

        String url = path + (query != null ? "?" + query : "");
        url = url.replace("/proxy","");
        return serverchooser(url);
    }

    public String serverchooser(String method) throws Exception{
        try{
            return requestHttp(method, server1);
        } catch (Exception e) {
            return requestHttp(method, server2);
        }
    }

    public String requestHttp(String method,String SERVER) throws Exception {
        System.out.println(method);
        URL obj = new URL(SERVER+method);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            return response.toString();
        } else { throw new Exception("Server not response");
        }
    }

}