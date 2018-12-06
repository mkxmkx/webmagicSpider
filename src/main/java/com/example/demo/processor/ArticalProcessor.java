package com.example.demo.processor;

import com.example.demo.pipeline.ConsolePipeline;
import com.example.demo.pipeline.MySQLPipeline;
import com.example.demo.repository.ArticalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;

/**
 * 在该类中定义爬虫
 * URL_Post是最终要解析的页面的URL格式
 * URL_LIST是辅助获得URL_Post的页面
 * 要定义该类是一个Component，定义Repository自动装配（Autowired)
 * 定义了一个creatSpider函数，启动爬虫，可以在其它类中调用creatSpider函数启动爬虫
 */

@Component
public class ArticalProcessor implements PageProcessor {

    @Autowired
    ArticalRepository articalRepository;

    public static final String URL_Post = "http://blog\\.sina\\.com\\.cn/s/blog_\\w+\\.html";
    public static final String URL_LIST = "http://blog\\.sina\\.com\\.cn/s/articlelist_1901692317_0_\\d+\\.html";

    private Site site = Site.me().setRetryTimes(3).setSleepTime(100).setCharset("UTF-8");

    @Override
    public Site getSite()
    {
        return site;
    }


    @Override
    public void process(Page page)
    {

        if(page.getUrl().regex(URL_LIST).match())
        {
            System.out.println("in if");
            page.addTargetRequests(page.getHtml().links().regex(URL_Post).all());
        }
        else
        {
            System.out.println("in else");
            page.putField("Title",page.getHtml()
                    .xpath("//div[@class='artical']/div[@class='articalTitle']/h2/text()").toString());
            page.putField("Time",page.getHtml()
                    .xpath("//*[@id='articlebody']/div[1]/span[3]/text()").toString());
            page.putField("Author",page.getHtml()
                    .xpath("//*[@id='sina_keyword_ad_area2']/p[1]/span/allText()").toString());
            page.putField("URL",page.getUrl().toString());


            page.putField("Content", page.getHtml().xpath("//*[@id='sina_keyword_ad_area2']/p[1]/allText()")
                    .toString());

        }

    }

    public void creatSpider()
    {
        Spider.create(new ArticalProcessor())
                .addUrl("http://blog.sina.com.cn/s/articlelist_1901692317_0_1.html")
                .addPipeline(new ConsolePipeline())
                .addPipeline(new MySQLPipeline(articalRepository)).run();
    }

    public static void main(String[] args)
    {

    }
}

