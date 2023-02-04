package org.acme.model;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.*;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@RegisterForReflection
@ToString
public class CatalogItemList {

    @NonNull
    private List<CatalogItem> catalogItemList;




}
