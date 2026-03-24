package exercice.tdse.spring.Controller;

import exercice.tdse.spring.Service.HttpConnection;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController()
public class controller {
    private final HttpConnection httpConnection;

    public controller(HttpConnection httpconnection) {
        this.httpConnection = httpconnection;
    }

    @GetMapping("/proxy/**")
    public String proxy(HttpServletRequest request) throws Exception {
        System.out.println("llego");
        return  httpConnection.pathfix(request);
    }
}
