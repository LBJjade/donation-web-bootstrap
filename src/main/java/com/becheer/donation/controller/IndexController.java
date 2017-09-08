package com.becheer.donation.controller;

import com.becheer.donation.model.base.ResponseDto;
import com.becheer.donation.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 首页控制器
 */
@Controller
public class IndexController extends BaseController {

    @Resource
    private IProjectService projectService;

    /**
     * 首页
     * @return
     */
    @GetMapping(value = "/")
    public String index(HttpServletRequest request, @RequestParam(value = "limit", defaultValue = "12") int limit) {
        return this.render("index");
    }


    /**
     * 获取首页项目列表
     */
    @GetMapping(value = "project/{pageNum}/{pageSize}")
    @ResponseBody
    public ResponseDto GetProject(HttpServletRequest request,@PathVariable int pageNum, @PathVariable int pageSize) {
        if (pageNum<=0){
            pageNum=1;
        }
        if (pageSize>50||pageSize<=0){
            pageSize=10;
        }
        return ResponseDto.GetResponse(200,"success",projectService.getProjectList(pageNum,pageSize));
    }

}
