package com.example.tacocloud.Controller;

import com.example.tacocloud.Model.Taco;
import com.example.tacocloud.Model.TacoResource;
import com.example.tacocloud.Model.TacoResourceAssembler;
import com.example.tacocloud.Repository.TacoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
@RepositoryRestController
public class RecentTacosController {
    @Autowired
    private TacoRepository tacoRepo;
    @Bean
    public RepresentationModelProcessor<PagedModel<EntityModel<Taco>>> tacoProcessor(EntityLinks links){
        return new RepresentationModelProcessor<PagedModel<EntityModel<Taco>>>(){
            @Override
            public PagedModel<EntityModel<Taco>> process(PagedModel<EntityModel<Taco>> resource){
                resource.add(links.linkFor(Taco.class).slash("recent").withRel("recents"));
                return resource;
            }
        };
    }
    @GetMapping(path = "/tacos/recent",produces = "application/hal+json")
    public ResponseEntity<CollectionModel<TacoResource>> recentTacos(){
        PageRequest page=PageRequest.of(0,12, Sort.by("createdAt").descending());
        List<Taco> tacos=tacoRepo.findAll(page).getContent();   //taco collection find
        List<TacoResource> tacoResources= (List<TacoResource>) new TacoResourceAssembler().toCollectionModel(tacos);    //convert to TacoResources
        CollectionModel<TacoResource> recentResources=CollectionModel.of(tacoResources);
        recentResources.add(linkTo(methodOn(RecentTacosController.class).recentTacos()).withRel("recents"));
        return new ResponseEntity<>(recentResources, HttpStatus.OK);
    }
}
