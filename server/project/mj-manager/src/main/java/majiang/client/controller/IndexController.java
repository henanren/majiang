package majiang.client.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zuoge85@gmail.com on 2016/12/6.
 */
@Controller
public class IndexController {
    @Value("${client.apiAddress}")
    private String apiAddress;

    @Value("${client.apiVersion}")
    private String apiVersion;

    @Value("${client.version}")
    private String version;

    @GetMapping({"*.html","**.html", "/index.html", "/"})
    private ModelAndView index(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws Exception {
        Map<String, Object> model = new HashMap<>();
        model.put("apiAddress", apiAddress);
        model.put("apiVersion", apiVersion);
        model.put("version", version);
        return new ModelAndView("index", model);
    }
}