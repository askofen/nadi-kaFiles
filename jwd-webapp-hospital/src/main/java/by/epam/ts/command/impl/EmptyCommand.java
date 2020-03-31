package by.epam.ts.command.impl;

import javax.servlet.http.HttpServletRequest;

import by.epam.ts.command.Command;
import by.epam.ts.servlet.manager.NavigationManager;

public final class EmptyCommand implements Command{

	@Override
	public String execute(HttpServletRequest request) {
		String page = NavigationManager.getProperty("path.page.index");
		return page;
	}

}
