package commands.commands;

import commands.contracts.Command;

public class HelpCommand implements Command {
    @Override
    public String execute() {
        return """
                Commands:
                  open <file>
                  close
                  save
                  saveas <file>
                  help
                  exit
                  enroll <fn> <program> <group> <firstName> <lastName>
                  advance <fn>
                  change <fn> program|group|year <value>
                  graduate <fn>
                  interrupt <fn>
                  resume <fn>
                  print <fn>
                  printall <program> <year>
                  enrollin <fn> <course>
                  addgrade <fn> <course> <grade>
                  protocol <course>
                  report <fn>
                """;
    }
}