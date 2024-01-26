package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Processor {
    private String path;
    private static final Logger logger = LogManager.getLogger();

    public String processPathSequence(String path_sequence) {
        logger.info("\nParsing Path Sequence");
        String path = "";
        int i=0;
        if(path_sequence.matches(".*\\d.*")) {
            while(i < path_sequence.length()) {
                char current = path_sequence.charAt(i);
                if(Character.isDigit(current)) {
                    String number = "";
                    while(i < path_sequence.length() && Character.isDigit(path_sequence.charAt(i))) {
                        number += path_sequence.charAt(i);
                        i++;
                    }
                    int value = Integer.parseInt(number);
                    path += String.valueOf(path_sequence.charAt(i)).repeat(value - 1);
                }else {
                    path += current;
                    i++;
                }
            }
            logger.info("This is the Factorized Path sequence: " + path);
        } else {
            path = path_sequence;
        }

        return path;
    }

    public String factorizePath(String path_sequence) {
        int i=0;
        char prev = ' ';
        String factorized = "";
        int count = 0;
        while(i < path_sequence.length()) {
            if(prev == ' ') {
                prev = path_sequence.charAt(i);
                count++;
            } else if (path_sequence.charAt(i) == prev) {
                count++;
            } else {
                if(count != 1) {
                    factorized += String.valueOf(count) + String.valueOf(prev)  ;
                } else {
                    factorized += String.valueOf(prev);
                }
                prev = path_sequence.charAt(i);
                count = 1;
            }
            i++;
        }
        if(count != 1) {
            factorized += String.valueOf(count) + String.valueOf(prev)  ;
        } else {
            factorized += String.valueOf(prev);
        }
        return factorized;
    }
}
