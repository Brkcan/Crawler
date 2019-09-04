package crawl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawler {

	public static void main(String[] args) throws IOException {
		
		Document doc = Jsoup.connect("https://ilan.gov.tr/kategori-ihale?type=21628").get();
		String title = doc.title();
		System.out.println("Title  : " + title);
		
		Elements li = doc.select("ul li.item.column.col-12 ul.admin-toolbar li div.pop-up a");
		for (Element link : li) {
			String links = link.attr("href");
			Document doc1 = Jsoup.connect("https://ilan.gov.tr/" + links).get();
			Elements li2 = doc1.select("div.column.col-16 div.crazy-box div.table-div div.tr div.td");
			li2.remove(3);
			if(li2.remove().size() > 6 ) {
				li2.remove(5);
			}
			
			Iterator<Element> li3 = li2.iterator();
			
			while(li3.hasNext()) {
				Element element = li3.next();
				Elements el = element.getElementsByClass("td");
				String e = el.get(0).ownText();
				System.out.println(e);
				try {
					// DÝKKAT 
					File dosya = new File("C:/Users/tcaki/desktop/crede.txt"); // TEXT DOSYASINI kendi masaüstüme gönderdim.
					FileWriter yazici = new FileWriter(dosya,true);
					BufferedWriter yaz = new BufferedWriter(yazici);
					yaz.write(e);
					System.out.println("\n");
					yaz.close();
				} catch (Exception hata ) {
					hata.printStackTrace();
				}
			
			}
		}
	}

}
