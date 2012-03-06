/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import play.*;
import play.mvc.*;
import java.util.*;
import models.*;

@With(Secure.class)
public class Customer_secured extends Controller {

    public static void welcome() {
        Customer c = Customer.find("byEmail", Security.connected()).first();
        render(c);
    }

    @Check("CUSTOMER")
    public static void add_item_service(String item_name, String service_name) {
        if (Security.isConnected()) {

            System.out.println(item_name);
            Customer c = Customer.find("byEmail", Security.connected()).first();
            System.out.println("set user");
            Item i = Item.find("byItem_name", item_name).first();
            Service s = Service.find("byService_name", service_name).first();
            //Service s = Service.find("select distinct s from Service s where s.service_name = :service_name").bind("service_name", service_name).first();
            WorkUpdate w = new WorkUpdate(c, s, i, "new").save();
            //System.out.println(c.customer_type.get_type());
            String cust_type = c.customer_type.name();
            System.out.println(c.customer_type.name());
            System.out.println(cust_type);

            c.addItemService(i, s);
            welcome();
        } else {
            //Secure.login();
            System.out.println(Security.isConnected());
            System.out.println("else");
            Customer_unsecured.welcome();
        }


    }
}
