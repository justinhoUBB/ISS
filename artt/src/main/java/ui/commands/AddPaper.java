package ui.commands;

import domain.Paper;
import service.PaperService;
import utilities.Utils;

import java.time.LocalDate;

public class AddPaper extends Command{

    private final PaperService paperService;

    public AddPaper(String key, String description,PaperService paperService) {
        super(key, description);
        this.paperService =paperService;
    }

    @Override
    public void execute() {
        System.out.println("Enter title:");
        String name = Utils.INPUT.nextLine();
        System.out.println("Enter publisher_id:");
        Long publicher_id= Long.valueOf(Utils.INPUT.nextLine());
        String dateStr = Utils.INPUT.nextLine();
        LocalDate dob = LocalDate.parse(dateStr, Utils.DATE_TIME_FORMATTER);
        paperService.addPaper(new Paper(name,publicher_id,dob));
    }
}
