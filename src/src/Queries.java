package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.net.*;

public class Queries {
	
	public static void initialStops(String init)
	{
		String url = "https://www.communitytransit.org/busservice/schedules/";
		String text = URLScraper.scrape(url);
		
		String re = "<hr id=\".*\" />\\s*<h3>(.*)</h3>\\s*((<div class=\"row Community\">\\s*<div class=\"col-xs-2\">\\s*<strong><a href=\".*\".*>(.*)</a></strong>\\s*</div>\\s*<div class=\"col-xs-9 col-xs-offset-1\">.*</div>\\s*</div>\\s*)*)";
		Pattern p = Pattern.compile(re);
		Matcher m = p.matcher(text);
		
		while(m.find())
		{
			String stop = m.group(1);
			if (stop.startsWith(init))
			{
				System.out.println("Destination: "+stop);
				String text2 = m.group(2);
				//System.out.print(text2);
				String re2 = "<div class=\"row Community\">\\s*<div class=\"col-xs-2\">\\s*<strong><a href=\".*\".*>(.*)</a></strong>\\s*</div>\\s*<div class=\"col-xs-9 col-xs-offset-1\">.*</div>\\s*</div>\\s*";
				Pattern p2 = Pattern.compile(re2);
				Matcher m2 = p2.matcher(text2);
				while(m2.find())
				{
					System.out.println("Bus Number: "+m2.group(1));
				}
			}
		}
	}
	
	public static void routeSchedule(String route)
	{
		route = route.replace('/', '-');	//	links for route names with '/' use a '-' instead
		String url = "https://www.communitytransit.org/busservice/schedules/route/"+route;
		String text = URLScraper.scrape(url);
		
		System.out.println("Route Link: "+url);
		
		String re = "<thead>\\s*<tr>\\s*<td colspan=\"\\d*\">\\s*<h2>(.*)<small>(.*)</small></h2>\\s*</td>\\s*</tr>\\s*<tr>\\s*(<th class=\"text-center\">Route</th>\\s*)?((<th class=\"text-center\">\\s*<span class=\"fa-stack\">\\s*<i class=\"fa fa-circle-thin fa-stack-2x\"></i>\\s*<strong class=\"fa fa-stack-1x\">(.*)</strong>\\s*</span>\\s*<p>(.*)</p>\\s*</th>\\s*)*)</tr>\\s*</thead>";
		Pattern p = Pattern.compile(re);
		Matcher m = p.matcher(text);
		
		while(m.find())
		{
			System.out.println("Days: "+m.group(1));
			System.out.println("Destination: "+m.group(2));
			String text2=m.group(4);
			String re2="<th class=\"text-center\">\\s*<span class=\"fa-stack\">\\s*<i class=\"fa fa-circle-thin fa-stack-2x\"></i>\\s*<strong class=\"fa fa-stack-1x\">(.*)</strong>\\s*</span>\\s*<p>(.*)</p>\\s*</th>\\s*";
			Pattern p2 = Pattern.compile(re2);
			Matcher m2 = p2.matcher(text2);
			while(m2.find())
			{
				System.out.println("Stop Number: "+m2.group(1)+" is "+m2.group(2));
			}
		}
	}
	
}
