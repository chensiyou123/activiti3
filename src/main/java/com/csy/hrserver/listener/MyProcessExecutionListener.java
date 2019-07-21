package com.csy.hrserver.listener;

import ch.qos.logback.classic.Logger;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 *
 * 流程实例监听类
 *
 * @auther: Ace Lee
 * @date: 2019/3/8 11:57
 */
@Component
@Slf4j
public class MyProcessExecutionListener implements ExecutionListener {

    private org.slf4j.Logger logger = LoggerFactory.getLogger(MyProcessExecutionListener.class);
    @Override
    public void notify(DelegateExecution execution) throws Exception {
        String eventName = execution.getEventName();
        //start
        if ("start".equals(eventName)) {
            logger.info("==================start==================");
        }else if ("end".equals(eventName)) {
            logger.info("==================end==================");
        }
    }
}

