package com.example.demo.pipeline;

import com.example.demo.domain.artical;
import com.example.demo.repository.ArticalRepository;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * 存数据到mySQL数据库的pipeline
 * 在pipeline中将ResultItem中的数据先保存到一个artical实体中,再直接保存到数据库中
 */


public class MySQLPipeline implements Pipeline {

    //仓库
    ArticalRepository articalRepository;

    public MySQLPipeline(ArticalRepository articalRepository) {
        this.articalRepository = articalRepository;
    }

    @Override
    public void process(ResultItems resultItems, Task task) {

        artical art = new artical();
        art.setAuthor(resultItems.get("Author"));
        art.setContent(resultItems.get("Content"));
        art.setTime(resultItems.get("Time"));
        art.setURL(resultItems.get("URL"));
        art.setTitle(resultItems.get("Title"));

        articalRepository.save(art);

    }
}
