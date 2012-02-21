/*
The application has to provide an instance of controllers.Secure.Security to customize
the authentication process.
By creating our own version of this class
we will be able to specify exactly how users should be authenticated.
 */
package controllers;

import models.*;

public class Security extends Secure.Security {

    static boolean authenticate(String username, String password) {
        //return true;
        System.out.println(" authenticate");
        return Customer.connect(username, password) != null;
    }

    static void onDisconnected() {
        System.out.println(" OnDisconnected");
        Application.index();
    }

    static void onAuthenticated() {
        System.out.println(" onAuthenticated");
        Customer c = Customer.find("byEmail", connected()).first();
        switch (c.customer_type) {
            case CUSTOMER:
                Application.index();
                break;
            case ADMIN:
                Admin.index();
                break;
            case SERVICE_ENGINEER:
                Application.service();
                break;
            default:
                Application.index();
                break;
        }

    }

    static boolean check(String profile) {



        System.out.println("check");
        System.out.println(Customer.find("byEmail", connected()).<Customer>first().isAdmin);
        System.out.println(Customer.find("byEmail", connected()).<Customer>first());
        Customer c = Customer.find("byEmail", connected()).<Customer>first();
        System.out.println(c);

        System.out.println(connected());
        System.out.println(c.customer_type);

        if (profile.equalsIgnoreCase(c.customer_type.name())) {
            return true;
        }
//        if ("admin".equals(profile)) {
//            return Customer.find("byEmail", connected()).<Customer>first().isAdmin;
//        }
        return false;
    }
}
