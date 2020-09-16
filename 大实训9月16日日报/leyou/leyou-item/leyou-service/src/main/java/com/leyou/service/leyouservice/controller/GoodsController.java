package com.leyou.service.leyouservice.controller;

import com.leyou.common.pojo.PageResult;
import com.leyou.item.bo.SpuBo;
import com.leyou.item.pojo.Sku;
import com.leyou.item.pojo.SpuDetail;
import com.leyou.service.leyouservice.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class GoodsController {
    @Autowired
    private GoodsService goodsService;
    @GetMapping("spu/page")
    public ResponseEntity<PageResult<SpuBo> >querySpuBoByPage(
            @RequestParam(value = "key", required = false)String key,
            @RequestParam(value = "saleable", required = false)Boolean saleable,
            @RequestParam(value = "page", defaultValue = "1")Integer page,
            @RequestParam(value = "rows", defaultValue = "5")Integer rows
    ){
        PageResult<SpuBo> pageResult =goodsService.querySpuBoByPage(key,saleable,page,rows);
        if(CollectionUtils.isEmpty(pageResult.getItems())){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pageResult);
    }
    @PostMapping("goods")
    public ResponseEntity<Void> saveGoods(@RequestBody SpuBo spuBo){
        this.goodsService.saveGoods(spuBo);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping("spu/detail/{spuId}")
    public ResponseEntity<SpuDetail> querySpuDetailBySpuId(@PathVariable("spuId")Long spuId){
           SpuDetail spuDetail= this.goodsService.querySpuDetailBySpuId(spuId);
           if(spuDetail==null){
               return  ResponseEntity.notFound().build();
           }
           return  ResponseEntity.ok(spuDetail);
    }
    @GetMapping("sku/list")
    public ResponseEntity<List<Sku>> querySkusBySpuId(@RequestParam("id")Long SpuId){
    List<Sku> skus=this.goodsService.querySkusBySpuId(SpuId);
        if(CollectionUtils.isEmpty(skus)){
            return ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(skus);

    }
    @PutMapping("goods")
    public  ResponseEntity<Void> updateGoods(@RequestBody SpuBo spuBo){
        this.goodsService.updateGoods(spuBo);
        return  ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }



}
