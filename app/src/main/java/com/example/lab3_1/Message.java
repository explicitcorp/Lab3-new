package com.example.lab3_1;


public class Message {

    protected String sent, received;
    protected long id;

    /**Constructor:*/
    public Message(String n, String e, long i)
    {
        sent =n;
        received = e;
        id = i;
    }

    public void update(String n, String e)
    {
        sent = n;
        received = e;
    }

    /**Chaining constructor: */
    public Message(String n, String e) { this(n, e, 0);}


    public String getEmail() {
        return received;
    }

    public String getName() {
        return sent;
    }

    public long getId() {
        return id;
    }


}
