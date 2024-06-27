package com.bookRealm.api_v1.service;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.Attributes;
import javax.naming.directory.Attribute;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.*;
import org.apache.commons.validator.routines.EmailValidator;

public class EmailValidationService {
	
	 public static boolean isEmailValid(String email) {
	        EmailValidator validator = EmailValidator.getInstance();
	        return validator.isValid(email);
	    }

	    public static boolean isValidDomain(String email) {
	        String domain = email.substring(email.indexOf("@") + 1);
	        try {
	            Hashtable<String, String> env = new Hashtable<>();
	            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.dns.DnsContextFactory");
	            DirContext ictx = new InitialDirContext(env);
	            Attributes attrs = ictx.getAttributes(domain, new String[]{"MX"});
	            Attribute attr = attrs.get("MX");

	            return (attr != null);
	        } catch (NamingException e) {
	            return false;
	        }
	    }

	    public static boolean isEmailAddressDeliverable(String email) {
	        String domain = email.substring(email.indexOf("@") + 1);
	        try {
	            Hashtable<String, String> env = new Hashtable<>();
	            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.dns.DnsContextFactory");
	            DirContext ictx = new InitialDirContext(env);
	            Attributes attrs = ictx.getAttributes(domain, new String[]{"MX"});
	            Attribute attr = attrs.get("MX");
	            if (attr == null) {
	                return false;
	            }

	            String mxRecord = (String) attr.get();
	            String[] mxRecords = mxRecord.split("\\s+");
	            String smtpServer = mxRecords[mxRecords.length - 1];

	            try (Socket socket = new Socket(smtpServer, 25);
	                 BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
	                String response = reader.readLine();
	                if (!response.startsWith("220")) {
	                    return false;
	                }

	                socket.getOutputStream().write(("HELO gmail.com\r\n").getBytes());
	                response = reader.readLine();
	                if (!response.startsWith("250")) {
	                    return false;
	                }

	                socket.getOutputStream().write(("MAIL FROM:<shailjaagarwal1970@gmail.com>\r\n").getBytes());
	                response = reader.readLine();
	                if (!response.startsWith("250")) {
	                    return false;
	                }

	                socket.getOutputStream().write(("RCPT TO:<" + email + ">\r\n").getBytes());
	                response = reader.readLine();
	                if (!response.startsWith("250")) {
	                    return false;
	                }

	                socket.getOutputStream().write("QUIT\r\n".getBytes());
	                return true;
	            }
	        } catch (IOException | NamingException e) {
	            return false;
	        }
	    }

}
