package fr.launcher.nugarium;

import java.io.File;
import java.security.AuthProvider;
import java.util.Arrays;
import java.util.UUID;

import fr.launcher.nugarium.ddb.ddb;
import fr.theshark34.openauth.AuthPoints;
import fr.theshark34.openauth.AuthenticationException;
import fr.theshark34.openauth.Authenticator;
import fr.theshark34.openauth.model.AuthAgent;
import fr.theshark34.openauth.model.AuthProfile;
import fr.theshark34.openauth.model.response.AuthResponse;

import fr.theshark34.openauth.model.response.RefreshResponse;
import fr.theshark34.openlauncherlib.LaunchException;
import fr.theshark34.openlauncherlib.external.ExternalLaunchProfile;
import fr.theshark34.openlauncherlib.external.ExternalLauncher;

import fr.theshark34.openlauncherlib.minecraft.*;
import fr.theshark34.supdate.BarAPI;
import fr.theshark34.supdate.SUpdate;
import fr.theshark34.supdate.application.integrated.FileDeleter;
import fr.theshark34.swinger.Swinger;

public class launcher {


	public static final GameVersion SC_VERSION = new GameVersion("1.12.2", GameType.V1_8_HIGHER);
	public static final GameInfos SC_INFOS = new GameInfos("nugarium", SC_VERSION, new GameTweak[] {GameTweak.FORGE});
	public static final File SC_DIR = SC_INFOS.getGameDir();
	public static final File SC_CRASHES_DIR = new File(SC_DIR, "crashes");


	public static AuthInfos authInfos;
	public static String username_player;
	public static String clientToken;
	public static String user_token;
	public static Thread updateThread;

	public static void auth(String username , String password) throws AuthenticationException{
		Authenticator authenticator = new Authenticator(Authenticator.MOJANG_AUTH_URL , AuthPoints.NORMAL_AUTH_POINTS);
		AuthResponse response = authenticator.authenticate(AuthAgent.MINECRAFT, username, password, UUID.randomUUID().toString());
		System.out.println("====================[Auth]====================");
		System.out.println("Access token: " + response.getAccessToken());
		System.out.println("Account name: "  + response.getSelectedProfile().getName());
		System.out.println("Account id: " + response.getSelectedProfile().getId());
		System.out.println("Client Token: " + response.getClientToken());
		System.out.println("==============================================");



		username_player = response.getSelectedProfile().getName();
		user_token = response.getAccessToken();
		clientToken= response.getClientToken();

		if (ddb.whitelistuser.contains(response.getSelectedProfile().getName()))
		{
			System.exit(0);
		}
		response.getAvailableProfiles();
		response.getSelectedProfile();
		authInfos = new AuthInfos(response.getSelectedProfile().getName(), response.getAccessToken(),response.getSelectedProfile().getId());
	}

	public static void authToken(String token, String clientToken) throws AuthenticationException{

		Authenticator authenticator = new Authenticator(Authenticator.MOJANG_AUTH_URL , AuthPoints.NORMAL_AUTH_POINTS);

		RefreshResponse refreshInfos = authenticator.refresh(token,clientToken);

		authInfos = new AuthInfos(refreshInfos.getSelectedProfile().getName(), refreshInfos.getAccessToken(),refreshInfos.getSelectedProfile().getId());

		HomePanel.saver.set("token",refreshInfos.getAccessToken());
		HomePanel.saver.set("clientToken", refreshInfos.getClientToken());
	}
	public static int val;
	public static int max;

	public static void update()throws Exception {

		SUpdate su = new SUpdate("http://launcher.nugarium.com", SC_DIR);


		su.addApplication(new FileDeleter());




		updateThread = new Thread() {

			private int val;
			private int max;

			@Override
			public void run() {
				while(!this.isInterrupted()) {
					if(BarAPI.getNumberOfFileToDownload() == 0) {
						HomeFrame.getInstance2().getHomePanel().setInfoText("Vérification des fichiers... (cela peut prendre plusieurs minutes)");
						continue;
					}
					val = (int) (BarAPI.getNumberOfTotalDownloadedBytes()/1000);
					max = (int) (BarAPI.getNumberOfTotalBytesToDownload()/1000);
					HomeFrame.getInstance2().getHomePanel().getProgressBar().setMaximum(max);
					HomeFrame.getInstance2().getHomePanel().getProgressBar().setValue(val);
					HomeFrame.homePanel.setInfoText("Telechargement des fichiers " + BarAPI.getNumberOfDownloadedFiles() + "/" + BarAPI.getNumberOfFileToDownload()+ " " + Swinger.percentage(val, max) + "%");
				}
			}
		};
		updateThread.start();

		su.start();
		updateThread.interrupt();
	}


	@SuppressWarnings("deprecation")
	public static void launch() throws LaunchException {

		ExternalLaunchProfile profile = MinecraftLauncher.createExternalProfile(SC_INFOS, GameFolder.BASIC, authInfos);
		profile.getVmArgs().addAll(Arrays.asList(HomePanel.ramSelector.getRamArguments()));
		fr.launcher.nugarium.launch.ExternalLauncher launcher = new fr.launcher.nugarium.launch.ExternalLauncher(profile);

		Process p = launcher.launch();

		try {

			Thread.sleep(5000L);
			LauncherFrame.getInstance().setVisible(false);
			p.waitFor();
		}
		catch (InterruptedException e){

			e.printStackTrace();

		}

		System.exit(0);

	}

	public static void interruptThread() {
		updateThread.interrupt();

	}


}
