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
package com.aionemu.gameserver.utils.guice;

import com.aionemu.commons.services.ScriptService;
import com.aionemu.gameserver.ShutdownHook;
import com.aionemu.gameserver.dataholders.DataManager;
import com.aionemu.gameserver.dataholders.ItemData;
import com.aionemu.gameserver.dataholders.ItemSetData;
import com.aionemu.gameserver.dataholders.PlayerExperienceTable;
import com.aionemu.gameserver.dataholders.SkillData;
import com.aionemu.gameserver.dataholders.WalkerData;
import com.aionemu.gameserver.services.ChatService;
import com.aionemu.gameserver.services.PlayerService;
import com.aionemu.gameserver.services.ServiceProxy;
import com.aionemu.gameserver.services.SocialService;
import com.aionemu.gameserver.services.TeleportService;
import com.aionemu.gameserver.utils.chathandlers.ChatHandlers;
import com.aionemu.gameserver.utils.chathandlers.ChatHandlersFactory;
import com.google.inject.AbstractModule;
import com.google.inject.Injector;
import com.google.inject.Provides;
import com.google.inject.Scopes;
import com.google.inject.Singleton;

/**
 * This is a configuration module for <tt>Injector</tt> that is used in aion-emu.<br>
 * 
 * @author Luno
 * 
 */
public class DataInjectionModule extends AbstractModule
{
	private Injector	injector;

	public void setInjector(Injector injector)
	{
		this.injector = injector;
	}

	@Override
	protected void configure()
	{
		bind(ShutdownHook.class).in(Scopes.SINGLETON);	
		bind(DataManager.class).asEagerSingleton();
		bind(PlayerService.class).in(Scopes.SINGLETON);
		bind(SocialService.class).in(Scopes.SINGLETON);
		bind(ScriptService.class).in(Scopes.SINGLETON);
		bind(TeleportService.class).in(Scopes.SINGLETON);
		bind(ServiceProxy.class).in(Scopes.SINGLETON);
		bind(ChatService.class).asEagerSingleton();
	}
	
	@SuppressWarnings("static-access")
	@Provides
	ItemData provideItemData(DataManager datamanager)
	{
		return datamanager.ITEM_DATA;
	}
	
	@Provides
	@Singleton
	ChatHandlers provideChatHandlers()
	{
		return new ChatHandlersFactory(injector).createChatHandlers();
	}
	
	@SuppressWarnings("static-access")
	@Provides
	SkillData provideSkillData(DataManager datamanager)
	{
		return datamanager.SKILL_DATA;
	}
	
	@SuppressWarnings("static-access")
	@Provides
	WalkerData provideWalkerData(DataManager datamanager)
	{
		return datamanager.WALKER_DATA;
	}

	@SuppressWarnings("static-access")
	@Provides
	PlayerExperienceTable providePlayerExpTable(DataManager datamanager)
	{
		return datamanager.PLAYER_EXPERIENCE_TABLE;
	}
	
	@SuppressWarnings("static-access")
	@Provides
	ItemSetData provideItemSetData(DataManager datamanager)
	{
		return datamanager.ITEM_SET_DATA;
	}
}
