package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.acme.model.CatalogItem;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class CatalogRepository implements PanacheRepository<CatalogItem> {
    public Optional<CatalogItem> findBySku(String sku){
        return find("sku",sku).singleResultOptional();
    }
}
