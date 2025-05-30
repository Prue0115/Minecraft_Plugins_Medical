package me.tacticalmedic;

import me.tacticalmedic.listeners.DamageListener;
import me.tacticalmedic.listeners.InteractionListener;
import me.tacticalmedic.listeners.PlayerListener;
import me.tacticalmedic.model.PlayerMedicalState;
import me.tacticalmedic.treatment.BleedingTask;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TacticalMedic extends JavaPlugin {

    private static TacticalMedic instance;
    private final Map<UUID, PlayerMedicalState> medicalStates = new HashMap<>();

    public static TacticalMedic getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        // 리스너 등록
        getServer().getPluginManager().registerEvents(new DamageListener(), this); // 피해 이벤트 처리
        getServer().getPluginManager().registerEvents(new InteractionListener(), this); // 상호작용 이벤트 처리
        getServer().getPluginManager().registerEvents(new PlayerListener(), this); // 리스폰 이벤트 처리

        // 출혈 체크 태스크
        new BleedingTask().runTaskTimer(this, 20, 20); // 1초마다 반복

        getLogger().info("🩺[TacticalMedic] 플러그인 활성화 완료.");
    }

    @Override
    public void onDisable() {
        getLogger().info("🧠[TacticalMedic] 플러그인 비활성화 완료.");
    }

    // 상태 관리 유틸

    public void createState(UUID uuid) {
        medicalStates.put(uuid, new PlayerMedicalState(uuid));
    }

    public PlayerMedicalState getState(UUID uuid) {
        return medicalStates.get(uuid);
    }

    public void removeState(UUID uuid) {
        medicalStates.remove(uuid);
    }

    public boolean hasState(UUID uuid) {
        return medicalStates.containsKey(uuid);
    }

}
