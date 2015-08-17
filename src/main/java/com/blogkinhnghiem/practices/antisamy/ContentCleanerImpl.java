package com.blogkinhnghiem.practices.antisamy;

import org.owasp.validator.html.AntiSamy;
import org.owasp.validator.html.CleanResults;
import org.owasp.validator.html.Policy;
import org.owasp.validator.html.PolicyException;
import org.owasp.validator.html.ScanException;
import org.springframework.stereotype.Service;

@Service ("contentCleaner")
public class ContentCleanerImpl implements ContentCleaner
{
    private static Policy policy = null;

    private static AntiSamy as = new AntiSamy();

    private Policy getPolicy() throws PolicyException
    {
        if (policy == null)
        {
            policy = Policy.getInstance(this.getClass().getClassLoader().getResourceAsStream("antisamy.xml"));
        }
        return policy;
    }

    @Override
    public String clean(String renderedHtml)
    {
        try
        {
            final CleanResults cleanResults = as.scan(renderedHtml, getPolicy());
            return cleanResults.getCleanHTML();
        }
        catch (ScanException e)
        {
            throw new RuntimeException("Cannot clean this html", e);
        }
        catch (PolicyException e)
        {
            throw new RuntimeException("Cannot find the antisamy policy", e);
        }
    }
}
