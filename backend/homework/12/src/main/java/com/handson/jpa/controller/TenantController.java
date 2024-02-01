package com.handson.jpa.controller;

import com.handson.jpa.entity.Tenant;
import com.handson.jpa.service.TenantServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tenant")
public class TenantController {

    @Autowired
    private TenantServices tenantService;

    @PostMapping("/add")
    public ResponseEntity<String> addTenant(@RequestBody Tenant tenant) {
        tenantService.addTenant(tenant);
        return new ResponseEntity<>("Tenant added successfully", HttpStatus.OK);
    }

}
