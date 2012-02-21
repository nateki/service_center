/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Kavinaesh
 */
public enum Customer_type {

    CUSTOMER {

        @Override
        public String toString() {
            return "Customer";
        }
    },
    SERVICE_ENGINEER {

        @Override
        public String toString() {
            return "Service Engineer";
        }
    },
    READ_ONLY {

        @Override
        public String toString() {
            return "Read Only";
        }
    },
    ADMIN {

        @Override
        public String toString() {
            return "Admin";
        }
    };
}
