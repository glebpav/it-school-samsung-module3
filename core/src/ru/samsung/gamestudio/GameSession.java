package ru.samsung.gamestudio;

import com.badlogic.gdx.utils.TimeUtils;


public class GameSession {

    long nextEnemySpawnTime;
    long sessionStartTime;

    public GameSession() {
    }

    public void startGame() {
        sessionStartTime = TimeUtils.millis();
        nextEnemySpawnTime = sessionStartTime + (long) (GameSettings.STARTING_ENEMY_APPEARANCE_COOL_DOWN
                * getEnemyPeriodCoolDown());
    }

    public boolean shouldSpawnTrash() {
        if (nextEnemySpawnTime <= TimeUtils.millis()) {
            nextEnemySpawnTime = TimeUtils.millis() + (long) (GameSettings.STARTING_ENEMY_APPEARANCE_COOL_DOWN
                    * getEnemyPeriodCoolDown());
            return true;
        }
        return false;
    }

    private float getEnemyPeriodCoolDown() {
        return (float) Math.exp(-0.001 * (TimeUtils.millis() - sessionStartTime + 1) / 1000);
    }
}
