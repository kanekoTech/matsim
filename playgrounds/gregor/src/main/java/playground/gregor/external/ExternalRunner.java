/* *********************************************************************** *
 * project: org.matsim.*
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 * copyright       : (C) 2015 by the members listed in the COPYING,        *
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

package playground.gregor.external;

import org.matsim.api.core.v01.Scenario;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.controler.Controler;
import org.matsim.core.scenario.ScenarioUtils;

public class ExternalRunner {

	public static void main(String[] args) {
		String config = "/Users/laemmel/devel/external/input/config.xml";
		Config c = ConfigUtils.createConfig();
		ConfigUtils.loadConfig(c, config);
		c.controler().setWriteEventsInterval(1);

		Scenario sc = ScenarioUtils.loadScenario(c);
		// sc.addScenarioElement(Sim2DScenario.ELEMENT_NAME, sim2dsc);

		// c.qsim().setEndTime(120);
		// c.qsim().setEndTime(23*3600);
		// c.qsim().setEndTime(41*60);//+30*60);

		Controler controller = new Controler(sc);

		controller.setOverwriteFiles(true);
		HybridExternalMobsimFactory fac = new HybridExternalMobsimFactory();
		controller.addMobsimFactory("extsim", fac);

		controller.run();
	}
}