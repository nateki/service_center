package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

import notifiers.*;

public class Application extends Controller {

    public static void index() {
        List<Item> all_items = Item.findAll();
        List<Service> all_services = Service.findAll();
        render(all_items, all_services);
    }

    public static void add_Customer(String email, String password, String fullname) {
        Customer c = new Customer(email, password, fullname).save();
    }

    public static void add_Item(String item_name) {
        Item i = new Item(item_name).save();
    }

    public static void add_Service(String service_name, String service_description) {
        Service s = new Service(service_name, service_description).save();
    }

    public static void input() {
        render();
    }

    public static void show(String email, String item_name, String service_name) {
        Item i = Item.find("byItem_name", item_name).first();
        Service s = Service.find("byService_name", service_name).first();
        //Service s = Service.find("select distinct s from Service s where s.service_name = :service_name").bind("service_name", service_name).first();
        Customer c = Customer.find("byEmail", email).first();
        WorkUpdate w = new WorkUpdate(c, s, i, "new").save();
        c.addItemService(i, s);
        Customer c1 = Customer.find("byEmail", email).first();
        render(c1, i, s);
    }

    public static void service() {
        List<WorkUpdate> new_workUpdates = WorkUpdate.find("byStatus", "new").fetch();
        List<WorkUpdate> started_workUpdates = WorkUpdate.find("byStatus", "started").fetch();
        List<WorkUpdate> closed_workUpdates = WorkUpdate.find("byStatus", "closed").fetch();
        render(new_workUpdates, started_workUpdates, closed_workUpdates);
    }

    public static void form(Long id) {
        if (id != null) {
            WorkUpdate workUpdate = WorkUpdate.findById(id);
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
