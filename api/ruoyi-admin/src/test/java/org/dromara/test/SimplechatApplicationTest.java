package org.dromara.test;

import dev.langchain4j.model.chat.ChatLanguageModel;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SimplechatApplicationTest {

    @Autowired
    private ChatLanguageModel chatLanguageModel;

    @Test
    public void testChat(){
        String generate = chatLanguageModel.chat("请你介绍一下新能源汽车");

        System.out.println(generate);
    }

}
