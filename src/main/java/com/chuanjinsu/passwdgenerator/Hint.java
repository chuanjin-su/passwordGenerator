package com.chuanjinsu.passwdgenerator;

public class Hint {
    private int id;
    private String hint;

    public Hint(int id, String hint){
        this.id = id;
        this.hint = hint;
    }

    public Hint(String hint){
        this.hint = hint;
    }

    public int getId() {
        return id;
    }

    public String getHint() {
        return hint;
    }

    @Override
    public String toString(){
        return hint;
    }
}
