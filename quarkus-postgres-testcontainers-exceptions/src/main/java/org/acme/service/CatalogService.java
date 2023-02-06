package org.acme.service;

import io.quarkus.panache.common.Sort;
import org.acme.exception.ResourceNotFoundException;
import org.acme.model.CatalogItem;
import org.acme.repository.CatalogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@ApplicationScoped
public class CatalogService {
    private Logger logger = LoggerFactory.getLogger(CatalogService.class);

    @Inject
    CatalogRepository catalogRepository;

    //getCatalogItemBySku
    private CatalogItem getCatalogItemBySku(String sku) throws ResourceNotFoundException {
        CatalogItem catalogItem = catalogRepository.findBySku(sku)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("catalog item is not found for sku : %s",sku)
                ));
        return catalogItem;
    }

    //getCatalogItems
    public List<CatalogItem> getCatalogItems(){
        Sort sort = Sort.ascending("name");
        return catalogRepository.listAll(sort);
    }

    //getCatalogItem
    public CatalogItem getCatalogItem(String skuNumber) throws ResourceNotFoundException {
        return getCatalogItemBySku(skuNumber);
    }

    //addCatalogItem
    @Transactional
    public Long addCatalogItem(CatalogItem catalogItem){
        catalogItem.setCreatedOn(new Date());
        catalogRepository.persist(catalogItem);
        return catalogItem.getId();
    }

    //deleteCatalogItem
    @Transactional
    public void deleteCatalogItem(String skuNumber) throws ResourceNotFoundException {
        CatalogItem catalogItem = getCatalogItemBySku(skuNumber);
        catalogRepository.delete(catalogItem);
        catalogRepository.flush();

    }

    //updateCatalogItem
    @Transactional
    public void updateCatalogItem(CatalogItem catalogItem) throws ResourceNotFoundException {
        CatalogItem catalogItemDB = getCatalogItemBySku(catalogItem.getSku());
        boolean priceDifference = catalogItemDB.getPrice() != catalogItem.getPrice();

        catalogItemDB.setUpdatedOn(new Date());
        catalogItemDB.setName(catalogItem.getName());
        catalogItemDB.setDescription(catalogItem.getDescription());
        catalogItemDB.setPrice(catalogItem.getPrice());
        catalogItemDB.setInventory(catalogItem.getInventory());

        if (priceDifference){
            //invoked product price event
        }
        catalogRepository.persist(catalogItemDB);
    }
}
