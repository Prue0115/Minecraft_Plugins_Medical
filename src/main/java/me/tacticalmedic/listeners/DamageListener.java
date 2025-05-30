package me.tacticalmedic.listeners;

import me.tacticalmedic.TacticalMedic;
import me.tacticalmedic.model.BodyPart;
import me.tacticalmedic.model.BleedingStage;
import me.tacticalmedic.model.PlayerMedicalState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.List;
import java.util.Random;
import java.util.UUID;

public class DamageListener implements Listener {

    private final Random random = new Random();

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;

        double damage = event.getDamage();
        UUID uuid = player.getUniqueId();
        TacticalMedic plugin = TacticalMedic.getInstance();

        // 상태가 없으면 생성
        if (!plugin.hasState(uuid)) {
            plugin.createState(uuid);
        }

        PlayerMedicalState state = plugin.getState(uuid);
        if (state == null) return;

        // 부위 무작위 선택
        BodyPart part = getRandomLimb();

        // 출혈 확률 적용 (30%)
        if (random.nextDouble() < 0.3) {
            BleedingStage stage = BleedingStage.LIGHT;
            state.setBleedingStage(part, stage);
            player.sendMessage("§c[출혈] " + part.getDisplay() + " 부위에서 출혈 발생!");
        }

        // 골절 판정 (피해량 6.0 이상)
        if (damage >= 6.0 && !state.isFractured(part)) {
            state.setFracture(part, true);
            player.sendMessage("§6[골절] " + part.getDisplay() + " 부위 골절 발생!");
        }

        // 통증 누적
        state.addPain((int) Math.round(damage / 2));
    }

    // 팔다리 중 무작위 선택
    private BodyPart getRandomLimb() {
        List<BodyPart> limbs = List.of(
                BodyPart.ARM_LEFT,
                BodyPart.ARM_RIGHT,
                BodyPart.LEG_LEFT,
                BodyPart.LEG_RIGHT
        );
        return limbs.get(random.nextInt(limbs.size()));
    }
}
