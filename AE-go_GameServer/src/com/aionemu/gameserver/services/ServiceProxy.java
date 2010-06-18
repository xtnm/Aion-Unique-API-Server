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
package com.aionemu.gameserver.services;

import com.aionemu.gameserver.spawnengine.SpawnEngine;
import com.google.inject.Inject;

/**
 * This service is used in object controllers as injecting all services one by one is overhead.
 * 
 * @author ATracer
 * 
 */
public class ServiceProxy
{
	@Inject
	private LegionService			legionService;
	@Inject
	private RespawnService			respawnService;
	@Inject
	private TeleportService			teleportService;
	@Inject
	private CraftService			craftService;
	@Inject
	private PlayerService			playerService;
	@Inject
	private SpawnEngine				spawnEngine;
	@Inject
	private KiskService				kiskService;

	/**
	 * @return the legionService
	 */
	public LegionService getLegionService()
	{
		return legionService;
	}

	/**
	 * @return the respawnService
	 */
	public RespawnService getRespawnService()
	{
		return respawnService;
	}

	/**
	 * @return the teleportService
	 */
	public TeleportService getTeleportService()
	{
		return teleportService;
	}

	/**
	 * 
	 * @return craftService
	 */
	public CraftService getCraftService()
	{
		return craftService;
	}

	/**
	 * @return the playerService
	 */
	public PlayerService getPlayerService()
	{
		return playerService;
	}

	/**
	 * @return the spawnEngine
	 */
	public SpawnEngine getSpawnEngine()
	{
		return spawnEngine;
	}
	
	/**
	 * @return the kiskService
	 */
	public KiskService getKiskService()
	{
		return kiskService;
	}
}
