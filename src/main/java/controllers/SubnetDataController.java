package main.java.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import main.java.dto.IpAddressResponse;
import main.java.services.SubnetCalculatorService;

@RestController
@CrossOrigin
public class SubnetDataController {

    private SubnetCalculatorService subnetCalculatorService;

    @Autowired
    public SubnetDataController(SubnetCalculatorService subnetCalculatorService) {
        this.subnetCalculatorService = subnetCalculatorService;
    }

    //http://localhost:8080/api/subnet?ip=200.120.23.23?suffix=22
    @GetMapping(value = "/api/subnet", produces = "application/json")
    public IpAddressResponse getSubnetData(
            @RequestParam(value = "ip") String ip,
            @RequestParam(value = "suffix", required = false) int suffix
    ) {
        return this.subnetCalculatorService.buildIpList(ip, suffix);
    }
}
