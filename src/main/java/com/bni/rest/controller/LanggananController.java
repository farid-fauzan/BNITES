package com.bni.rest.controller;

import com.bni.rest.pojo.LanggananPojo;
import com.bni.rest.pojo.LatihanPojo;
import com.bni.rest.pojo.LayananPojo;
import com.bni.rest.services.LanggananServices;
import com.bni.rest.services.MenuServices;
import com.bni.rest.utility.MessageModel;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/langganan")
public class LanggananController {

    @Autowired
    private LanggananServices langgananServices;

    @RequestMapping(value = "/add-langganan", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "Authorization",
                    value = "Authorization token",
                    required = true,
                    dataType = "string",
                    paramType = "header") })
    public ResponseEntity<MessageModel> saveLangganan(@Valid @RequestBody LanggananPojo langgananPojo){

        return langgananServices.addLangganan(langgananPojo);
    }

    @RequestMapping(value = "/batal-langganan", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "Authorization",
                    value = "Authorization token",
                    required = true,
                    dataType = "string",
                    paramType = "header") })
    public ResponseEntity<MessageModel> batalLangganan(@RequestParam Long idLangganan){

        return langgananServices.batalkan(idLangganan);
    }

    @RequestMapping(value = "/tambah-durasi-langganan", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "Authorization",
                    value = "Authorization token",
                    required = true,
                    dataType = "string",
                    paramType = "header") })
    public ResponseEntity<MessageModel> addDurasi(@RequestParam Long idLangganan, Integer durasi){

        return langgananServices.addDurasi(idLangganan, durasi);
    }



}
