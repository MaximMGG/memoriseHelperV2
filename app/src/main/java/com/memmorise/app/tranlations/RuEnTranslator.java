package com.memmorise.app.tranlations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class RuEnTranslator implements Translator{

    private static final String URL_RU_EN_TRANSLATIONS = "https://context.reverso.net/перевод/английский-русский/";
    private static final String URL_GOOGLE_CHECK = "https://www.google.com/search?q=";


    @Override
    public List<String> getTranclations(String word) throws IOException {
        List<String> list = new ArrayList<>();
        Elements elements = createConnection(word)
                .select("body > div[id=wrapper] > section > div[class=left-content] > section[id=top-results]" +
                        " > div[id=translations-content] > a > span[class=display-term]");
        int index = elements.size() >= 5 ? 5 : elements.size();
        for (int i = 0; i < index; i++) {
            list.add(elements.get(i).text());
        }
        return list;
    }

    @Override
    public String checkWord(String word) throws IOException {

        Document doc = createConnection(URL_GOOGLE_CHECK + word);
        Elements el = doc.select("body > div[class=main] > div > div[class=GyAeWb] > div[class=s6JM6d] > div[id=taw]" +
                " > div[id=oFNiHe] > p > a > b > i");
        return el.text();

    }

    private Document createConnection(String word) throws IOException {
        return Jsoup.connect(URL_RU_EN_TRANSLATIONS + word).get();
    }
    
}
