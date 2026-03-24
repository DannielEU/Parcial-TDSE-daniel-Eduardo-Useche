package exercice.tdse.spring.Controller;

import exercice.tdse.spring.Service.service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController()
public class controller {
    private final service serviceMath;

    public controller(service serviceMath) {
        this.serviceMath = serviceMath;
    }

    @GetMapping("/linearSearch")
    public String linearSearch(@RequestParam Map<String,String> params){
        return serviceMath.linearSearch(params);
    }
}
