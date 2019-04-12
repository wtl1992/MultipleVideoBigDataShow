package utils;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class HttpRequestUtil {
	//从网络上请求网页内容
	public static String requestHttp(String url,String encoding,String method){
		byte [] buffer = new byte[1024];
		int length = -1;
		StringBuffer content = new StringBuffer();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		BufferedInputStream is = null;
		try {
			HttpURLConnection urlConnection = (HttpURLConnection) new URL(url).openConnection();

			if ("GET".equalsIgnoreCase(method)){
				urlConnection.setRequestMethod("GET");
			}
			else{
				urlConnection.setRequestMethod("POST");
				urlConnection.setRequestProperty("Content-Type", "application/json");
			}
			urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.111 Safari/537.3");
			is = new BufferedInputStream(urlConnection.getInputStream());
			do{
				length = is.read(buffer);
				if (length !=-1){
					//content.append(new String(buffer,0,length,encoding));
					outputStream.write(buffer,0,length);
				}
			}while(length!=-1);
			content.append(new String(outputStream.toByteArray(),0,outputStream.toByteArray().length,encoding));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if (is != null){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return content.toString();
	}
}
