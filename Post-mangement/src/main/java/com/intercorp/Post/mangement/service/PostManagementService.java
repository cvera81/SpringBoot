package com.intercorp.Post.mangement.service;

import com.intercorp.Post.mangement.dto.PostDTO;
import com.intercorp.Post.mangement.dto.ClienteDTO;
import com.intercorp.Post.mangement.dto.kpiClienteDTO;
import com.intercorp.Post.mangement.dto.ClientesfpmDTO;

import java.util.List;

public interface PostManagementService {

    List<PostDTO> list();
    List<ClienteDTO> listclientes();
    Boolean add(PostDTO post);
    Boolean creacliente(ClienteDTO cliente);
    Boolean edit(String id ,PostDTO post);
    Boolean delete(String id);
    kpiClienteDTO kpiDeClientes();
    List<ClientesfpmDTO> fechaProbableDeMuerteDeClientes();
}
