/*
 * This file is part of aion-emu <aion-emu.com>.
 *
 *  aion-emu is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  aion-emu is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with aion-emu.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.aionemu.gameserver.controllers;

import com.aionemu.gameserver.model.gameobjects.AionObject;
import com.aionemu.gameserver.model.gameobjects.Monster;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.group.PlayerGroup;
import com.aionemu.gameserver.network.aion.serverpackets.SM_LOOT_STATUS;
import com.aionemu.gameserver.questEngine.QuestEngine;
import com.aionemu.gameserver.questEngine.model.QuestEnv;
import com.aionemu.gameserver.services.DropService;
import com.aionemu.gameserver.services.GroupService;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.utils.stats.StatFunctions;
import com.aionemu.gameserver.world.World;
import com.aionemu.gameserver.world.WorldType;

/**
 * @author ATracer, Sarynth
 */
public class MonsterController extends NpcController
{
	@Override
	public void doReward()
	{
		AionObject winner = getOwner().getAggroList().getMostDamage(); 
		
		if(winner == null)
			return;
		
		// TODO: Split the EXP based on overall damage.
		
		if (winner instanceof PlayerGroup)
		{
			GroupService.getInstance().doReward((PlayerGroup)winner, getOwner());
			
			Player leader = ((PlayerGroup)winner).getGroupLeader();
			
			// Give Drop
			DropService.getInstance().registerDrop(getOwner(), leader);
			PacketSendUtility.broadcastPacket(this.getOwner(), new SM_LOOT_STATUS(this.getOwner().getObjectId(), 0));
		}
		else if (((Player)winner).isInGroup())
		{
			GroupService.getInstance().doReward(((Player)winner).getPlayerGroup(), getOwner());
			
			// Give Drop
			DropService.getInstance().registerDrop(getOwner(), (Player)winner);
			PacketSendUtility.broadcastPacket(this.getOwner(), new SM_LOOT_STATUS(this.getOwner().getObjectId(), 0));
		}
		else
		{
			super.doReward();
			
			Player player = (Player)winner;
			
			// Exp reward
			long expReward = StatFunctions.calculateSoloExperienceReward(player, getOwner());
			player.getCommonData().addExp(expReward);

			// DP reward
			int currentDp = player.getCommonData().getDp();
			int dpReward = StatFunctions.calculateSoloDPReward(player, getOwner());
			player.getCommonData().setDp(dpReward + currentDp);
			
			// AP reward
			WorldType worldType = World.getInstance().getWorldMap(player.getWorldId()).getWorldType();
			if(worldType == WorldType.ABYSS)
			{
				int apReward = StatFunctions.calculateSoloAPReward(player, getOwner());
				player.getCommonData().addAp(apReward);
			}
			
			QuestEngine.getInstance().onKill(new QuestEnv(getOwner(), player, 0 , 0));
			
			// Give Drop
			DropService.getInstance().registerDrop(getOwner() , player);			
			PacketSendUtility.broadcastPacket(this.getOwner(), new SM_LOOT_STATUS(this.getOwner().getObjectId(), 0));
		}
	}
	
	@Override
	public void onRespawn()
	{
		super.onRespawn();
		DropService.getInstance().unregisterDrop(getOwner());
	}

	@Override
	public Monster getOwner()
	{
		return (Monster) super.getOwner();
	}
}