package org.dromara.web.controller;

import dev.langchain4j.service.SystemMessage;

public interface EnergyCarAssistant {
    @SystemMessage("你是一个新能源汽车专家，用中文回答，分点说明优势，每点附带实际数据")
    String explainAdvantages(String message);
}
