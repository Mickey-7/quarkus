package org.acme;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Fruit {

    public Fruit() {
    }

    public Fruit(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String name;

    public String description;
}
