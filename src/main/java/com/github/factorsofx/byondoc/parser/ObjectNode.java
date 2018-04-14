package com.github.factorsofx.byondoc.parser;

import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * Represents an object type in the BYOND path tree.
 */
public class ObjectNode implements Comparable<ObjectNode>
{
    private ObjectNode parent;
    private String name;
    private String doc;

    private Map<String, Var> vars = new HashMap<>();

    private List<ObjectNode> children = new ArrayList<>();

    /**
     * Creates a new object node with the given name and parent.
     * Automatically adds itself to the parent's child list.
     * @param parent
     * @param name
     */
    public ObjectNode(ObjectNode parent, String name, String doc)
    {
        this.parent = parent;
        this.name = name;
        this.doc = doc;

        if(parent != null)
        {
            parent.children.add(this);
        }
    }

    public void addVar(Var var)
    {
        vars.put(var.getName(), var);
    }

    public Var getVar(String name)
    {
        if(vars.containsKey(name))
        {
            return vars.get(name);
        }
        else
        {
            if(parent == null) return null;
            return parent.getVar(name);
        }
    }

    /**
     * @return The parent of this {@code ObjectNode}
     */
    public ObjectNode getParent()
    {
        return parent;
    }

    /**
     * Returns {@code true} if {@code potentialParent} is anywhere in the parent hierarchy of this node.
     */
    public boolean isParent(ObjectNode potentialParent)
    {
        return parent != null && (parent.equals(potentialParent) || parent.isParent(potentialParent));
    }

    public String getName()
    {
        return name;
    }

    public List<ObjectNode> getChildren()
    {
        return Collections.unmodifiableList(children);
    }

    public String getDoc()
    {
        return doc;
    }

    public Optional<ObjectNode> getChild(String name)
    {
        return children.stream().filter((node) -> node.name.equals(name)).findFirst();
    }

    /**
     * Gives the full path of this ObjectNode with slashes separating the nodes
     */
    @Override
    public String toString()
    {
        List<String> path = new ArrayList<>();
        ObjectNode currentParent = this;
        while(currentParent != null)
        {
            path.add(0, currentParent.name);
            currentParent = currentParent.parent;
        }
        return StringUtils.join(path, "/");
    }

    /**
     * Returns a list of the parent node heirarchy, highest to lowest (the way you would read them)
     */
    public List<ObjectNode> getParentsInOrder()
    {
        List<ObjectNode> parents = new ArrayList<>();
        ObjectNode currentParent = parent;
        while(currentParent != null)
        {
            parents.add(0, currentParent);
            currentParent = currentParent.parent;
        }
        return parents;
    }

    public Map<String, Var> getVars()
    {
        return Collections.unmodifiableMap(vars);
    }

    @Override
    public int compareTo(ObjectNode o)
    {
        return name.toLowerCase().compareTo(o.name.toLowerCase());
    }

    public void setDoc(String lastComment)
    {
        this.doc = lastComment;
    }
}
