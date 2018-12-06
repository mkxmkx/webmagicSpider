package com.example.demo.pipeline;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import java.util.Map;

/**
 * 输出到控制台pipeline
 * 将ResultItems中的每个键值都输出
 */

public class ConsolePipeline implements Pipeline {

    @Override
    public void process(ResultItems resultItems, Task task) {
        System.out.println("get page: " + resultItems.getRequest().getUrl());
        //遍历所有结果，输出到控制台，上面例子中的"title"、"time"、"author"都是一个key，其结果则是对应的value
        for (Map.Entry<String, Object> entry : resultItems.getAll().entrySet()) {
            System.out.println(entry.getKey() + ":\t" + entry.getValue());
        }
    }
}
