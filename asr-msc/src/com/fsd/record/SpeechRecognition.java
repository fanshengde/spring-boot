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
import com.iflytek.cloud.speech.UserWords;
/**
 * date：20170828
 * 实例来源
 * http://doc.xfyun.cn/msc_java/299249
 * 
 * #api
 * http://mscdoc.xfyun.cn/java/api/
 * */
public class SpeechRecognition {
	private static String APPID="";
	private static final String USER_WORD = "{\"userword\":[{\"name\":\"计算机词汇\",\"words\":[\"随机存储器\",\"只读存储器\",\"扩充数据输出\",\"局部总线\",\"压缩光盘\",\"十七寸显示器\"]},{\"name\":\"我的词汇\",\"words\":[\"槐花树老街\",\"王小贰\",\"发炎\",\"公事\"]}]}";;
	private static String DOMIN = "iat";
	private static String LANGUAGE = "zh_cn";
	private static String ACCENT = "mandarin";
	private static String AUDIO_SOURCE = "-1";
	private static int VOICE_PIECE_LENGTH = 4800;

	//file
	private static String VOICE_FILE_PATH="./";
	private static String VOICE_FILE_NAME="test.pcm";
	
	private static StringBuffer mResult = new StringBuffer();
	
	private boolean mIsLoop = true;
	
	
	public static void main(String[] args) {
		new SpeechRecognition().recognition();
	}
	private void recognition() {
		SpeechRecognizer mIat = SpeechRecognizer.createRecognizer();
		
		mIat.setParameter(SpeechConstant.DOMAIN, DOMIN);
		mIat.setParameter(SpeechConstant.LANGUAGE, LANGUAGE);
		mIat.setParameter(SpeechConstant.ACCENT, ACCENT);
		mIat.setParameter(SpeechConstant.AUDIO_SOURCE, AUDIO_SOURCE);
		
	
		mIat.startListening(mRecoListener);
		
		//fan 音频来源
//		byte[] voiceBuffer = getVoiceBuffer();
		
		//voiceBuffer为音频数据流，splitBuffer为自定义分割接口，将其以4.8k字节分割成数组
//		ArrayList<byte[]> buffers = splitBuffer(voiceBuffer, voiceBuffer.length, VOICE_PIECE_LENGTH);
		ArrayList<byte[]> buffers = getVoiceBuffer();
		for(int i = 0; i < buffers.size(); i++) {
			//每次写入msc数据4.8K,相当150ms录音数据
			mIat.writeAudio(buffers.get(i), 0, buffers.get(i).length);
		}
		
		mIat.stopListening();
	}
	
	private ArrayList<byte[]> getVoiceBuffer() {
		//fan
		File file = new File(VOICE_FILE_PATH +"/"+ VOICE_FILE_NAME);
		InputStream fis;
		byte[] bbuf = new byte[VOICE_PIECE_LENGTH];
		ArrayList<byte[]> result = new ArrayList<byte[]>();
		
		@SuppressWarnings("all")
		int hasRead = 0;  
		try {
			 fis = new FileInputStream(file);
			 while((hasRead = fis.read(bbuf))>0){  
//	            System.out.println(new String(bbuf,0,hasRead));
				 result.add(bbuf);
		     }  
			 fis.close(); 
		}catch( FileNotFoundException e	) {
			
			DebugLog.Log("voice file is not fount");
			
		}catch(IOException e) {
			
			DebugLog.Log("io process error!");
			
		}
		return result;
	}
	private ArrayList<byte[]> splitBuffer(byte[] voiceBuffer, int bufLength, int pieceLength){
		//fan
		return null;
	}
	
	/**
	 * 上传用户词表
	 * 
	 * 上传用户词表可以提高词表内词汇的识别率，也可以提高语义的效果，每个用户终端设备对应一个词表，用户词表的格式及构造方法详见《MSC Reference Manual》UserWords类。
	 * */
	private void uploadUserWords(String userWords) {
		SpeechRecognizer recognizer = SpeechRecognizer.getRecognizer();
		UserWords userWord = new UserWords(userWords);
		recognizer.setParameter(SpeechConstant.DATA_TYPE, "userword");
		recognizer.updateLexicon("userword", userWords.toString(), lexiconListener);
	}
	private RecognizerListener mRecoListener = new RecognizerListener() {
		
		@Override
		public void onVolumeChanged(int volume) {
			//音量值0~30
		}
		
		@Override
		public void onResult(RecognizerResult results, boolean isLast) {
			DebugLog.Log("Result:"+results.getResultString ());
		}
		
		@Override
		public void onEvent(int arg0, int arg1, int arg2, String arg3) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onError(SpeechError error) {
			//获取错误码描述
			error.getErrorDescription(true); 
			System.out.println("dddddddddddddddddd " + error.getErrorCode() + " : " + error.getErrorDesc());
		}
		
		@Override
		public void onEndOfSpeech() {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onBeginOfSpeech() {
			//开始录音
			
		}
	};
	
	LexiconListener lexiconListener = new LexiconListener() {
		
		@Override
		public void onLexiconUpdated(String lexiconId, SpeechError error) {
			if (error == null)
				DebugLog.Log("*************上传成功*************");
			else
				DebugLog.Log("*************" + error.getErrorCode()+ "*************");
		}
	};
}
