package com.memmorise.app.interective.learning.util;

import java.util.ArrayList;
import java.util.List;

import com.memmorise.app.interective.learning.LearnMap;
import com.memmorise.app.interective.learning.LearnMap.Node;

public class LearnUtil {


    public static List<Node> checkLevelOfNow(List<Node> pack, int level, LearnMap learnMap) {
        List<Node> result = new ArrayList<>();
        for (int i = 0; i < pack.size(); i++) {
            if (pack.get(i).levelOfNow < level) {
                result.add(pack.get(i));
            }
        }
        return result;
    }

}
