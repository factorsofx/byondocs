package com.github.factorsofx.byondoc.parser;

import java.nio.file.Path;
import java.util.*;

public class ObjectTree
{
    // region Byond Builtins

    // Root node
    private ObjectNode datum = new ObjectNode(null, "datum", "Built-in datum type");
    {
        datum.addVar(new Var("tag", "null", "builtins", EnumSet.noneOf(VarFlag.class)));
    }

    private ObjectNode atom = new ObjectNode(datum, "atom", "Built-in atom type");
    {
        atom.addVar(new Var("alpha", "255", "builtins", EnumSet.noneOf(VarFlag.class)));
        atom.addVar(new Var("appearance_flags", "0", "builtins", EnumSet.noneOf(VarFlag.class)));
        atom.addVar(new Var("blend_mode", "0", "builtins", EnumSet.noneOf(VarFlag.class)));
        atom.addVar(new Var("color", "null", "builtins", EnumSet.noneOf(VarFlag.class)));
        atom.addVar(new Var("density", "0", "builtins", EnumSet.noneOf(VarFlag.class)));
        atom.addVar(new Var("desc", "null", "builtins", EnumSet.noneOf(VarFlag.class)));
        atom.addVar(new Var("dir", "2", "builtins", EnumSet.noneOf(VarFlag.class)));
        atom.addVar(new Var("gender", "\"neuter\"", "builtins", EnumSet.noneOf(VarFlag.class)));
        atom.addVar(new Var("icon", "null", "builtins", EnumSet.noneOf(VarFlag.class)));
        atom.addVar(new Var("icon_state", "null", "builtins", EnumSet.noneOf(VarFlag.class)));
        atom.addVar(new Var("infra_luminosity", "0", "builtins", EnumSet.noneOf(VarFlag.class)));
        atom.addVar(new Var("invisibility", "0", "builtins", EnumSet.noneOf(VarFlag.class)));
        atom.addVar(new Var("layer", "1", "builtins", EnumSet.noneOf(VarFlag.class)));
        atom.addVar(new Var("luminosity", "0", "builtins", EnumSet.noneOf(VarFlag.class)));
        atom.addVar(new Var("maptext", "null", "builtins", EnumSet.noneOf(VarFlag.class)));
        atom.addVar(new Var("maptext_width", "32", "builtins", EnumSet.noneOf(VarFlag.class)));
        atom.addVar(new Var("maptext_height", "32", "builtins", EnumSet.noneOf(VarFlag.class)));
        atom.addVar(new Var("maptext_x", "0", "builtins", EnumSet.noneOf(VarFlag.class)));
        atom.addVar(new Var("maptext_y", "0", "builtins", EnumSet.noneOf(VarFlag.class)));
        atom.addVar(new Var("mouse_drag_pointer", "0", "builtins", EnumSet.noneOf(VarFlag.class)));
        atom.addVar(new Var("mouse_drop_pointer", "1", "builtins", EnumSet.noneOf(VarFlag.class)));
        atom.addVar(new Var("mouse_drop_zone", "0", "builtins", EnumSet.noneOf(VarFlag.class)));
        atom.addVar(new Var("mouse_opacity", "1", "builtins", EnumSet.noneOf(VarFlag.class)));
        atom.addVar(new Var("mouse_over_pointer", "0", "builtins", EnumSet.noneOf(VarFlag.class)));
        atom.addVar(new Var("name", "null", "builtins", EnumSet.noneOf(VarFlag.class)));
        atom.addVar(new Var("opacity", "0", "builtins", EnumSet.noneOf(VarFlag.class)));
        atom.addVar(new Var("overlays", "list()", "builtins", EnumSet.noneOf(VarFlag.class)));
        atom.addVar(new Var("override", "0", "builtins", EnumSet.noneOf(VarFlag.class)));
        atom.addVar(new Var("pixel_x", "0", "builtins", EnumSet.noneOf(VarFlag.class)));
        atom.addVar(new Var("pixel_y", "0", "builtins", EnumSet.noneOf(VarFlag.class)));
        atom.addVar(new Var("pixel_z", "0", "builtins", EnumSet.noneOf(VarFlag.class)));
        atom.addVar(new Var("plane", "0", "builtins", EnumSet.noneOf(VarFlag.class)));
        atom.addVar(new Var("suffix", "null", "builtins", EnumSet.noneOf(VarFlag.class)));
        atom.addVar(new Var("transform", "null", "builtins", EnumSet.noneOf(VarFlag.class)));
        atom.addVar(new Var("underlays", "list()", "builtins", EnumSet.noneOf(VarFlag.class)));
        atom.addVar(new Var("verbs", "list()", "builtins", EnumSet.noneOf(VarFlag.class)));
    }

    private ObjectNode movable = new ObjectNode(atom, "movable", "Built-in movable type");
    {
        movable.addVar(new Var("animate_movement", "1", "builtins", EnumSet.noneOf(VarFlag.class)));
        movable.addVar(new Var("bound_x", "0", "builtins", EnumSet.noneOf(VarFlag.class)));
        movable.addVar(new Var("bound_y", "0", "builtins", EnumSet.noneOf(VarFlag.class)));
        movable.addVar(new Var("bound_width", "32", "builtins", EnumSet.noneOf(VarFlag.class)));
        movable.addVar(new Var("bound_height", "32", "builtins", EnumSet.noneOf(VarFlag.class)));
        movable.addVar(new Var("glide_size", "0", "builtins", EnumSet.noneOf(VarFlag.class)));
        movable.addVar(new Var("screen_loc", "null", "builtins", EnumSet.noneOf(VarFlag.class)));
        movable.addVar(new Var("step_size", "32", "builtins", EnumSet.noneOf(VarFlag.class)));
        movable.addVar(new Var("step_x", "0", "builtins", EnumSet.noneOf(VarFlag.class)));
        movable.addVar(new Var("step_y", "0", "builtins", EnumSet.noneOf(VarFlag.class)));
    }

    private ObjectNode area = new ObjectNode(atom, "area", "Built-in area type");
    {
        area.addVar(new Var("layer", "1", "builtins", EnumSet.noneOf(VarFlag.class)));
        area.addVar(new Var("luminosity", "1", "builtins", EnumSet.noneOf(VarFlag.class)));
    }

    private ObjectNode turf = new ObjectNode(atom, "turf", "Built-in turf type");
    {
        turf.addVar(new Var("layer", "2", "builtins", EnumSet.noneOf(VarFlag.class)));
    }

    private ObjectNode obj = new ObjectNode(movable, "obj", "Built-in obj type");
    {
        obj.addVar(new Var("layer", "3", "builtins", EnumSet.noneOf(VarFlag.class)));
    }

    private ObjectNode mob = new ObjectNode(movable, "mob", "Built-in mob type");
    {
        mob.addVar(new Var("ckey", "null", "builtins", EnumSet.noneOf(VarFlag.class)));
        mob.addVar(new Var("density", "1", "builtins", EnumSet.noneOf(VarFlag.class)));
        mob.addVar(new Var("key", "null", "builtins", EnumSet.noneOf(VarFlag.class)));
        mob.addVar(new Var("layer", "4", "builtins", EnumSet.noneOf(VarFlag.class)));
        mob.addVar(new Var("see_in_dark", "2", "builtins", EnumSet.noneOf(VarFlag.class)));
        mob.addVar(new Var("see_infrared", "0", "builtins", EnumSet.noneOf(VarFlag.class)));
        mob.addVar(new Var("see_invisible", "0", "builtins", EnumSet.noneOf(VarFlag.class)));
        mob.addVar(new Var("sight", "0", "builtins", EnumSet.noneOf(VarFlag.class)));
    }

    private ObjectNode world = new ObjectNode(datum, "world", "Built-in world type");
    {
        world.addVar(new Var("turf", "/turf", "builtins", EnumSet.noneOf(VarFlag.class)));
        world.addVar(new Var("mob", "/mob", "builtins", EnumSet.noneOf(VarFlag.class)));
        world.addVar(new Var("area", "/area", "builtins", EnumSet.noneOf(VarFlag.class)));
    }

    // endregion

    private List<Path> fileDirs;
    private Map<String, String> macros;
    private Map<String, ObjectNode> pathCache;

    public ObjectTree()
    {
        fileDirs = new ArrayList<>();
        macros = new HashMap<>();
        pathCache = new HashMap<>();
    }

    public ObjectNode getOrCreateObjectNode(String path)
    {
        String trimmedPath = path.trim();

        ObjectNode current;
        if((current = pathCache.get(trimmedPath)) != null)
        {
            return current;
        }

        String[] splitPath = trimmedPath.split("/");
        int startIndex = 0;

        if(splitPath[0].isEmpty()) startIndex++;

        if(splitPath.length > startIndex)
        {
            ObjectNode currentNode;

            String startNodeName = splitPath[startIndex];
            switch(startNodeName)
            {
                case "datum":
                    currentNode = datum;
                    break;
                case "atom":
                    currentNode = atom;
                    break;
                case "area":
                    currentNode = area;
                    break;
                case "turf":
                    currentNode = turf;
                    break;
                case "obj":
                    currentNode = obj;
                    break;
                case "mob":
                    currentNode = mob;
                    break;
                default:
                    currentNode = datum;
                    break;
            }

            startIndex++;

            for(int i = startIndex; i < splitPath.length; i++)
            {
                // Iterates through the path, getting or creating children as it goes.
                // Remember, ObjectNode::new automatically adds the new node to its parent
                Optional<ObjectNode> optNode = currentNode.getChild(splitPath[i]);
                if(optNode.isPresent())
                {
                    currentNode = optNode.get();
                }
                else
                {
                    currentNode = new ObjectNode(currentNode, splitPath[i], "");
                }
            }

            return currentNode;
        }
        else
        {
            return datum;
        }
    }

    public void addGlobalVar(Var glob)
    {

    }

    public void addFileDir(Path path)
    {
        fileDirs.add(path);
    }

    public ObjectNode getRootNode()
    {
        return datum;
    }

    void addMacro(String name, String val)
    {
        macros.put(name, val);
    }

    void removeMacro(String name)
    {
        macros.remove(name);
    }

    boolean isMacro(String macro)
    {
        return macros.containsKey(macro);
    }

    String getMacro(String macro)
    {
        return macros.get(macro);
    }
}
