import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

	public class wordCount {

		public static void main(String[] args) {
			System.out.println(myWords());
		}
		
		public static String myWords() {
			String theWords = "";
			
			try {
				String s = "";
				Document document = Jsoup.connect(
						"https://www.gutenberg.org/files/1065/1065-h/1065-h.htm" )
						.timeout(0).get();

				Elements allElements = document.select("p");
				for (Element element : allElements) {
					s += element.ownText();
				}

				allElements = document.select("span[style*='margin-left: 20%']");
				for (Element element : allElements) {
					s += element.ownText();
				}

				HashMap<String, Integer> hm = new <String, Integer>HashMap();

				s = s.toLowerCase();
				String s2 = s.replaceAll("[^:a-z:]", " ");
				String[] words = s2.split("\s");
				for (int i = 0; i < words.length; i++) {
					if (words[i].length() != 0 && words[i].charAt(0) != ' ') {
						if (hm.containsKey(words[i])) {
							hm.replace(words[i], hm.get(words[i]) + 1);
						} else {
							hm.put(words[i], 1);
						}
					}
				}
				hm = sortByFrequency(hm);
				theWords = hm.entrySet().toString();
			} 
			catch (FileNotFoundException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
			}

			catch (IOException e) {

				e.printStackTrace();
			}
			return theWords;
		}

		private static HashMap sortByFrequency(HashMap map) {
			List list = new LinkedList(map.entrySet());

			Collections.sort(list, new Comparator() {
				public int compare(Object o1, Object o2) {
					return ((Comparable) ((Map.Entry) (o2)).getValue()).compareTo(((Map.Entry) (o1)).getValue());
				}
			});

			HashMap sortedHashMap = new LinkedHashMap();
			for (Iterator it = list.iterator(); it.hasNext();) {
				Map.Entry entry = (Map.Entry) it.next();
				sortedHashMap.put(entry.getKey(), entry.getValue());
			}

			return sortedHashMap;
		}
	}