package com.streamlined.client.features.modules.type.hud;

import com.streamlined.client.enums.ModuleCategory;
import com.streamlined.client.event.SubscribeEvent;
import com.streamlined.client.event.impl.ACAttackEntityEvent;
import com.streamlined.client.event.impl.ACDamageEntityEvent;
import com.streamlined.client.event.impl.ACUpdateEvent;
import com.streamlined.client.features.modules.utils.DefaultModuleRenderer;

public class ComboCounterModule extends DefaultModuleRenderer {

	private long hitTime = -1;
	private int combo;
	private int possibleTarget;

	public ComboCounterModule() {
		super("Combo Counter", "Display the number of subsequent hits.");
	}
	
	@Override
	public Object getValue() {
		if(combo == 0) {
			return "No combo";
		}
		else {
			return combo + " hit" + ((combo > 1) ? "s" : "");
		}
	}
	
	@Override
	public Object getDummy() {
		return "6 hits";
	}
	
	@SubscribeEvent
	public void onUpdate(ACUpdateEvent event) {
		if((System.currentTimeMillis() - hitTime) > 2000) {
			combo = 0;
		}
	}

	@SubscribeEvent
	public void onEntityAttack(ACAttackEntityEvent event) {
		possibleTarget = event.victim.getEntityId();
	}

	@SubscribeEvent
	public void onEntityDamage(ACDamageEntityEvent event) {
		if(event.entity.getEntityId() == possibleTarget) {
			dealHit();
		}
		else if(event.entity == mc.thePlayer) {
			takeHit();
		}
	}

	public void dealHit() {
		combo++;
		possibleTarget = -1;
		hitTime = System.currentTimeMillis();
	}

	public void takeHit() {
		combo = 0;
	}

	@Override
	public ModuleCategory getCategory() {
		return ModuleCategory.HUD;
	}

}
