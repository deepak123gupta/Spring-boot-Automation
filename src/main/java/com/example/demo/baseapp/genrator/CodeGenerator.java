package com.example.demo.baseapp.genrator;

import java.util.*;

public class CodeGenerator {
    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            System.out.println("Usage: java -jar crud-generator.jar input/employee.json");
            return;
        }
        String inputPath = args[0];
        Map<String, Object> json = JsonParserUtil.parse(inputPath);

        String packageName = (String) json.get("packageName");
        String entity = (String) json.get("entity");
        Map<String, String> fields = (Map<String, String>) json.get("fields");
        Map<String, Object> db = (Map<String, Object>) json.get("database");

        if (db == null) db = new HashMap<>();

        // build fields list for mustache
        List<Map<String, Object>> fieldList = new ArrayList<>();
        for (Map.Entry<String, String> e : fields.entrySet()) {
            String name = e.getKey();
            String type = e.getValue();
            String cap = name.substring(0, 1).toUpperCase() + name.substring(1);
            Map<String, Object> f = new HashMap<>();
            f.put("name", name);
            f.put("type", type);
            f.put("isId", name.equalsIgnoreCase("id"));
            f.put("nameCapital", cap);
            fieldList.add(f);
        }

        Map<String, Object> ctx = new HashMap<>();
        ctx.put("packageName", packageName);
        ctx.put("entity", entity);
        String entityLower = entity.substring(0, 1).toLowerCase() + entity.substring(1);
        ctx.put("entityLower", entityLower);
        ctx.put("fields", fieldList);
        ctx.put("db", db);
        ctx.put("groupId", json.get("groupId"));
        ctx.put("artifactId", json.get("artifactId"));
        ctx.put("version", json.get("version"));

        String appName = entity + "App";
        String outProjectDir = "output/" + appName;

        // base java path
        String basePath = outProjectDir + "/src/main/java/" + packageName.replace('.', '/');

        // generate source files
        ProjectBuilder.generate("base-springboot-project/Entity.mustache", basePath + "/entity/" + entity + ".java", ctx);
        ProjectBuilder.generate("base-springboot-project/Repository.mustache", basePath + "/repository/" + entity + "Repository.java", ctx);
        ProjectBuilder.generate("base-springboot-project/Service.mustache", basePath + "/service/" + entity + "Service.java", ctx);
        ProjectBuilder.generate("base-springboot-project/ServiceImpl.mustache", basePath + "/service/impl/" + entity + "ServiceImpl.java", ctx);
        ProjectBuilder.generate("base-springboot-project/Controller.mustache", basePath + "/controller/" + entity + "Controller.java", ctx);
        ProjectBuilder.generate("base-springboot-project/MainClass.mustache", basePath + "/DemoApplication.java", ctx);
        ProjectBuilder.generate("base-springboot-project/pom.xml.mustache", outProjectDir + "/pom.xml", ctx);

        // application.properties (MySQL)
        ProjectBuilder.generate("base-springboot-project/application.properties.mustache", outProjectDir + "/src/main/resources/application.properties", ctx);
        System.out.println("✅ Generated project at: " + outProjectDir);
        System.out.println("To run: cd " + outProjectDir + " && mvn spring-boot:run");
    }
}