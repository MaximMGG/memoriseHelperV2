package com.memmorise.app.learning;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.memmorise.app.interective.learning.LearnMap;
import com.memmorise.app.interective.learning.util.LearnUtil;

public class LearnUtilTest {

    static LearnMap<Integer, String> help;


    @BeforeAll
    static void initHelp() {
        help = new LearnMap<>();
        List<String> words = List.of ("Hello", "Bye", "Cat", "Dog", "Perrot", "Animal", "Man", "Women", "Child", "Leazy");
        List<String> trans = List.of("Привет, хай", "Пока, бай", "Кот, кошка", "Пес, собака", "Папугай", "Животное, зверь"
                , "Мужчина", "Женщина", "Ребенок", "Лунивый, лентяй");

        for (int i = 0; i < words.size(); i++) {
            help.put(i, words.get(i), trans.get(i));
        }
    }


    @Test
    void getRounmFourTest() {

        String tranlations = "Кот, кошка";

        List<String> randomFour = LearnUtil.getFourRandmTranslations(help, tranlations);

        for(String s : randomFour) {
            assertTrue(help.conteins(s));
        }
    }
}
