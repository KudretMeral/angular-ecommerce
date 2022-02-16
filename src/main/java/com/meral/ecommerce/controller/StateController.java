package com.meral.ecommerce.controller;


import com.meral.ecommerce.model.State;
import com.meral.ecommerce.service.StateService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/state")
public class StateController {

    @Autowired
    StateService stateService;

    @RequestMapping(value = "/get/{code}",method = RequestMethod.GET)
    public JSONObject getAllState(@PathVariable("code") String code)
    {
        JSONObject jo = new JSONObject();

        List<State> stateList=stateService.getAllState().stream()
                .filter(state -> state.getCountry().getCode().equals(code))
                .collect(Collectors.toList());

        jo.put("states",stateList);
        return jo;
    }

}
