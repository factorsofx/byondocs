package com.github.factorsofx.byondoc.parser;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.EnumSet;

public class Var
{
    private String name;
    private String type;
    private String value;
    private String fileIn;
    private String doc = "No documentation";
    private EnumSet<VarFlag> flags;

    public Var(String partialPathAndName, String val, String fileIn, EnumSet<VarFlag> flags)
    {
        String[] parts = partialPathAndName.split("/");
        this.name = parts[parts.length - 1];
        this.type = StringUtils.join(Arrays.copyOfRange(parts, 0, parts.length - 1), "/");
        this.value = val;
        this.fileIn = fileIn;
        this.flags = flags;
    }

    public String getName()
    {
        return name;
    }

    public String getType()
    {
        return type;
    }

    public String getValue()
    {
        return value;
    }

    public String getFileIn()
    {
        return fileIn;
    }

    public String getDoc()
    {
        return doc;
    }

    public void setDoc(String doc)
    {
        this.doc = doc;
    }

    public EnumSet<VarFlag> getFlags()
    {
        return flags;
    }

    public String getFlagstring()
    {
        if(flags.isEmpty()) return "";

        StringBuilder flagBuilder = new StringBuilder();

        for(VarFlag flag : VarFlag.values())
        {
            flagBuilder.append(flag.getFlag());
        }

        return flagBuilder.toString();
    }
}
