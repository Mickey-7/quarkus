package org.acme.service;

import io.quarkus.panache.common.Sort;
import org.acme.exception.ResourceNotFoundException;
import org.acme.model.CatalogItem;
import org.acme.model.ProductPrice;
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


    //getAllCatalog
    public List<CatalogItem> getCatalogItems(){
        Sort sort = Sort.ascending("name");
        return catalogRepository.listAll(sort);
    }

    //getCatalogBySku
    public CatalogItem getCatalogBySku(String skuNumber) throws ResourceNotFoundException {
        CatalogItem catalogItem = catalogRepository.findBySku(skuNumber)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("catalog item not found for the sku : %s",skuNumber)
                ));
        return catalogItem;

    }

    //getCatalog
    public CatalogItem getCatalogItem(String skuNumber) throws ResourceNotFoundException {
        return getCatalogBySku(skuNumber);
    }

    //addCatalog
    @Transactional
    public Long addCatalogItem(CatalogItem catalogItem){
        catalogItem.setCreatedOn(new Date());
        logger.info("=== "+catalogItem.toString());
        catalogRepository.persist(catalogItem);
        return catalogItem.getId();
    }

    //deleteCatalog
    @Transactional
    public void deleteCatalog(String skuNumber) throws ResourceNotFoundException {
        CatalogItem catalogItem = getCatalogItem(skuNumber);
        catalogRepository.delete(catalogItem);
        catalogRepository.flush();
    }

    //updateCatalog
    @Transactional
    public void updateCatalogItem(CatalogItem catalogItem) throws ResourceNotFoundException {
        CatalogItem catalogItemDB = getCatalogBySku(catalogItem.getSku());

        boolean priceDifference = catalogItemDB.getPrice() != catalogItem.getPrice();

        catalogItemDB.setName(catalogItem.getName());
        catalogItemDB.setDescription(catalogItem.getDescription());
        catalogItemDB.setPrice(catalogItem.getPrice());
        catalogItemDB.setInventory(catalogItem.getInventory());
        catalogItemDB.setUpdatedOn(new Date());

        //publish if price changed
        if (priceDifference){
            //invoking ProductPriceUpdateEvent
        }else {
            //do nothing
        }
        catalogRepository.persist(catalogItemDB);
    }

    //this is invoked in ProductPurchaseReceiveEvent
    @Transactional
    public void productPurchased(String skuNumber){
        try{
            CatalogItem catalogItem = getCatalogItem(skuNumber);
            if (catalogItem.getInventory() > 0){
                catalogItem.setInventory(catalogItem.getInventory()-1);
                updateCatalogItem(catalogItem);
            }
        }catch (ResourceNotFoundException e){
            logger.error(String.format("catalog item not found for sku :",skuNumber));
        }
    }


}
