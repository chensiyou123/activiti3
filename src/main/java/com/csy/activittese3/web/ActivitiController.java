package com.csy.activittese3.web;

import com.csy.activittese3.utils.ToWeb;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value="流程定义controller",description="流程定义操作",tags={"流程定义操作接口"})
@RestController
@RequestMapping("prof")
public class ActivitiController {
    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    @ApiOperation("根据process文件部署流程")
    @GetMapping("deployment")
    public Object deployment (@RequestParam("process")String process) {
        Deployment deployment = repositoryService.createDeployment().addClasspathResource(process+".bpmn").deploy();
        return ToWeb.buildResult().setObjData(deployment);
    }
    @ApiOperation("启动一个流程")
    @GetMapping("start")
    public Object start(@RequestParam("key")String key) {
        ProcessInstance processInstance= runtimeService.startProcessInstanceByKey(key);
        return ToWeb.buildResult().setObjData(processInstance);
    }


    @ApiOperation(value = "根据deploymentID删除定义的流程")
    public Object deleteOne(@PathVariable("id")String id) {
        //根据deploymentID删除定义的流程，普通删除
        repositoryService.deleteDeployment(id);
        System.out.println("普通删除--流程定义删除成功");
        return ToWeb.buildResult().msg("删除成功");
        //强制删除
//        repositoryService.deleteDeployment(id, true);
//        System.out.println("强制删除--流程定义删除成功");
    }
}
