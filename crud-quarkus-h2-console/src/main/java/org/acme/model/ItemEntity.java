package org.acme.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;

@Entity
@Table(name = "items")
public class ItemEntity extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    public Long id;
    @Column(name = "item_name")
    public String name;
    @Column(name = "item_count")
    public String count;
    @Column(name = "item_status")
    public String status;

    public ItemEntity() {
    }

    public ItemEntity(Long id, String name, String count, String status) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ItemEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", count='" + count + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
