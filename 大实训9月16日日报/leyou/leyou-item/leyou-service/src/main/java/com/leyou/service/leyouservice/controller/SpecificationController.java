package com.leyou.service.leyouservice.controller;

import com.leyou.item.pojo.SpecGroup;
import com.leyou.service.leyouservice.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("spec")
public class SpecificationController {
    @Autowired
    private SpecificationService specificationService;


    @GetMapping("groups/{cid}")
    public ResponseEntity<List<SpecGroup>> queryGroupsByCid(@PathVariable("cid")Long cid){
        List<SpecGroup> groups = this.specificationService.queryGroupsByCid(cid);
        if (CollectionUtils.isEmpty(groups)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(groups);
    }
    @PostMapping("group")
    public void  addGroup(@RequestBody SpecGroup specGroup){

            specificationService.addGroup(specGroup);

    }
@PutMapping("group")
    public void update(@RequestBody SpecGroup specGroup){

    specificationService.updateGroup(specGroup);
}

    @DeleteMapping("group/{id}")
    public void  deleteGroup(@PathVariable("id")Long id){

        specificationService.deleteGroup(id);
    }


}
