package org.acme.service;

import org.acme.model.ItemEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ItemService {

    public List<ItemEntity> get(){
        List<ItemEntity> listAll = ItemEntity.findAll().list();
        return listAll.stream().map(ie -> {
                    return new ItemEntity(ie.id,ie.name,ie.count,ie.status);
                }
        ).collect(Collectors.toList());
    }

    @Transactional
    public void create(ItemEntity itemEntity){
        ItemEntity ie = new ItemEntity();
        ie.name = itemEntity.name;
        ie.count = itemEntity.count;
        ie.status = itemEntity.status;
        System.out.println("-------"+itemEntity);
        ie.persist();
    }

    @Transactional
    public void update(ItemEntity itemEntity){
        ItemEntity item = ItemEntity.findById(itemEntity.getId());
        item.name = itemEntity.getName();
        item.count = itemEntity.getCount();
        item.status = itemEntity.getStatus();
        System.out.println("xxxxxxx "+itemEntity.toString());
    }

    @Transactional
    public void delete(Long id){
        ItemEntity.deleteById(id);
    }
}
