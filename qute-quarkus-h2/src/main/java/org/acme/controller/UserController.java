package org.acme.controller;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import org.acme.model.User;
import org.acme.repository.UserRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/")
public class UserController {

    // the Template home is under src/main/resources.templates/home.html
    @Inject
    Template home;

    // the Template home is under src/main/resources.templates/createupdate.html
    @Inject
    Template createupdate;

    @Inject
    UserRepository userRepository;

    //render home.html
    @GET
    @Path("/home")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getAllUserView(){
        List<User> users = userRepository.getUsers();
        return home.data(Map.of("users",users));
    }

    //render create user page
    @GET
    @Path("/create")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance createUserView(){
        User user = new User();
        Map<String,Object> obj = new HashMap<>();
        obj.put("user",user);
        obj.put("isUpdate",false);
        return createupdate.data(obj);
    }
    //saving of the user then return to view all
    @POST
    @Path("/create")
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public TemplateInstance createUser(
            @FormParam("firstName") String firstName,
            @FormParam("lastName") String lastName,
            @FormParam("email") String email
    ){
        User user = new User();
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        userRepository.addUser(user);
        return getAllUserView();
    }

    //delete user then return to view all
    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/delete/{id}")
    public TemplateInstance deleteUser(@PathParam("id") Long id){
        userRepository.deleteUser(id);
        return getAllUserView();
    }

    //render update user page
    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/update/{id}")
    public TemplateInstance updateUserView(@PathParam("id") Long id){
        User user = userRepository.getUser(id);
        Map<String,Object> obj = new HashMap<>();
        obj.put("user",user);
        obj.put("isUpdate",true);
        return createupdate.data(obj);
    }
    //updating of the user then return to view all
    @POST
    @Path("/update/{id}")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance updateUser(
            @FormParam("firstName") String firstName,
            @FormParam("lastName") String lastName,
            @FormParam("email") String email,
            @PathParam("id") Long id
    ){
        User user = new User();
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setId(id);
        userRepository.updateUser(user);
        return getAllUserView();
    }


}
