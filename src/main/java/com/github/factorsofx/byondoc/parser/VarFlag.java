package com.github.factorsofx.byondoc.parser;

public enum VarFlag
{
    GLOBAL("G"),
    CONST("C"),
    STATIC("G"),
    TMP("T");

    private final String flag;

    VarFlag(String flag)
    {
        this.flag = flag;
    }

    public String getFlag()
    {
        return flag;
    }
}
