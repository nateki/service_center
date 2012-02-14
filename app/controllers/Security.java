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
        return Customer.connect(username, password) != null;
    }
    static void onDisconnected() {
    Application.index();
}
    static void onAuthenticated() {
    Admin.index();
}
    static boolean check(String profile) {
    if("admin".equals(profile)) {
        return Customer.find("byEmail", connected()).<Customer>first().isAdmin;
    }
    return false;
}
}
