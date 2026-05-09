import commands.commands.*;
import commands.contracts.Command;
import commands.invoker.CommandProcessor;
import models.Student;
import storage.FileManager;
import storage.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        StudentRepository repository = new StudentRepository();
        FileManager fileManager      = new FileManager(repository);
        CommandProcessor processor   = new CommandProcessor();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Student Information System. Type 'help' for commands.");

        while (true) {
            System.out.print("> ");
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] tokens = tokenize(line);
            String cmdName  = tokens[0].toLowerCase();

            if (cmdName.equals("exit")) {
                System.out.println("Exiting the program...");
                break;
            }

            if (!cmdName.equals("open") && !cmdName.equals("help") && !fileManager.isOpen()) {
                System.out.println("No file is open. Use 'open <file>' first.");
                continue;
            }

            Command command = buildCommand(cmdName, tokens, fileManager, repository);
            if (command == null) {
                System.out.println("Unknown command or invalid arguments. Type 'help'.");
                continue;
            }

            processor.setCommand(command);
            try {
                System.out.println(processor.process());
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        scanner.close();
    }

    private static Command buildCommand(String name, String[] t,
                                        FileManager fm, StudentRepository repo) {
        try {
            return switch (name) {
                case "open"      -> new OpenCommand(fm, t[1]);
                case "close"     -> new CloseCommand(fm);
                case "save"      -> new SaveCommand(fm);
                case "saveas"    -> new SaveAsCommand(fm, t[1]);
                case "help"      -> new HelpCommand();
                case "enroll"    -> new EnrollCommand(repo,
                        Integer.parseInt(t[1]), t[2],
                        Integer.parseInt(t[3]), t[4], t[5]);
                case "printall"  -> new PrintAllCommand(repo, t[1], Integer.parseInt(t[2]));
                case "protocol"  -> new ProtocolCommand(repo, t[1]);

                // Commands where receiver is Student
                case "advance"   -> { Student s = find(repo, t[1]);
                    yield s != null ? new AdvanceCommand(s)   : notFound(t[1]); }
                case "graduate"  -> { Student s = find(repo, t[1]);
                    yield s != null ? new GraduateCommand(s)  : notFound(t[1]); }
                case "interrupt" -> { Student s = find(repo, t[1]);
                    yield s != null ? new InterruptCommand(s) : notFound(t[1]); }
                case "resume"    -> { Student s = find(repo, t[1]);
                    yield s != null ? new ResumeCommand(s)    : notFound(t[1]); }
                case "print"     -> { Student s = find(repo, t[1]);
                    yield s != null ? new PrintCommand(s)     : notFound(t[1]); }
                case "report"    -> { Student s = find(repo, t[1]);
                    yield s != null ? new ReportCommand(s)    : notFound(t[1]); }
                case "enrollin"  -> { Student s = find(repo, t[1]);
                    yield s != null ? new EnrollInCommand(s, t[2]) : notFound(t[1]); }
                case "addgrade"  -> { Student s = find(repo, t[1]);
                    yield s != null ? new AddGradeCommand(s, t[2],
                            Double.parseDouble(t[3])) : notFound(t[1]); }
                case "change"    -> { Student s = find(repo, t[1]);
                    yield s != null ? new ChangeCommand(s, t[2], t[3]) : notFound(t[1]); }
                default -> null;
            };
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("Invalid arguments for '" + name + "'. Type 'help'.");
            return null;
        }
    }

    private static Student find(StudentRepository repo, String fnStr) {
        try { return repo.findByFacultyNumber(Integer.parseInt(fnStr)); }
        catch (NumberFormatException e) { return null; }
    }

    private static Command notFound(String fn) {
        return () -> "Student not found: " + fn;
    }

    private static String[] tokenize(String line) {
        List<String> tokens = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        boolean inQuotes = false;
        for (char c : line.toCharArray()) {
            if (c == '"') { inQuotes = !inQuotes; }
            else if (c == ' ' && !inQuotes) {
                if (current.length() > 0) { tokens.add(current.toString()); current.setLength(0); }
            } else { current.append(c); }
        }
        if (current.length() > 0) tokens.add(current.toString());
        return tokens.toArray(new String[0]);
    }
}