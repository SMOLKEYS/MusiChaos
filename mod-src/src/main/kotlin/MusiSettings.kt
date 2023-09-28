package musi

import com.github.mnemotechnician.mkui.extensions.dsl.textButton
import mindustry.Vars
import mindustry.ui.dialogs.SettingsMenuDialog
import mindustry.ui.dialogs.SettingsMenuDialog.SettingsTable
import mindustry.ui.dialogs.SettingsMenuDialog.SettingsTable.Setting

object MusiSettings {
    fun load(){
        Vars.ui.settings.addCategory("MusiChaos"){t ->
            t.checkPref("includeunspecifiedtracks", false){
                MusiVars.includeUnspecifiedTracks = it
            }
        
            t.buttonPref("reloadtracks"){
                MusiVars.handleTracks()
            }
        }
    }

    fun SettingsTable.buttonPref(name: String, onClick: () -> Unit) = this.pref(ButtonSetting(name, onClick))

    open class ButtonSetting(name: String, var onClick: () -> Unit): Setting(name){

        override fun add(table: SettingsMenuDialog.SettingsTable) {
            table.textButton(title, wrap = false){
                onClick()
            }.growX().row()
        }
    }
}