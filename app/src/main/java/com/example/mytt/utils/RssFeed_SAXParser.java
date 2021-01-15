package com.example.mytt.utils;

import com.example.mytt.bean.NewBean;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import java.io.IOException;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/*
public class RssFeed_SAXParser {
    public NewBean getNewBean(String urlstr) throws ParserConfigurationException, SAXException, IOException {
        URL url=new URL(urlstr);
        SAXParserFactory saxParserFactory= SAXParserFactory.newInstance();
        SAXParser saxParser=saxParserFactory.newSAXParser();
        XMLReader xmlReader=saxParser.getXMLReader();
        RssHandle rssHandle=new RssHandle();
        xmlReader.setContentHandler(rssHandle);
        InputSource inputSource=new InputSource(url.openStream());
        xmlReader.parse(inputSource);
        return rssHandle.getRssFeed();
    }

}
        */
