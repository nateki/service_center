package controllers;

import play.*;
import play.mvc.*;

import java.util.*;
import java.util.ArrayList;
import java.util.List;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import models.*;

import notifiers.*;

@With(Secure.class)
public class Application extends Controller {

    public static void index() {
        List<Item> all_items = Item.findAll();
        List<Service> all_services = Service.findAll();
        Customer c = Customer.find("byEmail", Security.connected()).first();
        renderArgs.put("user", c.fullname);
        render(all_items, all_services);
    }

    public static void add_Customer(String email, String password, String fullname, Customer_type customer_type) {
        Customer c = new Customer(email, password, fullname, customer_type).save();
        index();
    }

    public static void add_Item(String item_name) {
        Item i = new Item(item_name).save();
        index();
    }

    public static void add_Service(String service_name, String service_description) {
        Service s = new Service(service_name, service_description).save();
        index();
    }

    public static void input() {
//        for (Customer_type customer_type : Customer_type.values()) {
//            List<Customer_type> customer_types = Arrays.asList(customer_type);
//        }
//        List<Customer_type> customer_types = Arrays.asList(Customer_type.values());
//        render(customer_types);
        Customer c = Customer.find("byEmail", Security.connected()).first();
        renderArgs.put("user", c.fullname);
        render();
    }

    @Check("CUSTOMER")
    public static void show(String email, String item_name, String service_name) {

        Customer c = Customer.find("byEmail", Security.connected()).first();
        if (item_name == null || service_name == null) {
            System.out.println("null empty render");
            renderArgs.put("user", "You did not choose any item or service now");
            render(c);

        } else {

//            if (c == null) {
//                System.out.println("empty render");
//                renderArgs.put("user", "You are not a registered customer. Please register");
//                render();
//            }
            System.out.println("add item service");
            Item i = Item.find("byItem_name", item_name).first();
            Service s = Service.find("byService_name", service_name).first();
            //Service s = Service.find("select distinct s from Service s where s.service_name = :service_name").bind("service_name", service_name).first();
            WorkUpdate w = new WorkUpdate(c, s, i, "new").save();
            //System.out.println(c.customer_type.get_type());
            String cust_type = c.customer_type.name();
            System.out.println(c.customer_type.name());
            System.out.println(cust_type);

            c.addItemService(i, s);
            renderArgs.put("user", c.fullname);
            render(c, i, s);
        }
    }

    @Check("SERVICE_ENGINEER")
    public static void service() {
        Customer c = Customer.find("byEmail", Security.connected()).first();
        List<WorkUpdate> new_workUpdates = WorkUpdate.find("byStatus", "new").fetch();
        List<WorkUpdate> started_workUpdates = WorkUpdate.find("byStatus", "started").fetch();
        List<WorkUpdate> closed_workUpdates = WorkUpdate.find("byStatus", "closed").fetch();
        renderArgs.put("user", c.fullname);
        render(new_workUpdates, started_workUpdates, closed_workUpdates);
    }

    public static void service_status() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        //get current date time with Date()
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        List<WorkUpdate> new_workUpdates = WorkUpdate.find("byStatus", "new").fetch();
        List<WorkUpdate> started_workUpdates = WorkUpdate.find("byStatus", "started").fetch();
        List<WorkUpdate> closed_workUpdates = WorkUpdate.find("byStatus", "closed").fetch();
        render(new_workUpdates, started_workUpdates, closed_workUpdates);
    }

    public static void service_monitor(List new_workUpdates, List started_workUpdates, List closed_workUpdates) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        //get current date time with Date()
        Date date = new Date();
        System.out.println(dateFormat.format(date));

        render(new_workUpdates, started_workUpdates, closed_workUpdates);
    }

    public static void form(Long id) {
        if (id != null) {
            WorkUpdate workUpdate = WorkUpdate.findById(id);
            Customer c = Customer.find("byEmail", Security.connected()).first();
            renderArgs.put("user", c.fullname);
            render(workUpdate);
        }
    }

    public static void save(Long id, String status, String ticket_no, Date Completion_Date) {
        WorkUpdate workUpdate = WorkUpdate.findById(id);
        workUpdate.status = status;
        workUpdate.ticket_no = ticket_no;
        workUpdate.UpdatedAt = new Date();
        workUpdate.Completion_Date = Completion_Date;
        if (status.equals("closed")) {
            try {
                if (Notifier.welcome(workUpdate)) {
                    System.out.println("mailed");
                    workUpdate.save();
                    service();
                }

            } catch (Exception e) {
                System.out.println("error");
                Logger.error(e, "Mail error");
            }

        }
        workUpdate.save();
        service();
    }
}
