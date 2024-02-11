package uo.ri.cws;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessFactory;
import uo.ri.cws.infrastructure.persistence.jpa.executor.JpaExecutorFactory;
import uo.ri.cws.infrastructure.persistence.jpa.repository.JpaRepositoryFactory;
import uo.ri.cws.infrastructure.persistence.jpa.util.Jpa;
import uo.ri.cws.ui.MainMenu;
import uo.ri.util.console.DefaultPrinter;

public class CashierMain {

	public static void main(String[] args) {
		new CashierMain()
				.config()
				.run()
				.close();
	}

	private CashierMain config() {
		Factory.service = new BusinessFactory();
		Factory.repository = new JpaRepositoryFactory();
		Factory.executor = new JpaExecutorFactory();

		return this;
	}

	public CashierMain run() {
		try {
			new MainMenu().execute();

		} catch (RuntimeException rte) {
			DefaultPrinter.printRuntimeException(rte);
		}
		return this;
	}

	private void close() {
		Jpa.close();
	}

}
