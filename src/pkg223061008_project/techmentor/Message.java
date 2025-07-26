/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg223061008_project.techmentor;

/**
 *
 * @author Apollo Gadget
 */
public class Message {
     private final int id;
    private final String name;
    private final String email;
    private final String subject;
    private final String message;
    private final String date; 

    public Message(int id, String name, String email, String subject, String message, String date) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.subject = subject;
        this.message = message;
        this.date = date;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getSubject() { return subject; }
    public String getMessage() { return message; }
    public String getDate() { return date; }
    
}
