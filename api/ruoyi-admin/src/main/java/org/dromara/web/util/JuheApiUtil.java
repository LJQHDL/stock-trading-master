package org.dromara.web.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 聚合数据API工具类
 * 参考聚合数据官方示例代码实现
 *
 * @author ljq
 * @date 2025-01-30
 */
public class JuheApiUtil {

    /**
     * 调用聚合数据美国股票API
     * 参考官方示例代码：http://web.juhe.cn/finance/stock/usaall
     *
     * @param apiKey API密钥
     * @param apiUrl API地址
     * @param page 页码
     * @return API响应结果
     * @throws Exception 调用异常
     */
    public static String callUsaStockApi(String apiKey, String apiUrl, String page) throws Exception {
        // 构建请求参数
        HashMap<String, String> map = new HashMap<>();
        map.put("key", apiKey);
        map.put("page", page);

        // 构建完整的请求URL
        URL url = new URL(String.format("%s?%s", apiUrl, formatParams(map)));

        // 发送HTTP GET请求
        BufferedReader in = new BufferedReader(new InputStreamReader((url.openConnection()).getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }

    /**
     * 格式化请求参数
     * 将Map参数转换为URL查询字符串格式
     *
     * @param map 参数Map
     * @return 格式化后的参数字符串
     */
    public static String formatParams(Map<String, String> map) {
        return map.entrySet().stream()
                .map(entry -> {
                    try {
                        return entry.getKey() + "=" + URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8.toString());
                    } catch (Exception e) {
                        System.err.println("参数编码失败: " + entry.getKey() + "=" + entry.getValue());
                        e.printStackTrace();
                        return entry.getKey() + "=" + entry.getValue();
                    }
                })
                .collect(Collectors.joining("&"));
    }

    /**
     * 测试方法
     * 演示如何使用聚合数据API
     */
    public static void main(String[] args) throws Exception {
        String apiKey = "5a65231850a01ed2b6699ed6b771a080";
        String apiUrl = "http://web.juhe.cn/finance/stock/usaall";

        HashMap<String, String> map = new HashMap<>();
        map.put("key", apiKey);
        map.put("page", "1");

        URL url = new URL(String.format("%s?%s", apiUrl, formatParams(map)));
        BufferedReader in = new BufferedReader(new InputStreamReader((url.openConnection()).getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        System.out.println(response);
    }
}
