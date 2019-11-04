
package com.github.skjolber.bank.example.v1;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.github.skjolber.bank.example.v1 package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.github.skjolber.bank.example.v1
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetAccountsRequest }
     * 
     */
    public GetAccountsRequest createGetAccountsRequest() {
        return new GetAccountsRequest();
    }

    /**
     * Create an instance of {@link GetAccountsResponse }
     * 
     */
    public GetAccountsResponse createGetAccountsResponse() {
        return new GetAccountsResponse();
    }

    /**
     * Create an instance of {@link BankException }
     * 
     */
    public BankException createBankException() {
        return new BankException();
    }

    /**
     * Create an instance of {@link BankRequestHeader }
     * 
     */
    public BankRequestHeader createBankRequestHeader() {
        return new BankRequestHeader();
    }

    /**
     * Create an instance of {@link CustomerException }
     * 
     */
    public CustomerException createCustomerException() {
        return new CustomerException();
    }

}
