package com.josediaz.kotlinaccelerator.interop;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
 * Java class to demonstrate calling Kotlin from Java
 */
public class JavaClassForKotlin {
    
    private String name;
    private int age;
    
    public JavaClassForKotlin(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getAge() {
        return age;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
    
    // Method that might return null
    public String getDescription() {
        return Math.random() > 0.5 ? "Description: " + name + " is " + age + " years old" : null;
    }
    
    // Method with varargs
    public String concatenate(String... strings) {
        StringBuilder sb = new StringBuilder();
        for (String s : strings) {
            sb.append(s).append(" ");
        }
        return sb.toString().trim();
    }
    
    // Method that throws checked exception
    public String riskyMethod() throws Exception {
        if (Math.random() > 0.5) {
            throw new Exception("Random exception from Java");
        }
        return "Success from Java";
    }
    
    // Static method
    public static String getStaticMessage() {
        return "Hello from Java static method";
    }
    
    // Method that returns Java collections
    public List<String> getNames() {
        List<String> names = new ArrayList<>();
        names.add("Alice");
        names.add("Bob");
        names.add("Charlie");
        return names;
    }
    
    public Map<String, Integer> getScores() {
        Map<String, Integer> scores = new HashMap<>();
        scores.put("Alice", 95);
        scores.put("Bob", 87);
        scores.put("Charlie", 92);
        return scores;
    }
    
    @Override
    public String toString() {
        return "JavaClassForKotlin{name='" + name + "', age=" + age + "}";
    }
}
