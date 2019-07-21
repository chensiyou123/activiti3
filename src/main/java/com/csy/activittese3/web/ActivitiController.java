package com.csy.activittese3.web;

import com.csy.activittese3.common.RestServiceController;
import com.csy.activittese3.utils.ToWeb;
import com.csy.activittese3.vo.ReProcdef;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Api(value="流程controller",description="流程操作",tags={"流程操作接口"})
@RestController
@RequestMapping("prof")
public class ActivitiController implements RestServiceController<ProcessDefinition, String> {
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

    @Override
    public Object getOne(String s) {
        return null;
    }

    @ApiOperation("分页查询流程定义")
    @Override
    public Object getList(Integer rowSize, Integer page) {
        List<ProcessDefinition> processDefinitions = repositoryService.createProcessDefinitionQuery().listPage(rowSize * (page - 1), rowSize);
        long count = repositoryService.createProcessDefinitionQuery().count();
        List<ReProcdef> list = new ArrayList<>();
        for (ProcessDefinition processDefinition : processDefinitions) {
            ReProcdef reProcdef = new ReProcdef(processDefinition);
            list.add(reProcdef);
        }
        return ToWeb.buildResult().setRows(
                ToWeb.Rows.buildRows()
                        .setRowSize(rowSize)
                        .setTotalPages((int) (count / rowSize + 1))
                        .setTotalRows(count)
                        .setList(list)
                        .setCurrent(page)
        );
    }

    @Override
    public Object postOne(ProcessDefinition entity) {
        return null;
    }

    @Override
    public Object putOne(String s, ProcessDefinition entity) {
        return null;
    }

    @Override
    public Object patchOne(String s, ProcessDefinition entity) {
        return null;
    }

    @Override
    public Object deleteOne(String s) {
        return null;
    }
}
