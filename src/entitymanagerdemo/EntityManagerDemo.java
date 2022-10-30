/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitymanagerdemo;

import static entitymanagerdemo.EntityManagerDemoTable.*;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Address;
import model.Customer;


public class EntityManagerDemo {

    public static void main(String[] args) {
        createData();
        printAllCustomer();
        printCustomerByCity("Bangkok");
    }

    public static void createData() {
        Customer customer1 = new Customer(1L, "John", "Henry", "jh@mail.com");
        Address address1 = new Address(1L, "123/4 Viphavadee Rd.", "Bangkok", "10900", "TH");
        address1.setCustomerFk(customer1);
        customer1.setAddressId(address1);
        Customer customer2 = new Customer(2L, "Marry", "Jane", "mj@mail.com");
        Address address2 = new Address(2L, "123/5 Viphavadee Rd.", "Bangkok", "10900", "TH");
        address2.setCustomerFk(customer2);
        customer2.setAddressId(address2);
        Customer customer3 = new Customer(3L, "Peter", "Parker", "pp@mail.com");
        Address address3 = new Address(3L, "543/21 Fake Rd.", "Nonthaburi", "20900", "TH");
        address3.setCustomerFk(customer3);
        customer3.setAddressId(address3);
        Customer customer4 = new Customer(4L, "Bruce", "Wayn", "bw@mail.com");
        Address address4 = new Address(4L, "678/90 Unreal Rd.", "Pathumthani", "30500", "TH");
        address4.setCustomerFk(customer4);
        customer4.setAddressId(address4);
        insertCustomerAddress(customer1, address1);
        insertCustomerAddress(customer2, address2);
        insertCustomerAddress(customer3, address3);
        insertCustomerAddress(customer4, address4);
    }

    public static void printAllCustomer() {
        List<Customer> cusList = findAllCustomer();
        printData(cusList);
    }

    public static void printCustomerByCity(String city) {
        List<Customer> cusList = findCustomerByCity(city);
        printData(cusList);
    }

    public static void printData(List<Customer> cusList) {
        for (int i = 0; i < cusList.size(); i++) {
            System.out.println("First Name:" + cusList.get(i).getFirstname());
            System.out.println("Last Name:" + cusList.get(i).getLastname());
            System.out.println("Email:" + cusList.get(i).getEmail());
            Address tempAdr = findAddressById(cusList.get(i).getId());
            System.out.println("Street:" + tempAdr.getStreet());
            System.out.println("City:" + tempAdr.getCity());
            System.out.println("Country:" + tempAdr.getCountry());
            System.out.println("Zip Code:" + tempAdr.getZipcode());
            if (i != cusList.size() - 1) {
                System.out.println("------------------------------------------");
                System.out.println("------------------------------------------");
            }

        }
    }
}
