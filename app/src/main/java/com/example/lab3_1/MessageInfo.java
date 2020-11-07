package com.example.lab3_1;


public class MessageInfo {

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    protected String message;
    protected long id;

    public int getSend() {
        return send;
    }

    public void setSend(int send) {
        this.send = send;
    }

    int send;





    /**Constructor:*/
    public MessageInfo(String m, int s,  long i)
    {

        this(m,s);
        message = m;
        id = i;
        send = s;

    }

    public MessageInfo(String m, int s)
    {
        message = m;
        send = s;
    }

    public void update(String m)
    {
      message = m;
    }



    public long getId() {
        return id;
    }


}
