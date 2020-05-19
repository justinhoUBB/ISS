package ui.commands;

import service.exceptions.ConferenceRuntimeException;
import utilities.Utils;

import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

public class Menu extends Command
{
    protected final Map<String, Command> commands = new HashMap<>();
    private boolean stop;

    public Menu(String key, String description)
    {
        super(key, description);
        stop = false;
    }

    @Override
    public void execute()
    {
        show();
    }

    public void addCommand(Command c)
    {
        commands.put(c.getKey(), c);
    }

    protected void printMenu()
    {
        commands.values().stream()
                .map(l -> String.format("%4s : %s", l.getKey(), l.getDescription()))
                .sorted()
                .forEach(System.out::println);
    }

    public void show()
    {
        while (!stop)
        {
            printMenu();
            System.out.println("Input the option: ");
            String key = Utils.INPUT.nextLine();
            Command com = commands.get(key);
            if (com == null)
            {
                System.out.println("Invalid Option.");
                continue;
            }
            try
            {
                com.execute();
            } catch (ConferenceRuntimeException exception)
            {
                System.out.println(exception.getMessage());
            } catch (DateTimeParseException e)
            {
                System.out.println("Wrong date format");
            } catch (NumberFormatException e)
            {
                System.out.println("Not a number");
            }
        }
        stop = false;
    }

    public void stop()
    {
        this.stop = true;
    }
}
