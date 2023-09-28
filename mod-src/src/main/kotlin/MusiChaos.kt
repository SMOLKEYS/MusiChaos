package meta

import arc.Events
import arc.util.Timer
import mindustry.Vars
import mindustry.game.EventType.ClientLoadEvent
import mindustry.mod.*

class MusiChaos : Mod() {

	init {
		Events.on(ClientLoadEvent::class.java) {
			MusiVars.load()
			MusiVars.handleTracks()
			
			MusiSettings.load()
		}
	}
}
