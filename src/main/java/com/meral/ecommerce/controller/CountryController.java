package com.meral.ecommerce.controller;


import com.meral.ecommerce.model.Country;
import com.meral.ecommerce.service.CountryService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/country")
public class CountryController {

    @Autowired
    CountryService countryService;

    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
    public JSONObject getAllCountries()
    {
        JSONObject jo = new JSONObject();

        List<Country> countryList=countryService.getAllCountries();

        jo.put("countries",countryList);
        return jo;
    }

}
