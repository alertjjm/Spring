package com.example.tacocloud.Model;

import com.example.tacocloud.Controller.RecentTacosController;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

public class TacoResourceAssembler extends RepresentationModelAssemblerSupport<Taco, TacoResource> {
    public TacoResourceAssembler() {
        super(RecentTacosController.class, TacoResource.class);
    }
    @Override
    public TacoResource toModel(Taco entity) {
        return createModelWithId(entity.getId(),entity);
    }

    @Override
    protected TacoResource instantiateModel(Taco entity) {
        return new TacoResource(entity);
    }
}
