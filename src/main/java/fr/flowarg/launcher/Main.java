package fr.flowarg.launcher;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import fr.flowarg.launcher.downloader.Downloader;
import fr.flowarg.launcher.gui.Frame;
import fr.flowarg.launcher.gui.Panel;
import fr.flowarg.launcher.updater.Updater;
import fr.litarvan.openauth.AuthPoints;
import fr.litarvan.openauth.AuthenticationException;
import fr.litarvan.openauth.Authenticator;
import fr.litarvan.openauth.model.AuthAgent;
import fr.litarvan.openauth.model.response.AuthResponse;
import fr.theshark34.openlauncherlib.LaunchException;
import fr.theshark34.openlauncherlib.external.ExternalLaunchProfile;
import fr.theshark34.openlauncherlib.external.ExternalLauncher;
import fr.theshark34.openlauncherlib.minecraft.AuthInfos;
import fr.theshark34.openlauncherlib.minecraft.GameFolder;
import fr.theshark34.openlauncherlib.minecraft.GameInfos;
import fr.theshark34.openlauncherlib.minecraft.GameTweak;
import fr.theshark34.openlauncherlib.minecraft.GameType;
import fr.theshark34.openlauncherlib.minecraft.GameVersion;
import fr.theshark34.openlauncherlib.minecraft.MinecraftLauncher;
import fr.theshark34.openlauncherlib.util.CrashReporter;
import fr.theshark34.swinger.Swinger;

public class Main
{
	public static final String ACTUAL_VERSION = "1.2.2";
	public static final GameVersion VERSION = new GameVersion("1.12.2", GameType.V1_8_HIGHER);
	public static final GameInfos INFOS = new GameInfos("gunsofchickens-modpack", VERSION, new GameTweak[] {GameTweak.FORGE});
	public static final File GAME_DIR = INFOS.getGameDir();
	public static final File LAUNCHER_CRASHS = new File(GAME_DIR + "\\logs");
	public static final GameFolder LAUNCH_DIR = new GameFolder("common\\assets", "common\\libraries", "common\\natives", "common\\versions\\1.12.2\\1.12.2.jar");
	private static AuthInfos authInfos;
	public static CrashReporter crashReporter = new CrashReporter("Launcher - Modpack Guns of Chickens", Main.LAUNCHER_CRASHS);
	
	public static void main(String[] args)
	{
		System.out.println("Launching launcher...");
		Swinger.setResourcePath("/assets/");
		Swinger.setSystemLookNFeel();
		System.out.println("Verifying available updates...");
		try {
			Updater.start();
		} catch (IOException | NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		}
		System.out.println("Initializing launcher..");
		Downloader downloader = new Downloader();
		downloader.init();
		System.out.println("Launching Window for " + System.getProperty("os.name") + " os.");
		Frame frame = new Frame("Launcher By FlowArg");
		frame.setVisible(true);
	}
	
	public static void auth(String username, String password) throws AuthenticationException
	{
		System.out.println("Authentication...");
		Authenticator authenticator = new Authenticator(Authenticator.MOJANG_AUTH_URL, AuthPoints.NORMAL_AUTH_POINTS);
		AuthResponse response = authenticator.authenticate(AuthAgent.MINECRAFT, username, password, "");
		authInfos = new AuthInfos(response.getSelectedProfile().getName(), response.getAccessToken(), response.getSelectedProfile().getId());
	}
	
	public static void launch() throws LaunchException, InterruptedException
	{
		ExternalLaunchProfile profile = MinecraftLauncher.createExternalProfile(INFOS, LAUNCH_DIR, authInfos);
		ExternalLauncher launcher = new ExternalLauncher(profile);
		
		profile.getVmArgs().addAll(Arrays.asList(Panel.getRamSelector().getRamArguments()));
		
		Process p = launcher.launch();
		Thread.sleep(5000L);
		Frame.getInstance().setVisible(false);
		p.waitFor();
		exit(0);
	}
	
	public static void exit(int status)
	{
		System.out.println("Exit with exit code " + status + ".");
		if(!Downloader.FILE_NAME.isEmpty()) Downloader.FILE_NAME.clear();
		if(!Downloader.LINK_OF_FILES.isEmpty()) Downloader.LINK_OF_FILES.clear();
		System.exit(status);
	}
}