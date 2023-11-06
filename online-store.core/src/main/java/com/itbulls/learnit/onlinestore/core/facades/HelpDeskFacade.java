package com.itbulls.learnit.onlinestore.core.facades;

import com.itbulls.learnit.onlinestore.persistence.enteties.helpdesk.SupportTicket;

public interface HelpDeskFacade {
	
	void addNewSupportTicket(SupportTicket supportTicket);
	
	SupportTicket getNextSupportTicket();
	
	/**
	 * @return amount of tickets that are no processed
	 */
	int getNumberOfTickets();
	
}
