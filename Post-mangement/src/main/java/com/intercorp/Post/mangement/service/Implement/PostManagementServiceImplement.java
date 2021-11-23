package com.intercorp.Post.mangement.service.Implement;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.intercorp.Post.mangement.common.Common;
import com.intercorp.Post.mangement.dto.ClienteDTO;
import com.intercorp.Post.mangement.dto.ClientesfpmDTO;
import com.intercorp.Post.mangement.dto.PostDTO;
import com.intercorp.Post.mangement.dto.kpiClienteDTO;
import com.intercorp.Post.mangement.firebase.FirebaseInitializer;
import com.intercorp.Post.mangement.service.PostManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.channels.ScatteringByteChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class PostManagementServiceImplement  implements PostManagementService {

    @Autowired
    private FirebaseInitializer firebase;



    @Override
    public List<PostDTO> list() {
        List<PostDTO> response = new ArrayList<>();
        PostDTO post;
        ApiFuture<QuerySnapshot> querySnapshotApiFuture= getCollection().get();
        try {
            for (DocumentSnapshot doc: querySnapshotApiFuture.get().getDocuments()) {
                post = doc.toObject(PostDTO.class);
                post.setId(doc.getId());
                response.add(post);
        }
            return  response;
        }catch (Exception e){
            return  null;
        }
    }

    @Override
    public List<ClienteDTO> listclientes() {

        List<ClienteDTO> response = new ArrayList<>();
        ClienteDTO cliente;
        ApiFuture<QuerySnapshot> querySnapshotApiFuture= getCollectionCliente().get();
        try {
            for (DocumentSnapshot doc: querySnapshotApiFuture.get().getDocuments()) {
                cliente = doc.toObject(ClienteDTO.class);
                cliente.setId(doc.getId());
                response.add(cliente);
            }
            return  response;
        }catch (Exception e){
            return  null;
        }

    }

    @Override
    public List<ClientesfpmDTO> fechaProbableDeMuerteDeClientes() {
        List<ClientesfpmDTO> clientesFechaProblableMuerte = new ArrayList<>();
        ClientesfpmDTO clientesfpmDTO ;
        ClienteDTO cliente;

        ApiFuture<QuerySnapshot> querySnapshotApiFuture= getCollectionCliente().get();
        try {
            for (DocumentSnapshot doc: querySnapshotApiFuture.get().getDocuments()) {
                cliente =  doc.toObject(ClienteDTO.class);
                cliente.setId(doc.getId());

                clientesfpmDTO = new ClientesfpmDTO();
                clientesfpmDTO.setId(cliente.getId());
                clientesfpmDTO.setNombre(cliente.getNombre());
                clientesfpmDTO.setApellido(cliente.getApellido());
                clientesfpmDTO.setEdad(cliente.getEdad());
                clientesfpmDTO.setFechaNacimiento(cliente.getFechaNacimiento());
                clientesfpmDTO.setFechaProbableDeMuerte(Common.fechaProbableDeMuerte());

                clientesFechaProblableMuerte.add(clientesfpmDTO);
            }
            return  clientesFechaProblableMuerte;
        }catch (Exception e){
            return  null;
        }

    }

    @Override
    public Boolean add(PostDTO post) {
        Map<String, Object> docData = getDocData(post);
        //CollectionReference posts = getCollection();
        ApiFuture<WriteResult> writeResultApiFuture = getCollection().document().create(docData);
        try {
            if(null != writeResultApiFuture.get()) {
                return Boolean.TRUE;
            }
            return  Boolean.FALSE;
        }catch (Exception e){
            return Boolean.FALSE;
        }
    }

    @Override
    public Boolean creacliente(ClienteDTO cliente) {
        Map<String,Object> clienteData= getClienteData(cliente);
        ApiFuture<WriteResult> writeResultApiFuture =getCollectionCliente().document().create(clienteData);
        try {
            if(null != writeResultApiFuture.get()) {
                return Boolean.TRUE;
            }
            return  Boolean.FALSE;
        }catch (Exception e){
            return Boolean.FALSE;
        }
    }

    @Override
    public Boolean edit(String id, PostDTO post) {
        Map<String, Object> docData = getDocData(post);
        ApiFuture<WriteResult> writeResultApiFuture = getCollection().document(id).set(docData);
        try {
            if(null != writeResultApiFuture.get()) {
                return Boolean.TRUE;
            }
            return  Boolean.FALSE;
        }catch (Exception e){
            return Boolean.FALSE;
        }
    }

    @Override
    public Boolean delete(String id) {
        ApiFuture<WriteResult> writeResultApiFuture = getCollection().document(id).delete();
        try {
            if(null != writeResultApiFuture.get()) {
                return Boolean.TRUE;
            }
            return  Boolean.FALSE;
        }catch (Exception e){
            return Boolean.FALSE;
        }
    }


    private CollectionReference getCollection() {
        return firebase.getFirestore().collection("post");
    }
    private CollectionReference getCollectionCliente() {
        return firebase.getFirestore().collection("cliente");
    }
    private Map<String, Object> getDocData(PostDTO post) {
        Map<String, Object> docData= new HashMap<>();
        docData.put("title", post.getTitle());
        docData.put("content", post.getContent());
        return docData;
    }
    private Map<String, Object> getClienteData(ClienteDTO cliente){
        Map<String,Object> clienteData = new HashMap<>();
        clienteData.put("nombre",cliente.getNombre());
        clienteData.put("apellido",cliente.getApellido());
        clienteData.put("edad",cliente.getEdad());
        clienteData.put("fechaNacimiento",cliente.getFechaNacimiento());
        return clienteData;
    }
    @Override
    public kpiClienteDTO kpiDeClientes() {
        List<Integer> edades = listclientes().stream().map(ClienteDTO::getEdad).collect(Collectors.toList());
        double promedio = Common.promedioEdades(edades.stream().mapToInt(x->x).toArray());
        double desviacionEstandar = Common.desEstandarEdades(edades.stream().mapToInt(x->x).toArray());
        kpiClienteDTO kpiDeCliente = new kpiClienteDTO();
        kpiDeCliente.setPromedioEdades(promedio);
        kpiDeCliente.setDesEstandarEdades(desviacionEstandar);
        return kpiDeCliente;
    }



}


