package com.fsd.record;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import com.fsd.util.DebugLog;
import com.iflytek.cloud.speech.LexiconListener;
import com.iflytek.cloud.speech.RecognizerListener;
import com.iflytek.cloud.speech.RecognizerResult;
import com.iflytek.cloud.speech.SpeechConstant;
import com.iflytek.cloud.speech.SpeechError;
import com.iflytek.cloud.speech.SpeechRecognizer;
import com.iflytek.cloud.speech.SpeechUtility;
import com.iflytek.cloud.speech.UserWords;

/**
 * date：20170828 实例来源 http://doc.xfyun.cn/msc_java/299249
 * 
 * #api http://mscdoc.xfyun.cn/java/api/
 */
public class MscTest {
	private static String APPID = "59a3b91e";
	private static final String USER_WORD = "{\"userword\":[{\"name\":\"计算机词汇\",\"words\":[\"随机存储器\",\"只读存储器\",\"扩充数据输出\",\"局部总线\",\"压缩光盘\",\"十七寸显示器\"]},{\"name\":\"我的词汇\",\"words\":[\"槐花树老街\",\"王小贰\",\"发炎\",\"公事\"]}]}";;
//	private static String DOMIN = "iat";
//	private static String LANGUAGE = "zh_cn";
//	private static String ACCENT = "mandarin";
	private static String AUDIO_SOURCE = "-1";
	private static String RESULT_TYPE = "plain";
	private static int VOICE_PIECE_LENGTH = 64 * 1024;
	// file
	private static String VOICE_FILE_PATH = "./";
	private static String VOICE_FILE_NAME = "test.pcm";
//	private static String VOICE_FILE_NAME = "yueyu.wav";

	private static StringBuffer mResult = new StringBuffer();

	public static void main(String[] args) {
		new MscTest().recognize();
	}
	private boolean mIsEndOfSpeech = false;

	private void recognize() {
		if (SpeechRecognizer.getRecognizer() == null) {
			SpeechRecognizer.createRecognizer();
		}

		mIsEndOfSpeech = false;
		SpeechUtility.createUtility("appid=" + APPID);
		RecognizePcmfileByte();
	}

	private void RecognizePcmfileByte() {
		SpeechRecognizer recognizer = SpeechRecognizer.getRecognizer();
		recognizer.setParameter(SpeechConstant.AUDIO_SOURCE, AUDIO_SOURCE);
		recognizer.setParameter(SpeechConstant.RESULT_TYPE, RESULT_TYPE);
		recognizer.startListening(recListener);

		FileInputStream fis = null;
		final byte[] buffer = new byte[VOICE_PIECE_LENGTH];
		try {
			fis = new FileInputStream(new File(VOICE_FILE_PATH + VOICE_FILE_NAME));
			if (0 == fis.available()) {
				mResult.append("no audio avible!");
				recognizer.cancel();
			} else {
				int lenRead = buffer.length;
				while(buffer.length == lenRead && !mIsEndOfSpeech) {
					lenRead = fis.read(buffer);
					recognizer.writeAudio(buffer, 0, lenRead);
				}
				recognizer.stopListening();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != fis) {
					fis.close();
					fis = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	RecognizerListener recListener = new RecognizerListener() {

		@Override
		public void onVolumeChanged(int volume) {
			DebugLog.Log("onVolumeChanged enter");
			if (volume > 0)
				DebugLog.Log("*************音量值:" + volume + "*************");
		}

		@Override
		public void onResult(RecognizerResult result, boolean islast) {
			DebugLog.Log("onResult enter");
			mResult.append(result.getResultString());

			if (islast) {
				DebugLog.Log("识别结果为111:" + mResult.toString());
				mIsEndOfSpeech = true;
				mResult.delete(0, mResult.length());
				waitupLoop();
			}
		}

		@Override
		public void onEvent(int arg0, int arg1, int arg2, String arg3) {
			DebugLog.Log("onEvent enter");
		}

		@Override
		public void onError(SpeechError error) {
			mIsEndOfSpeech = true;
			DebugLog.Log("error： *************" + error.getErrorCode() + "*************" + error.getErrorDesc() + "*************" +error.getStackTrace()
			);
			waitupLoop();
		}

		@Override
		public void onEndOfSpeech() {
			DebugLog.Log("onEndOfSpeech enter");
			mIsEndOfSpeech = true;
		}

		@Override
		public void onBeginOfSpeech() {
			DebugLog.Log("onBeginOfSpeech enter");
			DebugLog.Log("*************开始录音*************");
		}
	};

	private void waitupLoop() {
		synchronized (this) {
			MscTest.this.notify();
		}
	}
}
