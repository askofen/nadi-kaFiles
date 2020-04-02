package by.epam.ts.controller.command;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import by.epam.ts.controller.command.impl.EmptyCommand;
import by.epam.ts.controller.command.impl.LoginCommand;
import by.epam.ts.controller.command.impl.LogoutCommand;
import by.epam.ts.controller.command.impl.ShowPatientMainPageCommand;
import by.epam.ts.controller.command.impl.ShowTreatmentCommand;
import by.epam.ts.controller.command.impl.SignUpCommand;
import by.epam.ts.controller.command.impl.WrongRequestCommand;

public class CommandProvider {
	
private final Map<CommandEnum, Command> repository = new HashMap<>();
	
	public CommandProvider() {
		repository.put(CommandEnum.LOGIN, new LoginCommand());
		repository.put(CommandEnum.LOGOUT, new LogoutCommand());
		repository.put(CommandEnum.SHOW_TREATMENT, new ShowTreatmentCommand());
		repository.put(CommandEnum.SIGN_UP, new SignUpCommand());
		repository.put(CommandEnum.WRONG_REQUEST, new WrongRequestCommand());
		repository.put(CommandEnum.SHOW_PATIENT_MAIN_PAGE, new ShowPatientMainPageCommand());
	}
	
	public Command defineCommand(HttpServletRequest request) {
		
		Command current = new EmptyCommand();
		String action = request.getParameter("command");
		if (action == null || action.isEmpty()) {
			return current;
		}
		try {
		CommandEnum command = CommandEnum.valueOf(action.toUpperCase());
		current = repository.get(command);
		}catch (IllegalArgumentException ex) {
			// log
			current = repository.get(CommandEnum.WRONG_REQUEST);
			return current;
		}
		return current;
	}

}
