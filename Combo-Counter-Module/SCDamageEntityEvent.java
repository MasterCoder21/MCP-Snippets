package com.streamlined.client.event.impl;

import com.streamlined.client.event.SCEvent;

import net.minecraft.entity.Entity;

public class SCDamageEntityEvent extends SCEvent {

	public Entity entity;
	
	public SCDamageEntityEvent(Entity entity) {
		this.entity = entity;
	}

}
