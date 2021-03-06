package Base;


import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@SuppressWarnings("ALL")
//Imported from andrepet

public class MP3Player {

    private final Map<String, MediaPlayer> audioMap;
    private final JFXPanel fxPanel;

    public MP3Player() {
        audioMap = new ConcurrentHashMap<>();
        fxPanel = new JFXPanel(); // need to create one instance
    }

    private void setLoop(MediaPlayer mediaPlayer, boolean loop) {
        if (mediaPlayer == null) {
            return;
        }
        if (loop) {
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        } else {
            mediaPlayer.setCycleCount(1);
        }
    }

    private boolean isLoop(MediaPlayer mediaPlayer) {
        if (mediaPlayer == null) {
            return false;
        }
        return mediaPlayer.getCycleCount() == MediaPlayer.INDEFINITE;
    }

    public void play(String filepath) {
        play(filepath, false);
    }

    public void play(String filepath, boolean repeat) {
        try {
            MediaPlayer mediaPlayer;
            if (audioMap.containsKey(filepath)) {
                mediaPlayer = audioMap.get(filepath);
                this.setLoop(mediaPlayer, repeat);
            } else {
                mediaPlayer = new MediaPlayer(new Media(new File(filepath).toURI().toString()));
                this.setLoop(mediaPlayer, repeat);
                audioMap.put(filepath, mediaPlayer);
            }
            mediaPlayer.seek(Duration.ZERO);
            mediaPlayer.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public boolean isRepeatMode(String filepath) {
        try {
            if (audioMap.containsKey(filepath)) {
                return this.isLoop(audioMap.get(filepath));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void stop(String filepath) {
        try {
            if (audioMap.containsKey(filepath)) {
                MediaPlayer mediaPlayer = audioMap.get(filepath);
                mediaPlayer.pause();
                mediaPlayer.seek(Duration.ZERO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopAll() {
        Set<String> keys = audioMap.keySet();
        keys.forEach(this::stop);
    }

    public void pause(String filepath) {
        try {
            if (audioMap.containsKey(filepath)) {
                MediaPlayer mediaPlayer = audioMap.get(filepath);
                mediaPlayer.pause();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pauseAll() {
        Set<String> keys = audioMap.keySet();
        keys.forEach(this::pause);
    }

    public void resume(String filepath) {
        try {
            if (audioMap.containsKey(filepath)) {
                MediaPlayer mediaPlayer = audioMap.get(filepath);
                mediaPlayer.play();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void resumeAll() {
        Set<String> keys = audioMap.keySet();
        keys.forEach(this::resume);
    }

    public void playFX(String filepath) {
        try {
            MediaPlayer mediaPlayer = new MediaPlayer(new Media(new File(filepath).toURI().toString()));
            mediaPlayer.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}