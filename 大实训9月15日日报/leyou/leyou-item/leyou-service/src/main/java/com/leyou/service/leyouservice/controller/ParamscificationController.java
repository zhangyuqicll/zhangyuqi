package com.leyou.service.leyouservice.controller;

import com.leyou.item.pojo.SpecParam;
import com.leyou.service.leyouservice.service.ParamscificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("spec")
public class ParamscificationController {
    @Autowired
    private ParamscificationService paramscificationService;
    @GetMapping("params")
    public ResponseEntity<List<SpecParam>> queryParams(@RequestParam("gid")Long gid) {
        List<SpecParam> params = this.paramscificationService.queryParams(gid);
        if (CollectionUtils.isEmpty(params)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(params);
    }
    @PostMapping("param")
    public void addParam(@RequestBody SpecParam specParam){
        paramscificationService.addParam(specParam);
    }
    @DeleteMapping("param/{id}")
    public void deleteParam(@PathVariable("id") Long id){
        paramscificationService.deleteParam(id);
    }
    @PutMapping("param")
    public void  update(@RequestBody SpecParam specParam){
        paramscificationService.update(specParam);
    }

}
