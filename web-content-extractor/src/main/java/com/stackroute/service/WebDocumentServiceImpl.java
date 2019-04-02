//web document service methods implemented

package com.stackroute.service;

import com.stackroute.domain.SearchDocument;
import com.stackroute.domain.WebDocument;
import com.stackroute.exception.EmptyFileException;
import com.stackroute.exception.FileNotFoundException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class WebDocumentServiceImpl implements WebDocumentService{
    private WebDocument webDocument= new WebDocument();

    @Override
    public void sendSearchdoc(SearchDocument searchDocument){
        webDocument.setId(searchDocument.getId());
        webDocument.setConceptName(searchDocument.getConceptName());
        webDocument.setDomain(searchDocument.getDomain());
        webDocument.setLink(searchDocument.getLink());

    }


    @Override
    public String extractTitle(SearchDocument searchDocument){
        String title="";
        String link=searchDocument.getLink();
        try {
            Document document = Jsoup.connect(link).userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
                    .referrer("http://www.google.com").ignoreHttpErrors(true)
                    .get();
            //getting the title of the webpage
            title=document.title();
            webDocument.setTitle(title);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return webDocument.getTitle();
    }

    @Override
    public String extractDescription(SearchDocument searchDocument){
        String description = "";
        String link=searchDocument.getLink();
        try {
            Document document = Jsoup.connect(link).userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
                    .referrer("http://www.google.com").ignoreHttpErrors(true)
                    .get();
            Elements metaTags = document.getElementsByTag("meta");
            for (Element mTag : metaTags) {
                //storing the meta tag properties
                String name = mTag.attr("name");
                String property = mTag.attr("property");
                if (!name.equals("")) {
                    if (name.equals("description")) {
                        //getting the description meta tag
                        description = document.select("meta[name=description]").first().attr("content");
                        webDocument.setDescription(description);
                    }
                }
                else if (!property.equals("")) {
                    if (property.equals("og:description")) {
                        description = document.select("meta[property=og:description]").first().attr("content");
                        webDocument.setDescription(description);
                    }
                }
                else{
                    webDocument.setDescription(description);
                }
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return webDocument.getDescription();
    }

    @Override
    public String extractKeywords(SearchDocument searchDocument){
        String keywords = "";
        String link=searchDocument.getLink();
        try {
            Document document = Jsoup.connect(link).userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
                    .referrer("http://www.google.com").ignoreHttpErrors(true)
                    .get();
            Elements metaTags = document.getElementsByTag("meta");
            for (Element mTag : metaTags) {
                //storing the meta tag properties
                String name = mTag.attr("name");
                String property = mTag.attr("property");
                if (!name.equals("")) {
                    if (name.equals("keywords")) {
                        //getting the keywords meta tag
                        keywords = document.select("meta[name=keywords]").first().attr("content");
                        webDocument.setKeywords(keywords);
                    }
                }
                else if (!property.equals("")) {
                    if (name.equals("og:keywords")) {
                        keywords = document.select("meta[property=og:keywords]").first().attr("content");
                        webDocument.setKeywords(keywords);
                    }
                }
                else{
                    webDocument.setKeywords(keywords);
                }
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return webDocument.getKeywords();
    }

    @Override
    public int extractImageCount(SearchDocument searchDocument){
        int imageCount=0;
        String link=searchDocument.getLink();
        try {
            Document document = Jsoup.connect(link).userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
                    .referrer("http://www.google.com").ignoreHttpErrors(true)
                    .get();
            //counting the number of images on the html page
            Elements images = document.select("img[src~=(?i)\\.(png|jpe?g|gif)]");
            for (Element image : images) {
                imageCount++;
            }
            webDocument.setImageCount(imageCount);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return webDocument.getImageCount();
    }

    @Override
    public void extractCodeSnippets(SearchDocument searchDocument){
        double codeSnippets=0;
        String link=searchDocument.getLink();
        try {
            Document document = Jsoup.connect(link).userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
                    .referrer("http://www.google.com").ignoreHttpErrors(true)
                    .get();
            //calculating all the pre code tags and counting the number of times they occur
            Elements code = document.getElementsByTag("pre").tagName("code");

            for (Element codeElem : code) {
                codeSnippets++;
            }
            webDocument.setCodeSnippets(codeSnippets);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public WebDocument getContentExtractorResults(){
        return webDocument;
    }
}
