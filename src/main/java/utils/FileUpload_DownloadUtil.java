package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;

/*
 * 文件上传、下载的功能类：支持文件的断点下载
 */
public class FileUpload_DownloadUtil {

	/*
	 * 常量定义: SUSPEND_COMMAND:暂停命令 STOP_COMMAND:结束命令 CONTINUE_COMMAND:继续命令
	 */
	private static byte SUSPEND_COMMAND = 0x00;
	private static byte STOP_COMMAND = 0x01;
	private static byte CONTINUE_COMMAND = 0X02;

	/*
	 * 文件上传: localFile：将要上传的本地文件 brerakPoint：上传文件的断点标识 os：输出流 progress：输出的进度参数
	 * command：对循环的控制命令
	 */
	public static void fileUpload(final File localFile, OutputStream os,
			Integer breakPoint, Integer progress, final Byte command)
			throws FileNotFoundException {
		final RandomAccessFile rcf = new RandomAccessFile(localFile, "r");
		// 采用线程的方式进行文件的传输
		new Thread(new FileUpload_DownloadUtil().new FileUploadRunnable(rcf,
				os, command, breakPoint, progress)).start();
	}

	public static void fileDownload(final File localFile, InputStream is,
			Integer breakPoint, Integer progress, final Byte command)
			throws FileNotFoundException {
		final RandomAccessFile rcf = new RandomAccessFile(localFile, "r");
		// 采用线程的方式进行文件的传输
		new Thread(new FileUpload_DownloadUtil().new FileDownloadRunnable(rcf,
				is, command, breakPoint, progress)).start();
	}

	// 内部类
	class FileUploadRunnable implements Runnable {
		private RandomAccessFile rcf;
		private OutputStream os;
		private Byte command;
		private Integer breakPoint;
		private Integer progress;

		public FileUploadRunnable(RandomAccessFile rcf, OutputStream os,
				Byte command, Integer breakPoint, Integer progress) {
			this.rcf = rcf;
			this.os = os;
			this.command = command;
			this.breakPoint = breakPoint;
			this.progress = progress;
		}

		// buffer：缓存池
		private byte buffer[] = new byte[1024];
		private int length = -1;

		public void run() {
			// 文件断点上传操作
			do {
				synchronized (FileUpload_DownloadUtil.class) {
					if (command == SUSPEND_COMMAND) {
						;
					} else if (command == CONTINUE_COMMAND) {
						try {
							length = rcf
									.read(buffer, breakPoint, buffer.length);
							if (length != -1) {
								os.write(buffer, 0, length);
								progress = progress != null ? progress + length
										: length;
							}
						} catch (IOException e) {
							e.printStackTrace();
							break;
						}
					} else if (command == STOP_COMMAND) {
						try {
							rcf.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
					}
				}

			} while (length != -1);
		}
	}

	class FileDownloadRunnable implements Runnable {
		private RandomAccessFile rcf;
		private InputStream is;
		private Byte command;
		private Integer breakPoint;
		private Integer progress;

		public FileDownloadRunnable(RandomAccessFile rcf, InputStream is,
				Byte command, Integer breakPoint, Integer progress) {
			this.rcf = rcf;
			this.is = is;
			this.command = command;
			this.breakPoint = breakPoint;
			this.progress = progress;
		}

		// buffer：缓存池
		private byte buffer[] = new byte[1024];
		private int length = -1;

		public void run() {
			// 文件断点上传操作
			do {
				synchronized (FileUpload_DownloadUtil.class) {
					if (command == SUSPEND_COMMAND) {
						;
					} else if (command == CONTINUE_COMMAND) {
						try {
							length = is.read(buffer);
							if (length != -1) {
								rcf.write(buffer, progress, length);
								progress = progress != null ? progress + length
										: length;
							}
						} catch (IOException e) {
							e.printStackTrace();
							break;
						}
					} else if (command == STOP_COMMAND) {
						try {
							rcf.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
					}
				}

			} while (length != -1);
		}
	}

}
