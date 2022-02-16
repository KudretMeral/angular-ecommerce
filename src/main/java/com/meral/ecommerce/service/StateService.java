package com.meral.ecommerce.service;


import com.meral.ecommerce.model.State;
import com.meral.ecommerce.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateService {

    @Autowired
    StateRepository stateRepository;



    public List<State> getAllState()
    {
        return stateRepository.findAll();
    }

}
