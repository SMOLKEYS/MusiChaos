package musi

import arc.Core
import arc.util.Log
import arc.files.Fi
import arc.audio.Music
import arc.struct.*
import mindustry.Vars
import com.github.mnemotechnician.mkui.delegates.setting

@Suppress("MemberVisibilityCanBePrivate", "SpellCheckingInspection")
object MusiVars {
    val syn = "musichaos-"
    
    var includeUnspecifiedTracks by setting(false, syn)
    
    //cache for builtin tracks
    val ambientMusicCache = Seq<Music>()
    val darkMusicCache = Seq<Music>()
    val bossMusicCache = Seq<Music>()
    
    val customTrackCache = ObjectMap<Fi, Music?>()
    
    private fun tryMusicInit(file: Fi): Music?{
        val mus: Music? = null
        
        try{
           mus = if(customTrackCache.containsKey(file)) customTrackCache[file] else Music(file)
        }catch(err: Exception){
            Log.err("Failed to initialize music file: $file", err)
        }
        
        if(!customTrackCache.containsKey(file) && mus != null) customTrackCache.put(file, mus)
        
        return mus
    }
    
    fun load(){
        ambientMusicCache.add(Vars.control.sound.ambientMusic)
        darkMusicCache.add(Vars.control.sound.darkMusic)
        bossMusicCache.add(Vars.control.sound.bossMusic)
    }
    
    fun handleTracks() {
        val path = Core.settings.dataDirectory.child("musichaos")
        val sounds = Vars.control.sound
        
        if(!path.exists()) path.mkdirs()
        
        sounds.ambientMusic.clear().add(ambientMusicCache)
        sounds.darkMusic.clear().add(darkMusicCache)
        sounds.bossMusic.clear().add(bossMusicCache)
        
        path.walk{
            when{
                it.name().startsWith("ambientmus-") -> {
                    
                    val ambi = tryMusicInit(it)
                    
                    if(ambi != null) sounds.ambientMusic.add(ambi)
                }
                
                it.name().startsWith("darkmus-") -> {
                    
                    val dark = tryMusicInit(it)
                    
                    if(dark != null) sounds.darkMusic.add(dark)
                }
                
                it.name().startsWith("bossmus-") -> {
                    
                    val boss = tryMusicInit(it)
                    
                    if(boss != null) sounds.bossMusic.add(boss)
                }
                
                else -> {
                    Log.info("Unspecified music file: ${it.name()}")
                    
                    if(includeUnspecifiedTracks){
                        val unspec = tryMusicInit(it)
                        val builtins = arrayOf(sounds.ambientMusic, sounds.darkMusic, sounds.bossMusic)
                        
                        builtins.random().add(unspec)
                    }
                }
            }
        }
    }
}