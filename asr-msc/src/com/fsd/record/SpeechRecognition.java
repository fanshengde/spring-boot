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
 * date��20170828
 * ʵ����Դ
 * http://doc.xfyun.cn/msc_java/299249
 * 
 * #api
 * http://mscdoc.xfyun.cn/java/api/
 * */
public class SpeechRecognition {
	private static String APPID="";
	private static final String USER_WORD = "{\"userword\":[{\"name\":\"������ʻ�\",\"words\":[\"����洢��\",\"ֻ���洢��\",\"�����������\",\"�ֲ�����\",\"ѹ������\",\"ʮ�ߴ���ʾ��\"]},{\"name\":\"�ҵĴʻ�\",\"words\":[\"�������Ͻ�\",\"��С��\",\"����\",\"����\"]}]}";;
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
		
		//fan ��Ƶ��Դ
//		byte[] voiceBuffer = getVoiceBuffer();
		
		//voiceBufferΪ��Ƶ��������splitBufferΪ�Զ���ָ�ӿڣ�������4.8k�ֽڷָ������
//		ArrayList<byte[]> buffers = splitBuffer(voiceBuffer, voiceBuffer.length, VOICE_PIECE_LENGTH);
		ArrayList<byte[]> buffers = getVoiceBuffer();
		for(int i = 0; i < buffers.size(); i++) {
			//ÿ��д��msc����4.8K,�൱150ms¼������
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
	 * �ϴ��û��ʱ�
	 * 
	 * �ϴ��û��ʱ������ߴʱ��ڴʻ��ʶ���ʣ�Ҳ������������Ч����ÿ���û��ն��豸��Ӧһ���ʱ��û��ʱ�ĸ�ʽ�����췽�������MSC Reference Manual��UserWords�ࡣ
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
			//����ֵ0~30
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
			//��ȡ����������
			error.getErrorDescription(true); 
			System.out.println("dddddddddddddddddd " + error.getErrorCode() + " : " + error.getErrorDesc());
		}
		
		@Override
		public void onEndOfSpeech() {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onBeginOfSpeech() {
			//��ʼ¼��
			
		}
	};
	
	LexiconListener lexiconListener = new LexiconListener() {
		
		@Override
		public void onLexiconUpdated(String lexiconId, SpeechError error) {
			if (error == null)
				DebugLog.Log("*************�ϴ��ɹ�*************");
			else
				DebugLog.Log("*************" + error.getErrorCode()+ "*************");
		}
	};
}
