package controllers;

import constants.ApiConstants;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubnetDataController {

    @GetMapping("/api/v1/sub/")
    public String getSubnetData(
            @RequestParam(value = "ip" ) String ip,
            @RequestParam(value = "suffix", required = false) String suffix
    ) {
        return ip + suffix;
    }
}
