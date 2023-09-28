package musi

import arc.Events
import arc.util.Time
import mindustry.Vars
import mindustry.game.EventType.ClientLoadEvent
import mindustry.mod.*

class MusiChaos : Mod() {

	init {
		Events.on(ClientLoadEvent::class.java) {
			Time.run(120f){
			    MusiVars.load()
			    MusiVars.handleTracks()
			}
			
			MusiSettings.load()
		}
	}
}
