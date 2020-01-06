package fr.flowarg.launcher.utils;

import fr.flowarg.launcher.Main;

@SuppressWarnings("unused")
public interface Constants
{
    String USER_HOME = System.getProperty("user.home") + "\\";
    String APP_DATA = USER_HOME + "AppData\\";
    String TEMP_DIR = APP_DATA + "Local\\Temp\\";
    String ROAMING_DIR = APP_DATA + "Roaming\\";
    String COMMON = Main.GAME_DIR + "\\common\\";
    String LIBS = COMMON + "libraries\\";
    String MODS = Main.GAME_DIR + "\\mods\\";
    String NATIVES = COMMON + "natives\\";
    String ASSETS = COMMON + "assets\\";
    String OBJECTS = ASSETS + "objects\\";
    String OS = System.getProperty("os.name");
    String JRE_CUSTOM_DIR = Main.GAME_DIR + "\\runtime\\x64\\";
    String LAUNCHER_LOGS_DIR = Main.GAME_DIR + "\\launcher-log\\";
}
