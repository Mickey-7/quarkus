package org.acme.service;

import org.acme.util.BannedUserClient;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BannedUserService {
    @RestClient
    BannedUserClient bannedUserClient;

    public boolean isBanned(String username){
        System.out.println("username banned : "+username);
        String banned = bannedUserClient.isBanned(username);
        return Boolean.parseBoolean(banned);
    }
}
