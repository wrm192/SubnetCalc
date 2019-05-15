package calculator.controllers;


import calculator.dto.IpAddressResponse;
import calculator.services.SubnetCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
