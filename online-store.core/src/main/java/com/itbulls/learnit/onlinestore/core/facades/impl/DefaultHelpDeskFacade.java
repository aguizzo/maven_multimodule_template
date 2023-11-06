package com.itbulls.learnit.onlinestore.core.facades.impl;

import java.util.PriorityQueue;
import java.util.Queue;

import com.itbulls.learnit.onlinestore.core.facades.HelpDeskFacade;
import com.itbulls.learnit.onlinestore.persistence.enteties.helpdesk.SupportTicket;
import com.itbulls.learnit.onlinestore.persistence.utils.comparators.CustomSupportTicketsComparator;

public class DefaultHelpDeskFacade implements HelpDeskFacade{
	
	private Queue<SupportTicket> tickets = new PriorityQueue<SupportTicket>(new CustomSupportTicketsComparator());
	
	@Override
	public void addNewSupportTicket(SupportTicket supportTicket) {
		if (supportTicket == null)
			return;
		tickets.offer(supportTicket);
		
	}

	@Override
	public SupportTicket getNextSupportTicket() {
		return tickets.poll();
	}

	@Override
	public int getNumberOfTickets() {
		return tickets.size();
	}

}
