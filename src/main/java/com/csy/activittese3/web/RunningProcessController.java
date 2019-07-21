package com.csy.activittese3.web;

import com.csy.activittese3.common.RestServiceController;
import com.csy.activittese3.utils.ToWeb;
import com.csy.activittese3.vo.RuExecution;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value="流程实列controller",description="流程实列操作",tags={"流程实列操作接口"})

@RestController
@RequestMapping("runs")
public class RunningProcessController {
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private IdentityService identityService;


    @ApiOperation("启动一个流程")
    @GetMapping(value = "{state}/{processInstanceId}")
    public Object updateState(@PathVariable("state") String state, @PathVariable("processInstanceId") String processInstanceId) {
        String result="";
      //  ProcessInstance processInstance =repositoryService.createModelQuery().
        if (state.equals("active")) {
            runtimeService.activateProcessInstanceById(processInstanceId);
            result="已激活ID为[" + processInstanceId + "]的流程实例。";
        } else if (state.equals("suspend")) {
            runtimeService.suspendProcessInstanceById(processInstanceId);
            result="已挂起ID为[" + processInstanceId + "]的流程实例。";
        }
        return ToWeb.buildResult().msg(result);
    }

    }
