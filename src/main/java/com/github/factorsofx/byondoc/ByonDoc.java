package com.github.factorsofx.byondoc;

import com.github.factorsofx.byondoc.parser.ObjectNode;
import com.github.factorsofx.byondoc.parser.ObjectTree;
import com.github.factorsofx.byondoc.parser.ObjectTreeParser;
import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.ToolManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ByonDoc
{
    public static void main(String... args) throws IOException, ExecutionException, InterruptedException
    {
        if(args.length != 2 || StringUtils.isEmpty(args[0]) || StringUtils.isEmpty(args[1]))
        {
            System.err.println("Bad args");
            System.exit(1);
        }
        // Get path and check DME exists
        File dme = new File(args[0]);
        if(!dme.exists())
        {
            System.err.println("DME doesn't exist");
            System.exit(2);
        }

        // Parse the object tree

        System.out.println("Parsing tree for " + dme.getName());

        Future<ObjectTree> fullTree = new ObjectTreeParser(dme).parse();

        while(!fullTree.isDone())
        {
            System.out.println(".");
            try
            {
                TimeUnit.MILLISECONDS.sleep(500);
            }
            catch(InterruptedException e)
            {
                System.err.println("Interrupted");
                return;
            }
        }
        System.out.println();

        ObjectTree tree = fullTree.get();

        // Create doc folder
        File docRoot = new File(args[1]);
        if(docRoot.exists() && !docRoot.isDirectory())
        {
            System.err.println("./docs is not a directory");
            System.exit(3);
        }
        docRoot.mkdir();

        // Generate the docs

        Properties properties = new Properties();
        properties.setProperty("resource.loader", "class");
        properties.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");

        VelocityEngine velocity = new VelocityEngine(properties);
        velocity.init();

        ToolManager toolManager = new ToolManager();
        toolManager.setVelocityEngine(velocity);

        Template indexTemplate = velocity.getTemplate("/templates/index.vm");
        Context indexContext = toolManager.createContext();

        indexContext.put("dme_name", dme.getName());

        FileWriter indexWriter = new FileWriter(new File(docRoot, "index.html"));
        indexTemplate.merge(indexContext, indexWriter);
        indexWriter.flush();
        indexWriter.close();

        Template treeTemplate = velocity.getTemplate("/templates/tree.vm");
        Context treeContext = toolManager.createContext();

        treeContext.put("root_node", tree.getRootNode());
        treeContext.put("dme_name", dme.getName());

        FileWriter treeWriter = new FileWriter(new File(docRoot, "tree.html"));
        treeTemplate.merge(treeContext, treeWriter);
        treeWriter.flush();
        treeWriter.close();

        // Here we go
        // Recursively generate pages for nodes

        Template objTypeTemplate = velocity.getTemplate("templates/object_template.vm");

        ObjectNode currentNode = tree.getRootNode();

        recursivelyGenerateNodeDocs(dme.getName(), objTypeTemplate, toolManager, docRoot, currentNode);
    }

    private static void recursivelyGenerateNodeDocs(String dmeName, Template template, ToolManager toolManager, File folder, ObjectNode node)
    {
        try
        {
            File toWrite = new File(folder, node.getName() + ".html");
            FileWriter typePageWriter = new FileWriter(toWrite);

            Context context = toolManager.createContext();
            context.put("dme_name", dmeName);
            context.put("obj_type", node);
            context.put("nav_breadcrumb", node.getParentsInOrder().stream().map((parentNode) ->
            {
                Map<String, Object> model = new HashMap<>();
                model.put("url", "/" + parentNode.toString() + ".html");
                model.put("name", parentNode.getName());
                return model;
            }).collect(Collectors.toList()));

            template.merge(context, typePageWriter);
            typePageWriter.close();

            if(node.getChildren().size() > 0)
            {
                File subdir = new File(folder, node.getName());
                subdir.mkdir();
                node.getChildren().forEach((child) -> recursivelyGenerateNodeDocs(dmeName, template, toolManager, subdir, child));
            }
        }
        catch(Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
