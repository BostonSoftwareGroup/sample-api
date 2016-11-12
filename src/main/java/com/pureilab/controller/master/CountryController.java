package com.pureilab.controller.master;

import com.pureilab.model.master.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.google.gson.Gson;


/**
 * Created by Julian on 9/28/2016.
 */
//@Controller

@RestController
public class CountryController {


    // ------------------------
    // PRIVATE FIELDS
    // ------------------------

    @Autowired
    private CountryDao countryDao;

    @Autowired
    private CustomerDao customerDao;

    // ------------------------
    // PUBLIC METHODS
    // ------------------------

    /**
     * /create  --> Create a new user and save it in the database.
     *
     * @param email User's email
     * @param name User's name
     * @return A string describing if the user is succesfully created or not.
     */
    @RequestMapping("/create")
    @ResponseBody
    public String create(String code, String name) {
        Country obj = null;
        try {
            obj = new Country(code, name);
            countryDao.save(obj);
        }
        catch (Exception ex) {
            return "Error creating the country: " + ex.toString();
        }
        return "Country succesfully created! (id = " + obj.getId() + ")";
    }

    /**
     * /delete  --> Delete the user having the passed id.
     *
     * @param id The id of the user to delete
     * @return A string describing if the user is succesfully deleted or not.
     */
    @RequestMapping("/delete")
    @ResponseBody
    public String delete(long id) {
        try {
            Country obj = new Country(id);
            countryDao.delete(obj);
        }
        catch (Exception ex) {
            return "Error deleting the user: " + ex.toString();
        }
        return "Country succesfully deleted!";
    }

    /**
     * /get-by-email  --> Return the id for the user having the passed email.
     *
     * @param email The email to search in the database.
     * @return The user id or a message error if the user is not found.
     */
    @RequestMapping("/get-by-name")
    //@ResponseBody
    public Country getByName(String name) {
        String objId;
        Country obj = null;
        try {
            obj = countryDao.findByName(name);
            objId = String.valueOf(obj.getId());
        }
        catch (Exception ex) {
            //return "Country not found";
            return null;
        }
        //return "The country id is: " + objId;
        return obj;
    }

    @RequestMapping("/get-by-code")
    //@ResponseBody
    public Country getByCode(String code) {
        String objId;
        Country obj = null;
        try {
            obj = countryDao.findByCode(code);
            objId = String.valueOf(obj.getId());
        }
        catch (Exception ex) {
            //return "Country not found";
            return null;
        }
        //return "The country id is: " + objId;
        return obj;
    }

    //@CrossOrigin(origins = "http://192.168.1.12:8080")
    @CrossOrigin
    @RequestMapping("/get-all")
    //@ResponseBody
    public APIResponse getAllObjects() {
        APIResponse response = new APIResponse();
        Iterable allObjs = null;
        try {
            //allObjs = countryDao.findAll();
            //allObjs = countryDao.findAll(new PageRequest(0,2));
            allObjs = customerDao.findAll(new Sort("firstName"));
        }
        catch (Exception ex) {
            //return "Country not found";
            return null;
        }
        //return "The country id is: " + objId;
        response.setCode("200");
        response.setMessage("Success");
        response.setBody(allObjs);
        //return allObjs;
        return response;
    }

   @RequestMapping(value = "/create-customer", method = RequestMethod.POST)
   //@ResponseBody
    public APIResponse createCustomer(@RequestBody String json) {

        APIResponse response = new APIResponse();
        try {
            //allObjs = countryDao.findAll();
            //allObjs = countryDao.findAll(new PageRequest(0,2));
            Gson gson = new Gson();
            Customer customer = gson.fromJson(json, Customer.class);
            System.out.println("JSON: " + json);

            if(customer != null) {
                customerDao.save(customer);
            }
            response.setCode("200");
            response.setMessage("Success");
            response.setBody(customer);
        }
        catch (Exception ex) {
            //return "Country not found";
            ex.printStackTrace();
            return null;
        }
        System.out.println(response.getBody());

        return response;
    }

    //@CrossOrigin(origins = "http://192.168.1.12:8080")
    @CrossOrigin
    @RequestMapping("/customers")
    //@ResponseBody
    public APIResponse getCustomers() {
        APIResponse response = new APIResponse();

        Iterable allObjs = null;

        try {
            //allObjs = countryDao.findAll();
            //allObjs = countryDao.findAll(new PageRequest(0,2));
            allObjs = customerDao.findAll(new Sort("firstName"));

        }
        catch (Exception ex) {
            //return "Country not found";
            return null;
        }
        //return "The country id is: " + objId;
        response.setCode("200");
        response.setMessage("Success");
        response.setBody(allObjs);
        //return allObjs;
        return response;
    }

    //@CrossOrigin(origins = "http://192.168.1.12:8080")
    @CrossOrigin
    @RequestMapping("/get-all2")
    //@ResponseBody
    public Iterable getAllObjects2() {
        Iterable allObjs = null;
        try {
            //allObjs = countryDao.findAll();
            //allObjs = countryDao.findAll(new PageRequest(0,2));
            allObjs = customerDao.findAll(new Sort("firstName"));

        }
        catch (Exception ex) {
            //return "Country not found";
            return null;
        }
        //return "The country id is: " + objId;
        return allObjs;
    }

    /**
     * /update  --> Update the email and the name for the user in the database
     * having the passed id.
     *
     * @param id The id for the user to update.
     * @param email The new email.
     * @param name The new name.
     * @return A string describing if the user is succesfully updated or not.
     */
    @RequestMapping("/update")
    @ResponseBody
    public String updateCountry(long id, String name) {
        try {
            Country obj = countryDao.findOne(id);
            obj.setName(name);
            countryDao.save(obj);
        }
        catch (Exception ex) {
            return "Error updating the country: " + ex.toString();
        }
        return "Country succesfully updated!";
    }

}
