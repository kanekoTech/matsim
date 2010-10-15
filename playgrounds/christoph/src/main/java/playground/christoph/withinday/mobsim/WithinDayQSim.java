/* *********************************************************************** *
 * project: org.matsim.*
 * WithinDayQSim.java
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 * copyright       : (C) 2007 by the members listed in the COPYING,        *
 *                   LICENSE and WARRANTY file.                            *
 * email           : info at matsim dot org                                *
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *   (at your option) any later version.                                   *
 *   See also COPYING, LICENSE and WARRANTY file                           *
 *                                                                         *
 * *********************************************************************** */

package playground.christoph.withinday.mobsim;

import org.apache.log4j.Logger;
import org.matsim.api.core.v01.Scenario;
import org.matsim.api.core.v01.population.Activity;
import org.matsim.api.core.v01.population.PlanElement;
import org.matsim.core.api.experimental.events.EventsManager;
import org.matsim.core.population.ActivityImpl;
import org.matsim.ptproject.qsim.AgentFactory;
import org.matsim.ptproject.qsim.QSim;
import org.matsim.ptproject.qsim.interfaces.QSimEngineFactory;
import org.matsim.ptproject.qsim.netsimengine.DefaultQSimEngineFactory;

/*
 * This extended QSim contains some methods that
 * are needed for the WithinDay Replanning Modules.
 * 
 * Some other methods are used for the Knowledge Modules. They
 * should be separated somewhen but at the moment this seems
 * to be difficult so they remain here for now...
 */
public class WithinDayQSim extends QSim {

	private final static Logger log = Logger.getLogger(WithinDayQSim.class);
	private WithinDayAgentFactory agentFactory;
	
	public WithinDayQSim(final Scenario scenario, final EventsManager events) {
		this(scenario, events, new DefaultQSimEngineFactory());
	}
	
	public WithinDayQSim(final Scenario scenario, final EventsManager events, QSimEngineFactory factory) {
		super(scenario, events, factory);
		
		// use WithinDayAgentFactory that creates WithinDayPersonAgents who can reset their chachedNextLink
		agentFactory = new WithinDayAgentFactory(this);
		super.setAgentFactory(agentFactory);
	}
	
//	/*
//	 * Used by the Activity End Replanning Module.
//	 * This contains all Agents that are going to end their Activities.
//	 */
//	public PriorityBlockingQueue<PersonAgent> getActivityEndsList() {
//		return super.activityEndsList;
//	}
	
	
	@Override
	public void setAgentFactory(AgentFactory factory) {
		throw new RuntimeException("Please use a WithinDayAgentFactory!");
	}
	
	public void setAgentFactory(WithinDayAgentFactory factory) {
		this.agentFactory = factory;
		super.setAgentFactory(factory);
	}
	
	// yyyy replaced by calls to QSim.getAgents(). Could the QSim use a Map<Id, MobsimAgent> instead of a Collection? christoph, oct'10
//	public Map<Id, PersonAgent> getPersonAgents() {
//		return this.agentFactory.getPersonAgents();
//	}
}
