package me.tacticalmedic.util;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoggerUtil {

    private static final File logDir = new File("plugins/TacticalMedic/logs");

    public static void log(String message) {
        if (!logDir.exists()) {
            boolean created = logDir.mkdirs();
            if (!created) {
                System.err.println("[TacticalMedic] 로그 디렉토리 생성 실패");
                return;
            }
        }

        String fileName = new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + ".log";
        File file = new File(logDir, fileName);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write("[" + getTimeStamp() + "] " + message + "\n");
        } catch (IOException e) {
            System.err.println("[TacticalMedic] 로그 파일 쓰기 실패: " + e.getMessage());
        }
    }

    private static String getTimeStamp() {
        return new SimpleDateFormat("HH:mm:ss").format(new Date());
    }
}
