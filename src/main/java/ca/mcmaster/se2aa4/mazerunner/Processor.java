package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Processor {
    private static final Logger logger = LogManager.getLogger();

    public String processPathSequence(String pathSequence) {
        String path = "";
        int i=0;
        if(pathSequence.matches(".*\\d.*")) {
            while(i < pathSequence.length()) {
                char current = pathSequence.charAt(i);
                if(Character.isDigit(current)) {
                    String number = "";
                    while(i < pathSequence.length() && Character.isDigit(pathSequence.charAt(i))) {
                        number += pathSequence.charAt(i);
                        i++;
                    }
                    int value = Integer.parseInt(number);
                    path += String.valueOf(pathSequence.charAt(i)).repeat(value - 1);
                }else {
                    path += current;
                    i++;
                }
            }
        } else {
            path = pathSequence;
        }

        return path;
    }

    public String factorizePath(String pathSequence) {
        logger.info("Factorizing path");
        int i=0;
        char prev = ' ';
        String factorized = "";
        int count = 0;
        while(i < pathSequence.length()) {
            if(prev == ' ') {
                prev = pathSequence.charAt(i);
                count++;
            } else if (pathSequence.charAt(i) == prev) {
                count++;
            } else {
                if(count != 1) {
                    factorized += String.valueOf(count) + String.valueOf(prev) + " ";
                } else {
                    factorized += String.valueOf(prev) + " ";
                }
                prev = pathSequence.charAt(i);
                count = 1;
            }
            i++;
        }
        if(count != 1) {
            factorized += String.valueOf(count) + String.valueOf(prev) + " ";
        } else {
            factorized += String.valueOf(prev) + " ";
        }
        return factorized;
    }
}
