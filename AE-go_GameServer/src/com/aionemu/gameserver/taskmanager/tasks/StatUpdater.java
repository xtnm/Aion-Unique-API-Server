/*
 * This file is part of aion-unique <aion-unique.org>.
 *
 *  aion-unique is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  aion-unique is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with aion-unique.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.aionemu.gameserver.taskmanager.tasks;

import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.taskmanager.AbstractIterativePeriodicTaskManager;

/**
 * @author lord_rex
 *
 */
public class StatUpdater extends AbstractIterativePeriodicTaskManager<Creature>
{
	private static final class SingletonHolder
	{
		private static final StatUpdater INSTANCE	= new StatUpdater();
	}

	public static StatUpdater getInstance()
	{
		return SingletonHolder.INSTANCE;
	}
	
	protected StatUpdater()
	{
		super(500);
	}

	/* (non-Javadoc)
	 * @see com.aionemu.gameserver.taskmanager.AbstractIterativePeriodicTaskManager#callTask(java.lang.Object)
	 */
	@Override
	protected void callTask(Creature task)
	{
		task.getGameStats().recomputeStatsImpl();
		
		stopTask(task);
	}

	/* (non-Javadoc)
	 * @see com.aionemu.gameserver.taskmanager.AbstractIterativePeriodicTaskManager#getCalledMethodName()
	 */
	@Override
	protected String getCalledMethodName()
	{
		return "recomputeStats()";
	}
}
