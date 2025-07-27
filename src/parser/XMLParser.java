package parser;

import exceptions.EmptyQueueException;
import implementations.MyQueue;
import implementations.MyStack;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class XMLParser {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java parser.XMLParser <filename>");
            return;
        }

        String filename = "res/" + args[0];
        MyStack<String> tagStack = new MyStack<>();
        MyQueue<String> errorLines = new MyQueue<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                line = line.trim();

                // Ignore processing instructions
                if (line.startsWith("<?") || line.startsWith("<!")) {
                    continue;
                }

                // Process line: basic logic
                if (line.matches(".*<[^/].*?>.*") && !line.contains("/>")) {
                    // opening tag
                    String tag = extractTag(line);
                    if (tag != null)
                        tagStack.push(tag);
                } else if (line.matches(".*</.*?>.*")) {
                    // closing tag
                    String closingTag = extractClosingTag(line);
                    if (tagStack.isEmpty()) {
                        errorLines.enqueue("Line " + lineNumber + ": Unexpected closing tag </" + closingTag + ">");
                    } else {
                        String openTag = tagStack.pop();
                        if (!openTag.equals(closingTag)) {
                            errorLines.enqueue("Line " + lineNumber + ": Tag mismatch. Expected </" + openTag
                                    + ">, found </" + closingTag + ">");
                        }
                    }
                } else if (line.contains("/>")) {
                    // self-closing tag → ignore
                    continue;
                }
            }

            // Check leftover unclosed tags
            while (!tagStack.isEmpty()) {
                errorLines.enqueue("Missing closing tag for <" + tagStack.pop() + ">");
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        // Output errors
        if (errorLines.isEmpty()) {
            System.out.println("XML document is well-formed.");
        } else {
            System.out.println("Malformed XML detected:");
            while (!errorLines.isEmpty()) {
                try {
                    System.out.println(errorLines.dequeue());
                } catch (EmptyQueueException e) {
                    System.out.println("Error: Tried to dequeue from an empty queue.");
                }
            }
        }
    }

    // Extract tag name from opening tag
    private static String extractTag(String line) {
        int start = line.indexOf("<") + 1;
        int end = line.indexOf(">");
        if (start > 0 && end > start) {
            String tag = line.substring(start, end).split(" ")[0];
            return tag.replace("/", "").trim();
        }
        return null;
    }

    // Extract tag name from closing tag
    private static String extractClosingTag(String line) {
        int start = line.indexOf("</") + 2;
        int end = line.indexOf(">", start);
        if (start > 1 && end > start) {
            return line.substring(start, end).trim();
        }
        return null;
    }
}