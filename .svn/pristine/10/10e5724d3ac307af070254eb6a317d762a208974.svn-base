package com.deal.controller.syncboss;

import com.deal.entity.syncboss.RequestDTO;
import com.deal.util.ExecutorPool;
import com.deal.util.JsonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;

@Controller
public class SyncBossController{
    private static Logger logger = LoggerFactory.getLogger(SyncBossController.class);

    @Autowired
    public SyncBossRunnable syncBossService;

    private JsonMapper jsonMapper = new JsonMapper();

    @RequestMapping(value = "/syncboss", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<String> syncData(@RequestBody RequestDTO json) throws ParseException{
        logger.info("syncData json:" + json);
        //RequestDTO input = jsonMapper.fromJson(json.toString(),RequestDTO.class);
        syncBossService.setRequestDTO(json);
        ExecutorPool.getInstance().submit((Runnable) syncBossService);
        //返回结果
        logger.info("send result: " + HttpStatus.OK);
        return new ResponseEntity<String>("received", HttpStatus.OK);
    }
}
