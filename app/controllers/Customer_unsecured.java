package controllers;

import play.*;
import play.mvc.*;
import java.util.*;
import models.*;

public class Customer_unsecured extends Controller {

    public static void welcome() {
        render();
    }
    public static void register() {
        render();
    }
    public static void all_services() {
        List<Item> all_items = Item.findAll();
        List<Service> all_services = Service.findAll();
        render(all_items, all_services);
    }
    public static void feedback() {
        render();
    }
    public static void add_Customer(String email, String password, String fullname, Customer_type customer_type) {
        Customer c = new Customer(email, password, fullname, customer_type).save();
        welcome();
    }
}
