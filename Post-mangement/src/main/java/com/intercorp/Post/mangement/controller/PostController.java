package com.intercorp.Post.mangement.controller;


import ch.qos.logback.core.net.server.Client;
import com.intercorp.Post.mangement.dto.PostDTO;
import  com.intercorp.Post.mangement.dto.ClienteDTO;
import com.intercorp.Post.mangement.service.PostManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/post")

public class PostController {
    @Autowired
    private PostManagementService service;

    /*
    @GetMapping(value = "/greet/{name}")
    public String greet(@PathVariable(value="name") String name ){
        return "Hello word : " + name;
    }

    @GetMapping(value = "/list")
    public ResponseEntity list(){
        return new ResponseEntity(service.list(), HttpStatus.OK);
    }


    @PostMapping(value = "/add")
    public ResponseEntity add(@RequestBody PostDTO post){
        return new ResponseEntity(service.add(post),HttpStatus.OK);
    }
*/

    @GetMapping(value = "/listclientes")
    public ResponseEntity listclientes() {
        return new ResponseEntity(service.fechaProbableDeMuerteDeClientes(), HttpStatus.OK);
        //return new ResponseEntity(service.listclientes(), HttpStatus.OK);
    }
    @PostMapping(value = "/creacliente")
    public ResponseEntity creacliente(@RequestBody ClienteDTO cliente){
        return  new ResponseEntity(service.creacliente(cliente),HttpStatus.OK);
    }
    /*
    @PutMapping(value="/{id}/update")
    public ResponseEntity edit(@PathVariable(value = "id") String id,@RequestBody PostDTO post){
        return new ResponseEntity(service.edit(id,post), HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}/delete")
    public ResponseEntity delete(@PathVariable(value = "id") String id){
        return  new ResponseEntity(service.delete(id),HttpStatus.OK);
    }
    */
    @GetMapping(value = "/kpideclientes")
    public ResponseEntity kpideclientes()
    {
        return new ResponseEntity(service.kpiDeClientes(),HttpStatus.OK);
    }


}
