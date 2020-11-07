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
    boolean send;

    public boolean isSend() {
        return send;
    }

    public void setSend(boolean send) {
        this.send = send;
    }



    /**Constructor:*/
    public MessageInfo(String m,  long i)
    {
        message = m;
        id = i;
    }

    public MessageInfo(String m)
    {
        this(m,0);
        message = m;
    }

    public void update(String m)
    {
      message = m;
    }



    public long getId() {
        return id;
    }


}
