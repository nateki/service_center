package controllers;

import play.*;
import play.mvc.*;
import java.util.*;

import models.*;

@With(Secure.class)
public class Admin extends Controller {

    @Before
    static void setConnectedUser() {
        if (Security.isConnected()) {
            Customer customer = Customer.find("byEmail", Security.connected()).first();
            System.out.println("set user");
            renderArgs.put("user", customer.fullname);
        }
    }

    @Check("ADMIN")
    public static void index() {
        String user = Security.connected();
        System.out.println("index");
        render();
    }
}
