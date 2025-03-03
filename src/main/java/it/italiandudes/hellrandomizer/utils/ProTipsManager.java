package it.italiandudes.hellrandomizer.utils;

import org.jetbrains.annotations.NotNull;

public final class ProTipsManager {

    // Pro Tips
    private static final String[] PRO_TIPS = new String[]{
            "Ricordati di dare un'occhiata alle impostazioni per personalizzare la tua esperienza in gioco.",
            "Qui dovrebbe esserci una pro tip, utile vero?",
            "Ti e' venuta un'idea per questa app? Vai al menu principale clicca sul pulsante \"Segnala un Problema o Proponi un'Idea\" per ottenere il link al GitHub per propormi la tua idea!\nSe e' fattibile e interessante verra' implementata!",
            "Lo sapevi che lo sviluppatore di questa app su Discord si chiama \"hacker6329_hd\"?",
            "Siamo realisti, questa app e' stata sviluppata da una persona sola, ed e' pure gratuita con codice disponibile su GitHub!\nItalianDudes e' tanta roba!",
            "Sai che questa applicazione funziona su tutti i dispositivi in cui Java 8 e' disponibile? E Java 8 e' disponibile per TANTI dispositivi!",
            "INSERT INTO player (happiness) VALUES (OVER9000!!!);",
            "Eliminando importanti file di sistema....... SCHERZAVO!!!",
            "Ho DECISAMENTE bisogno di un caffe'...",
            "Ho DECISAMENTE bisogno di rimettere a posto i miei orari di sonno...",
            "Dici che e' preoccupante che molto del codice di questa app e' stato scritto di notte tra le 12 e le 6 di mattina?",
    };

    // Tips Getter
    @NotNull
    public static String getRandomProTip() {
        return PRO_TIPS[Randomizer.randomBetween(0, PRO_TIPS.length)];
    }
}
