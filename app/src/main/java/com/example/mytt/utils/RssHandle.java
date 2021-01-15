package com.example.mytt.utils;

import android.util.Log;

import com.example.mytt.bean.NewBean;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by Administrator on 2019/5/14 0014.
 */

/*public class RssHandle extends DefaultHandler {
    NewBean rssNewBean;
    RssItem rssItem;
    String lastElementName="";
    final int Rss_TITLE=1;
    final int Rss_LINK=2;
    final int Rss_DESCRIPTION=3;
    final int Rss_CATEGORY=4;
    final int Rss_PUBDATE=5;
    int currentPlag=0;

    public RssHandle(){

    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();;
        rssFeed=new RssFeed();
        rssItem=new RssItem();
    }
    @Override
    public void characters(char[] ch,int start,int length) throws SAXException {
        super.characters(ch, start, length);
        String text=new String(ch,start,length);
        Log.i("i","yao"+text);
        switch(currentPlag){
            case Rss_TITLE:
                rssItem.setTitle(text);
                currentPlag=0;
                break;
            case Rss_PUBDATE:
                rssItem.setPubdate(text);
                currentPlag=0;
                break;
            case Rss_CATEGORY:
                rssItem.setCategory(text);
                currentPlag=0;
                break;
            case Rss_LINK:
                rssItem.setLink(text);
                currentPlag=0;
                break;
            case Rss_DESCRIPTION:
                rssItem.setDescription(text);
                currentPlag=0;
                break;
                default:
                    break;
        }
    }
    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        if("channel".equals(localName)){
            currentPlag=0;
            return;
        }
        if("item".equals(localName)){
            rssItem=new RssItem();
            return;
        }
        if("title".equals(localName)){
            currentPlag=Rss_TITLE;
            return;
        }
        if("description".equals(localName)){
            currentPlag=Rss_DESCRIPTION;
            return;
        }
        if("link".equals(localName)){
            currentPlag=Rss_LINK;
            return;
        }
        if("pubDate".equals(localName)){
            currentPlag=Rss_PUBDATE;
            return;
        }
        if("category".equals(localName)){
            currentPlag=Rss_CATEGORY;
            return;
        }
    }
    @Override
    public void endElement(String uri, String loaclName, String qName) throws SAXException {
        super.endElement(uri, loaclName, qName);
        if("item".equals(loaclName)){
            rssFeed.addItem(rssItem);
            return;
        }

    }
    @Override
    public void endDocument()throws SAXException {
        super.endDocument();
    }
    public RssFeed getRssFeed(){
        return rssFeed;
    }


}*/
