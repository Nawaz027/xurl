package com.crio.shorturl;

import java.util.*;
import java.lang.*;


class XUrlImpl implements XUrl {

    Map<String, String> entries  = new HashMap<String, String>(); //longurl, shortURL
    Map<String, String> reverseEntries  = new HashMap<String, String>(); //shortURL, longURL
    // int count = 0;
    Map <String, Integer> count = new HashMap<String, Integer>();

public String registerNewUrl(String str) {
    if (entries.containsKey(str)) {
        return entries.get(str);
    } else {
    StringBuffer sb = new StringBuffer();
    String prefixUrl = "http://short.url/";
    sb = sb.append(prefixUrl);
    String AlphaNumericStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvxyz0123456789";
    int i;
    for ( i=0; i<9; i++) {
    int ch = (int)(AlphaNumericStr.length() * Math.random());
    sb.append(AlphaNumericStr.charAt(ch));
    }
    
      String shortRes = sb.toString();
      entries.put(str, shortRes);
      reverseEntries.put(shortRes, str);
      return shortRes;
    }
}


public String registerNewUrl(String longUrl, String shortUrl) {
    if (reverseEntries.containsKey(shortUrl)) {
        return null;
    } else {
        
          entries.put(longUrl, shortUrl);
          reverseEntries.put(shortUrl, longUrl);
          return shortUrl;
        }

}

public String getUrl (String shortUrl) {

    String key = reverseEntries.get(shortUrl);
    if (count.containsKey(key)) {
        count.put(key, count.get(key) + 1);
    } else {
        count.put(key, 1);
    }
    
    if (reverseEntries.containsKey(shortUrl)) return reverseEntries.get(shortUrl);
    return null;
    // return reverseEntries.get(shortUrl);
    // if (reverseEntries.get(shortUrl) == null) return null;
    // return reverseEntries.get(shortUrl);
    
}

public Integer getHitCount(String longUrl) {
    if (count.containsKey(longUrl)) return count.get(longUrl);
    else return 0;
    // return count;
}


public String delete(String longUrl) {
    String emptyURL = entries.get(longUrl);
    entries.put(longUrl, null);
    reverseEntries.put(emptyURL, null);
    return null;
}


}
