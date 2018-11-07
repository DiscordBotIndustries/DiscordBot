package main;

import events.AdminCommands;
import events.Risk.RiskGame;
import events.TextResponse.TextResponder;
import events.funCommands;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;

public class Main {

    private static JDA jda;

    public static void main(String[] args) throws Exception {
        jda = new JDABuilder(AccountType.BOT)
                .setToken("NTAyNjIzNTgxNjUwNTUwNzg0.Dr1J4Q.kSClvf_E9ahsDQEtmXYLRCBoh5M")
                .buildBlocking();
        jda.addEventListener(new funCommands());
        jda.addEventListener(new AdminCommands());
        jda.addEventListener(new TextResponder());
        jda.addEventListener(new RiskGame());
    }
}
