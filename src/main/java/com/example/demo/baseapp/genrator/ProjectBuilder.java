package com.example.demo.baseapp.genrator;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

import java.io.*;
import java.nio.file.*;
import java.util.Map;

public class ProjectBuilder {
    public static final String TEMPLATES_DIR = "src/main/resources/templates";

    public static void copySkeleton(String targetDir) throws IOException {
        Path src = Paths.get(TEMPLATES_DIR, "base-springboot-project");
        if (!Files.exists(src)) throw new IOException("Skeleton not found at: " + src.toString());
        Path dst = Paths.get(targetDir);
        Files.walk(src).forEach(source -> {
            try {
                Path relative = src.relativize(source);
                Path dest = dst.resolve(relative);
                if (Files.isDirectory(source)) {
                    if (!Files.exists(dest)) Files.createDirectories(dest);
                } else {
                    Files.copy(source, dest, StandardCopyOption.REPLACE_EXISTING);
                }
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        });
    }

    public static void generate(String templateFile, String outputFile, Map<String, Object> context) throws Exception {
        MustacheFactory mf = new DefaultMustacheFactory();
        try (Reader reader = new FileReader(Paths.get(TEMPLATES_DIR, templateFile).toFile())) {
            Mustache mustache = mf.compile(reader, templateFile);
            File out = Paths.get(outputFile).toFile();
            out.getParentFile().mkdirs();
            try (Writer writer = new FileWriter(out)) {
                mustache.execute(writer, context).flush();
            }
        }
    }
}