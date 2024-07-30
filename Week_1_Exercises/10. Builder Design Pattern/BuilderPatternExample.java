/*
Exercise 3: Implementing the Builder Pattern
Scenario: 
You are developing a system to create complex objects such as a Computer with multiple optional parts. Use the Builder Pattern to manage the construction process.
Steps:
1.	Create a New Java Project:
o	Create a new Java project named BuilderPatternExample.
2.	Define a Product Class:
o	Create a class Computer with attributes like CPU, RAM, Storage, etc.
3.	Implement the Builder Class:
o	Create a static nested Builder class inside Computer with methods to set each attribute.
o	Provide a build() method in the Builder class that returns an instance of Computer.
4.	Implement the Builder Pattern:
o	Ensure that the Computer class has a private constructor that takes the Builder as a parameter.
5.	Test the Builder Implementation:
o	Create a test class to demonstrate the creation of different configurations of Computer using the Builder pattern.

*/
import java.util.*;

class Computer{
    private final String CPU;
    private final String RAM;
    private final String storage;
    private final String GPU;

    private Computer(Builder builder){
        this.CPU = builder.CPU;
        this.RAM = builder.RAM;
        this.storage = builder.storage;
        this.GPU = builder.GPU;
    }

    public String getCPU() {
        return CPU;
    }

    public String getRAM() {
        return RAM;
    }
    public String getStorage() {
        return storage;
    }
    public String getGPU() {
        return GPU;
    }
    public static class Builder{
        private final String CPU;
        private final String RAM;
        private final String storage;
        // Optional attributes
        private String GPU;

        // Constructor for required attributes
        public Builder(String CPU, String RAM, String storage) {
            this.CPU = CPU;
            this.RAM = RAM;
            this.storage = storage;
        }

        // Methods to set optional attributes
        public Builder setGPU(String GPU) {
            this.GPU = GPU;
            return this;
        }

        // Build method to create a Computer object
        public Computer build() {
            return new Computer(this);
        }
    }
}
public class BuilderPatternExample {
    public static void main(String[] args) {
        // Creating different configurations of Computer using Builder pattern
        Computer gamingPC = new Computer.Builder("Intel i9", "32GB", "1TB SSD")
                .setGPU("NVIDIA RTX 3080")
                .build();

        Computer officePC = new Computer.Builder("Intel i5", "16GB", "512GB SSD")
                .build();

        // Displaying the configurations
        System.out.println("Gaming PC Configuration:");
        System.out.println("CPU: " + gamingPC.getCPU());
        System.out.println("RAM: " + gamingPC.getRAM());
        System.out.println("Storage: " + gamingPC.getStorage());
        System.out.println("GPU: " + gamingPC.getGPU());

        System.out.println("\nOffice PC Configuration:");
        System.out.println("CPU: " + officePC.getCPU());
        System.out.println("RAM: " + officePC.getRAM());
        System.out.println("Storage: " + officePC.getStorage());
        System.out.println("GPU: " + officePC.getGPU()); // This will be null since GPU is optional
    }
}
