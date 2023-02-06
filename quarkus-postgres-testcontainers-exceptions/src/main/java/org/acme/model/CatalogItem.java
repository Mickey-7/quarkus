package org.acme.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.acme.validation.IEnumValidator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CATALOG_ITEMS",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "SKU_NUMBER")
    }
)
public class CatalogItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;

    @NotEmpty(message = "SKU cannot be null or empty")
    @NonNull
    @Column(name = "SKU_NUMBER", unique = true, nullable = false, length = 16)
    private String sku;

    @NotEmpty(message = "Name cannot be empty or null")
    @NonNull
    @Column(name = "ITEM_NAME", unique = true, nullable = false, length = 255)
    private String name;

    @NotEmpty(message = "Description cannot be empty or null")
    @NonNull
    @Column(name = "DESCRIPTION", nullable = false, length = 500)
    private String description;

    @NonNull
    @Column(name = "CATEGORY", nullable = false)
    @IEnumValidator(
            enumClazz = Category.class,
            message = "invalid category provided"
    )
    private String category;

    @NotNull(message = "Price cannot be empty or null")
    @NonNull
    @Column(name = "PRICE", nullable = false, precision = 10, scale = 2)
    private Double price;

    @NotNull(message = "Inventory cannot be empty or null")
    @NonNull
    @Column(name = "INVENTORY", nullable = false)
    private Integer inventory;
    @NonNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_ON", nullable = false, length = 19)
    private Date createdOn;
    @Column(name = "UPDATED_ON", nullable = true, length = 19)
    private Date updatedOn;
}
