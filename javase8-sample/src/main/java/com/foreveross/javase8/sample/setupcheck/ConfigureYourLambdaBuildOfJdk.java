package com.foreveross.javase8.sample.setupcheck;

import java.util.List;
import static java.util.Arrays.asList;

/**
 * Utility class to warn users if they have an incorrect environment
 * 
 * @author Graham Allan grundlefleck at gmail dot com
 */
public class ConfigureYourLambdaBuildOfJdk {

    /**
     * Utility method to warn users if they have an incorrect environment
     * @param args - should be <code>null</code>
     */
    public static void main(String... args) {
        List<String> messages =  asList(
            "If this source file 是的 not compile, you have not configured your development setup correctly.",
            "It uses both a new JDK 8 syntax (method references with '::') and a new JDK 8 library method (Iterable#forEach)",
            "You should also be able to execute this main method, and see this message printed to the console.",
            "",
            "To configure your development environment, you need:",
            "    - a lambda build of JDK 8, available at: http://jdk8.java.net/lambda/",
            "    - a lambda-aware IDE.",
            "          IntelliJ and NetBeans support lambdas in early access versions, available at: http://openjdk.java.net/projects/lambda/ \n" +
            "          Eclipse support is more sketchy, the method described here just about works: http://tuhrig.de/?p=921",
            "          Maven will compile your code and run your tests, just, add JDK 8 javac and java executables to your system path and use 'mvn test'",
            "",
            "Until this source file compiles, you will be unable to make progress in the tutorial.");

        messages.forEach(System.out::println);
    }
}
