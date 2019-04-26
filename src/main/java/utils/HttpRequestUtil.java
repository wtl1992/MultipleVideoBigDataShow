package utils;

import java.io.*;
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


	/**
	 * 返回字节数组
	 * @param url
	 * @param encoding
	 * @param method
	 * @return
	 */
	public static byte [] requestHttpThroughBytes(String url,String encoding,String method){
		byte [] buffer = new byte[1024];
		int length = -1;
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		BufferedInputStream is = null;
		try {
			HttpURLConnection urlConnection = (HttpURLConnection) new URL(url).openConnection();

			if ("GET".equalsIgnoreCase(method)) {
				urlConnection.setRequestMethod("GET");
//				urlConnection.setRequestProperty("pragma", "no-cache");
//				urlConnection.setRequestProperty("cookie", "BAIDUID=E46BDD0E162531F1DD2FEF03B41829E2:FG=1; PSTM=1556160786; BIDUPSID=5CA9CAB967453740D61CCD724A5EC293; BDORZ=B490B5EBF6F3CD402E515D22BCDA1598");
//				urlConnection.setRequestProperty("accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3");
//				urlConnection.setRequestProperty("cache-control","no-cache");
			}
			else{
				urlConnection.setRequestMethod("POST");
				urlConnection.setRequestProperty("Content-Type", "application/json");
			}
			urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.111 Safari/537.3");
			is = new BufferedInputStream(urlConnection.getInputStream());

			while((length = is.read(buffer))!=-1){
				outputStream.write(buffer,0,length);
			}

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

		byte bytes [] = outputStream.toByteArray();


		return bytes;
	}
}
