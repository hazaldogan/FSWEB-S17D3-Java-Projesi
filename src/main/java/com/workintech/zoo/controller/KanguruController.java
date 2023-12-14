package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Kanguru;
import com.workintech.zoo.validation.ZooKanguruValidation;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/kangurus")
public class KanguruController {
    private Map<Integer, Kanguru> kangurus;

    @PostConstruct
    public void init(){
        kangurus = new HashMap<>();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Kanguru> findAll(){
        return this.kangurus.values().stream().toList();
    }

    @GetMapping("/{id}")
    public Kanguru find(@PathVariable("id") Integer id){
        ZooKanguruValidation.isIdValid(id);
        ZooKanguruValidation.checkKanguruExistence(kangurus,id,true);
        return kangurus.get(id);
    }

    @PostMapping
    public Kanguru save(@RequestBody Kanguru kanguru){
        ZooKanguruValidation.checkKanguruExistence(kangurus,kanguru.getId(),false);
        ZooKanguruValidation.checkKanguruWeight(kanguru.getWeight());
        kangurus.put(kanguru.getId(),kanguru);
        return kangurus.get(kanguru.getId());
    }

    @PutMapping("/{id}")
    public  Kanguru update(@PathVariable("id") Integer id, @RequestBody Kanguru Kanguru){
        ZooKanguruValidation.isIdValid(id);
        ZooKanguruValidation.checkKanguruWeight(Kanguru.getWeight());
        Kanguru.setId(id);
        if(kangurus.containsKey(id)){
            kangurus.put(id,Kanguru);
            return kangurus.get(id);
        }
        else {
            return save(Kanguru);
        }

    }
    @DeleteMapping("/{id}")
    public  Kanguru delete(@PathVariable("id") Integer id){
        ZooKanguruValidation.isIdValid(id);
        ZooKanguruValidation.checkKanguruExistence(kangurus,id,true);
        return kangurus.remove(id);
    }
}
