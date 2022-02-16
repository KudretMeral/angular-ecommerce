package com.meral.ecommerce.service;


import com.meral.ecommerce.model.Country;
import com.meral.ecommerce.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    @Autowired
    CountryRepository countryRepository;

    public List<Country> getAllCountries()
    {
        return countryRepository.findAll();
    }

}
