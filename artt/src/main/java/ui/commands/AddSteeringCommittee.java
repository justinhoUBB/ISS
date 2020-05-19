package ui.commands;

import domain.SteeringCommittee;
import service.SteeringCommitteeService;
import utilities.Utils;

public class AddSteeringCommittee extends Command{


    private final SteeringCommitteeService steeringCommitteeService;

    public AddSteeringCommittee(String key, String description, SteeringCommitteeService steeringCommitteeService) {
        super(key, description);
        this.steeringCommitteeService=steeringCommitteeService;
    }

    @Override
    public void execute() {
        System.out.println("Enter FirstName: ");
        String first_name= Utils.INPUT.nextLine();
        System.out.println("Enter LastName: ");
        String last_name= Utils.INPUT.nextLine();
        System.out.println("Enter Email: ");
        String email= Utils.INPUT.nextLine();
        steeringCommitteeService.addSteeringCommittee(new SteeringCommittee(first_name,last_name,email));
    }
}
