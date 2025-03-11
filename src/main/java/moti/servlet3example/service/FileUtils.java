package moti.servlet3example.service;

import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;


public class FileUtils {
    private FileUtils() {}
    
    // This is a functional interface that can be implemented with lambda expressions in Java 8+
    @FunctionalInterface // Added annotation to explicitly mark this as a functional interface
    public interface ReaderAction {
        void onReader(Reader reader) throws Exception;
    }
    
    /** Load a file content from either user HOME directory or from classpath as resource. */
    public static void loadOptionalFile(String fileName, String packageName, ReaderAction action) {
        // No changes needed here - code is compatible with Java 21
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        String directory = System.getProperty("user.home");
        withReader(fileName, directory, packageName, cl, action);
    }
    
    /** Load a file content from either user directory or from classpath as resource. */
    public static void withReader(String fileName, String directory, String packageName, ClassLoader cl, ReaderAction action) {
        // Could use 'var' here, but keeping explicit types for clarity
        File file = new File(directory + "/" + fileName);
        if (file.exists()) {
            // Load from a file
            try (Reader reader = new FileReader(file)) {
                action.onReader(reader);
            } catch (Exception e) {
                throw new RuntimeException("Failed to read from file: " + file, e);
            }
        } else {
            // If no user file, then load from classpath resource.
            if (packageName.contains(".")) {
                packageName = packageName.replaceAll("\\.", "/");
            }
            if (!packageName.startsWith("/")) {
                packageName = "/" + packageName;
            }
            String resourceName = packageName + "/" + fileName;
            try (InputStream inputStrem = cl.getResourceAsStream(resourceName)) {
                if (inputStrem == null) {
                    throw new RuntimeException("No resource found from classpath: " + resourceName);
                }
                action.onReader(new InputStreamReader(inputStrem));
            } catch (Exception e) {
                throw new RuntimeException("Failed to read from classpath resource: " + resourceName, e);
            }
        }
    }
}