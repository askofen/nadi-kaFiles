package by.epam.ts.controller.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.ts.dal.factory.impl.DaoFactoryImpl;
import by.epam.ts.dal.pool.ConnectionPool;
import by.epam.ts.dal.pool.ConnectionPoolException;

public class ConnectionPoolListener implements ServletContextListener {
	
	private ConnectionPool connectionPool;
	static final Logger log = LogManager.getLogger( ConnectionPoolListener.class);

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext context = sce.getServletContext();
		connectionPool = new ConnectionPool();
		try {
		connectionPool.initializePoolData();
		}catch (ConnectionPoolException ex) {
			log.log(Level.ERROR, "ConnectionPool wasn't initialized",ex);
		}
		DaoFactoryImpl.setConnectionPool(connectionPool);
		context.setAttribute("connectionPool", connectionPool);
		
		log.info("From Listener init");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		ServletContext context= sce.getServletContext();
		connectionPool.dispose();
		context.removeAttribute("connectionPool");
	}

}
