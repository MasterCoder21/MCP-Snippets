package com.streamlined.client.event.impl;

import com.streamlined.client.event.SCEvent;

import net.minecraft.entity.Entity;

public class SCAttackEntityEvent extends SCEvent {

	public final Entity attacker;
	public final Entity victim;
	
	public SCAttackEntityEvent(Entity attacker, Entity victim) {
		this.attacker = attacker;
		this.victim = victim;
	}
	
}
