package com.streamlined.client.features.modules.type.hud;

import com.streamlined.client.enums.ModuleCategory;
import com.streamlined.client.event.SubscribeEvent;
import com.streamlined.client.event.impl.SCAttackEntityEvent;
import com.streamlined.client.event.impl.SCDamageEntityEvent;
import com.streamlined.client.event.impl.SCpdateEvent;
import com.streamlined.client.features.modules.utils.SCDefaultRenderModule;

public class ComboCounterModule extends SCDefaultRenderModule {

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
	public Object getDummyValue() {
		return "6 hits";
	}
	
	@SubscribeEvent
	public void onUpdate(SCUpdateEvent event) {
		if((System.currentTimeMillis() - hitTime) > 2000) {
			combo = 0;
		}
	}

	@SubscribeEvent
	public void onEntityAttack(SCAttackEntityEvent event) {
		possibleTarget = event.victim.getEntityId();
	}

	@SubscribeEvent
	public void onEntityDamage(SCDamageEntityEvent event) {
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
