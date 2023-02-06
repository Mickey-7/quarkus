package org.acme.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Category {
    BOOKS("Books"),
    MUSIC("Music"),
    MOVIES("Movies"),
    GAMES("Games"),
    ELECTRONICS("Electronics"),
    COMPUTERS("Computers"),
    OFFICE("Office");



    private String value;




}
