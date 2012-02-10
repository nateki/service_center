package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends Controller {

    public static void index() {
         List <Item> all_items = Item.find("order by item_name desc").from(0).fetch(10);
         List <Service> all_services = Service.find("order by Service_name desc").from(0).fetch(10);
         List <Customer> all_customers = Customer.find("order by fullname desc").from(0).fetch(10);
         System.out.println(" I am index");
        render(all_items,all_services,all_customers);
    }
     public static void add_Customer(String email, String password, String fullname) {
       Customer c = new Customer(email,password,fullname).save();
       }
      public static void add_Item(String item_name) {
       Item i = new Item(item_name).save();
          }
      public static void add_Service(String service_name, String service_description) {
       Service s = new Service(service_name,service_description).save();
           }

      public static void input() {
          System.out.println(" I am input()");
          render();
           }
       public static void show(String email, String item_name,String service_name) {
           Item i =
           Item.find("select distinct i from Item i where i.item_name = nvl(:item_name,'')").bind("item_name",item_name).first();
           Service s = Service.find("select distinct s from Service s where s.service_name = :service_name").bind("service_name",service_name).first();
           Customer c =
           Customer.find("select distinct c from Customer c where c.email = :email").bind("email",email).first();
           WorkUpdate w = new WorkUpdate(c,s,i,"new").save();
           c.addItemService(i,s);
           Customer c1 =
           Customer.find("select distinct c1 from Customer c1 where c1.email = :email").bind("email",email).first();
           render(c1,i,s);
       }

}
