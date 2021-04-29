package fr.launcher.nugarium;

import fr.launcher.nugarium.mcping.MinecraftPing;
import fr.launcher.nugarium.mcping.MinecraftPingOptions;
import fr.launcher.nugarium.mcping.MinecraftPingReply;

import java.io.IOException;

public class Ping {

    public static boolean lobby = false;
    public static boolean freebuild = false;
    public static boolean guild = false;

    public static int lobby_player;
    public static int freebuild_player;
    public static int guild_player;



    public static void Ping_lobby() throws IOException {
        MinecraftPingReply data = new MinecraftPing().getPing(new MinecraftPingOptions().setHostname("play.nugarium.com").setPort(25556));
        System.out.println(data.getDescription() + "  --  " + data.getPlayers().getOnline() + "/" + data.getPlayers().getMax());
        lobby = true;
        lobby_player = data.getPlayers().getOnline();
    }

    public static void Ping_freebuild() throws IOException {
        MinecraftPingReply data = new MinecraftPing().getPing(new MinecraftPingOptions().setHostname("play.nugarium.com").setPort(25557));
        System.out.println(data.getDescription() + "  --  " + data.getPlayers().getOnline() + "/" + data.getPlayers().getMax());
        freebuild = true;
        freebuild_player = data.getPlayers().getOnline();

    }
    public static void Ping_guild() throws IOException {
        MinecraftPingReply data = new MinecraftPing().getPing(new MinecraftPingOptions().setHostname("play.nugarium.com").setPort(25559));
        System.out.println(data.getDescription() + "  --  " + data.getPlayers().getOnline() + "/" + data.getPlayers().getMax());
        guild = true;
        guild_player = data.getPlayers().getOnline();
        System.out.println(guild_player);
    }
}
