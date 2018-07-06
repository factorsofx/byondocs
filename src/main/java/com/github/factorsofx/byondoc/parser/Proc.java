package com.github.factorsofx.byondoc.parser;

public class Proc
{
    private String name;
    private String argString;
    private String doc = "No documentation";
    
    public Proc(String name, String argString)
    {
        this.name = name;
        this.argString = argString;
    }

    public String getName()
    {
        return name;
    }

    public String getArgString()
    {
        return argString;
    }

    public void setDoc(String doc)
    {
        this.doc = doc;
    }

    public String getDoc()
    {
        return doc;
    }
}
