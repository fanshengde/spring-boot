package com.fsd.record;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.fsd.util.DebugLog;
import com.fsd.util.DrawableUtils;
import com.iflytek.cloud.speech.LexiconListener;
import com.iflytek.cloud.speech.RecognizerListener;
import com.iflytek.cloud.speech.RecognizerResult;
import com.iflytek.cloud.speech.ResourceUtil;
import com.iflytek.cloud.speech.SpeechConstant;
import com.iflytek.cloud.speech.SpeechError;
import com.iflytek.cloud.speech.SpeechRecognizer;
import com.iflytek.cloud.speech.UserWords;

public class IatSpeechView {
	private static final long serialVersionUID = 1L;


	// ������д����
	private SpeechRecognizer mIat;
	

	private Map<String, String> mParamMap = new HashMap<String, String>();
	
	private static final String VAL_TRUE = "1";
	
	private static class DefaultValue{
		public static final String ENG_TYPE = SpeechConstant.TYPE_CLOUD;
		public static final String SPEECH_TIMEOUT = "60000";
		public static final String NET_TIMEOUT = "20000";
		public static final String LANGUAGE = "zh_cn";
		
		public static final String ACCENT = "mandarin";
		public static final String DOMAIN = "iat";
		public static final String VAD_BOS = "5000";
		public static final String VAD_EOS = "1800";
		
		public static final String RATE = "16000";
		public static final String NBEST = "1";
		public static final String WBEST = "1";
		public static final String PTT = "1";
		
		public static final String RESULT_TYPE = "json";
		public static final String SAVE = "0";
	}

	private static final String DEF_FONT_NAME = "����";
	private static final int DEF_FONT_STYLE = Font.BOLD;
	private static final int DEF_FONT_SIZE = 30;
	private static final int TEXT_COUNT = 100;
	
	/**
	 * ��ʼ����ť����. ���ð�ť����ͼƬ����С��������¼� ��ʼ���ı��������������͡���С
	 */
	public IatSpeechView() {
		// ��ʼ����д����
		mIat=SpeechRecognizer.createRecognizer();
		
		initParamMap();
		initMenu();
	}

	/***
	 * ������ʵ��. ��ť���¶���ʵ��
	 */
	public void actionPerformed() {
		if (!mIat.isListening())
			mIat.startListening(recognizerListener);
		else
			mIat.stopListening();
	}

	/**
	 * ��д������
	 */
	private RecognizerListener recognizerListener = new RecognizerListener() {

		@Override
		public void onBeginOfSpeech() {
			DebugLog.Log( "onBeginOfSpeech enter" );
		}

		@Override
		public void onEndOfSpeech() {
			DebugLog.Log( "onEndOfSpeech enter" );
		}

		/**
		 * ��ȡ��д���. ��ȡRecognizerResult���͵�ʶ���������Խ�������ۼӣ���ʾ��Area��
		 */


		@Override
		public void onError(SpeechError error) {
			DebugLog.Log( "onError enter" );
			if (null != error){
				DebugLog.Log("onError Code��" + error.getErrorCode());
			}
		}

		@Override
		public void onEvent(int eventType, int arg1, int agr2, String msg) {
			DebugLog.Log( "onEvent enter" );
		}

		@Override
		public void onResult(RecognizerResult arg0, boolean arg1) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onVolumeChanged(int arg0) {
			// TODO Auto-generated method stub
			
		}
	};


	private void initParamMap(){
		this.mParamMap.put( SpeechConstant.ENGINE_TYPE, DefaultValue.ENG_TYPE );
		this.mParamMap.put( SpeechConstant.SAMPLE_RATE, DefaultValue.RATE );
		this.mParamMap.put( SpeechConstant.NET_TIMEOUT, DefaultValue.NET_TIMEOUT );
		this.mParamMap.put( SpeechConstant.KEY_SPEECH_TIMEOUT, DefaultValue.SPEECH_TIMEOUT );
		
		this.mParamMap.put( SpeechConstant.LANGUAGE, DefaultValue.LANGUAGE );
		this.mParamMap.put( SpeechConstant.ACCENT, DefaultValue.ACCENT );
		this.mParamMap.put( SpeechConstant.DOMAIN, DefaultValue.DOMAIN );
		this.mParamMap.put( SpeechConstant.VAD_BOS, DefaultValue.VAD_BOS );
		
		this.mParamMap.put( SpeechConstant.VAD_EOS, DefaultValue.VAD_EOS );
		this.mParamMap.put( SpeechConstant.ASR_NBEST, DefaultValue.NBEST );
		this.mParamMap.put( SpeechConstant.ASR_WBEST, DefaultValue.WBEST );
		this.mParamMap.put( SpeechConstant.ASR_PTT, DefaultValue.PTT );
		
		this.mParamMap.put( SpeechConstant.RESULT_TYPE, DefaultValue.RESULT_TYPE );
		this.mParamMap.put( SpeechConstant.ASR_AUDIO_PATH, null );
	}
	
	private void initMenu(){
		//��������
		{
			Map<String, String> engMap = new LinkedHashMap<String, String>();
			engMap.put( SpeechConstant.TYPE_CLOUD, "�ƶ�" );
			engMap.put( SpeechConstant.TYPE_LOCAL, "����" );
			
			JMenu engMenu = this.addRadioMenu( "��������", SpeechConstant.ENGINE_TYPE, engMap, DefaultValue.ENG_TYPE, mRadioItemListener );
			engMenu.setEnabled( false );	//Ŀǰ�ݲ�֧������ģʽ��
		}
		
		//������
		{
			Map<String, String> rateMap = new LinkedHashMap<String, String>();
			rateMap.put( "16000", "16k" );
			rateMap.put( "8000", "8k" );
			
			this.addRadioMenu( "������", SpeechConstant.SAMPLE_RATE, rateMap, DefaultValue.RATE, mRadioItemListener );
		}
		
		//���糬ʱ
		this.addSliderMenu( "���糬ʱ"
				, SpeechConstant.NET_TIMEOUT
				, 0
				, 30000
				, Integer.valueOf(DefaultValue.NET_TIMEOUT)
				, mChangeListener );
		
		//¼����ʱ
		this.addSliderMenu( "¼����ʱ"
				, SpeechConstant.KEY_SPEECH_TIMEOUT
				, 0
				, 60000
				, Integer.valueOf(DefaultValue.SPEECH_TIMEOUT)
				, mChangeListener );
		
		//����
		{
			Map<String, String> languageMap = new LinkedHashMap<String, String>();
			languageMap.put( "zh_cn", "��������" );
			languageMap.put( "en_us", "��ʽӢ��" );
			
			this.addRadioMenu( "��������", SpeechConstant.LANGUAGE, languageMap, DefaultValue.LANGUAGE, mRadioItemListener );
		}
		
		//����
		{
			Map<String, String> accentMap = new LinkedHashMap<String, String>();
			accentMap.put( "mandarin", "��ͨ��" );
			accentMap.put( "cantonese", "����" );
			accentMap.put( "lmz", "���ϻ�" );
			accentMap.put( "henanese", "���ϻ�" );
			
			this.addRadioMenu( "����", SpeechConstant.ACCENT, accentMap, DefaultValue.ACCENT, mRadioItemListener );
		}
		
		//����
		{
			Map<String, String> domainMap = new LinkedHashMap<String, String>();
			domainMap.put( "iat", "�ճ�����" );
			domainMap.put( "music", "����" );
			domainMap.put( "poi", "��ͼ" );
			domainMap.put( "vedio", "��Ƶ" );
			
			this.addRadioMenu( "����", SpeechConstant.DOMAIN, domainMap, DefaultValue.DOMAIN, mRadioItemListener );
		}
		
		//ǰ�˵㳬ʱ
		this.addSliderMenu( "ǰ�˵㳬ʱ"
				, SpeechConstant.VAD_BOS
				, 1000
				, 10000
				, Integer.valueOf(DefaultValue.VAD_BOS)
				, mChangeListener );
		
		//��˵㳬ʱ
		this.addSliderMenu( "��˵㳬ʱ"
				, SpeechConstant.VAD_EOS
				, 0
				, 10000
				, Integer.valueOf(DefaultValue.VAD_EOS)
				, mChangeListener );
		
		//���Ӷ��ѡ
		{
			Map<String, String> nbestMap = new LinkedHashMap<String, String>();
			nbestMap.put( "1", "��" );
			nbestMap.put( "0", "��" );
			
			this.addRadioMenu( "���Ӷ��ѡ", SpeechConstant.ASR_NBEST, nbestMap, DefaultValue.NBEST, mRadioItemListener );
		}
		
		//������ѡ
		{
			Map<String, String> wbestMap = new LinkedHashMap<String, String>();
			wbestMap.put( "1", "��" );
			wbestMap.put( "0", "��" );
			
			this.addRadioMenu( "������ѡ", SpeechConstant.ASR_WBEST, wbestMap, DefaultValue.WBEST, mRadioItemListener );
		}
		
		//������
		{
			Map<String, String> pttMap = new LinkedHashMap<String, String>();
			pttMap.put( "1", "��" );
			pttMap.put( "0", "��" );
			
			this.addRadioMenu( "������", SpeechConstant.ASR_PTT, pttMap, DefaultValue.PTT, mRadioItemListener );
		}
		
		//�������
		{
			Map<String, String> resultMap = new LinkedHashMap<String, String>();
			resultMap.put( "json", "json" );
			resultMap.put( "plain", "plain" );
			
			this.addRadioMenu( "�������", SpeechConstant.RESULT_TYPE, resultMap, DefaultValue.RESULT_TYPE, mRadioItemListener );
		}
		
		//������Ƶ
		{
			Map<String, String> saveMap = new LinkedHashMap<String, String>();
			saveMap.put( "1", "��" );
			saveMap.put( "0", "��" );
			
			this.addRadioMenu( "������Ƶ", SpeechConstant.ASR_AUDIO_PATH, saveMap, DefaultValue.SAVE, mRadioItemListener );
		}
		
	}//end of function initMenu
	
	private JMenu addRadioMenu( final String text, final String name, Map<String, String> cmd2Names, final String defaultVal, ActionListener actionListener ){
		JMenu menu = new JMenu( text );
		menu.setName( name );
		ButtonGroup group = new ButtonGroup();
		
		for( Entry<String, String>entry : cmd2Names.entrySet() ){
			JRadioButtonMenuItem item = new JRadioButtonMenuItem( entry.getValue(), false );
			item.setName( name );
			item.setActionCommand( entry.getKey() );
			item.addActionListener( actionListener );
			if( defaultVal.equals(entry.getKey()) ){
				item.setSelected( true );
			}
			
			group.add( item );
			menu.add( item );
		}
		
		
		return menu;
	}
	
	private void addSliderMenu( final String text, final String name, final int min, final int max, final int defaultVal, ChangeListener changeListener ){
		JMenu menu = new JMenu( text );
		
		JSlider slider = new JSlider( SwingConstants.HORIZONTAL
				, min
				, max
				, defaultVal );
		
		slider.addChangeListener( this.mChangeListener );
		slider.setName( name );
		slider.setPaintTicks( true );
		slider.setPaintLabels( true );
		final int majarTick = Math.max( 1, (max-min)/5 );
		slider.setMajorTickSpacing( majarTick );
		slider.setMinorTickSpacing( majarTick/2 );
		menu.add( slider );
		
	}
	
	//ѡ�������
	private ActionListener mRadioItemListener = new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent event) {
			DebugLog.Log( "mRadioItemListener actionPerformed etner action command="+event.getActionCommand() );
			Object obj = event.getSource();
			if( obj instanceof JMenuItem ){
				JMenuItem item = (JMenuItem)obj;
				DebugLog.Log( "mRadioItemListener actionPerformed name="+item.getName()+", value="+event.getActionCommand() );
				String value = event.getActionCommand();
				if( SpeechConstant.ASR_AUDIO_PATH.equals(item.getName()) ){
				}
				
				mParamMap.put( item.getName(), value );
			}else{
				DebugLog.Log( "mRadioItemListener actionPerformed source object is not JMenuItem" );
			}// end of if-else is object instance of JMenuItem
			
		}
		
	}; //end of  mEngItemListener
	
	//������������
	private ChangeListener mChangeListener = new ChangeListener(){

		@Override
		public void stateChanged(ChangeEvent event) {
			DebugLog.Log( "mChangeListener stateChanged enter" );
			Object obj = event.getSource();
			if( obj instanceof JSlider ){
				JSlider slider = (JSlider)obj;
				DebugLog.Log( "bar name="+slider.getName()+", value="+slider.getValue() );

				mParamMap.put( slider.getName(), String.valueOf(slider.getValue()) );
			}else{
				DebugLog.Log( "mChangeListener stateChanged source object is not JProgressBar" );
			}
		}
		
	};
	
	void setting(){
		final String engType = this.mParamMap.get(SpeechConstant.ENGINE_TYPE);
		
		for( Entry<String, String> entry : this.mParamMap.entrySet() ){
			mIat.setParameter( entry.getKey(), entry.getValue() );
		}
		
		//����ʶ��ʱ������Դ������������
		if( SpeechConstant.TYPE_LOCAL.equals(engType) ){
			//�����ϳ�����
			mIat.setParameter( ResourceUtil.ENGINE_START, SpeechConstant.ENG_ASR );
			
			//������Դ·��
			final String rate = this.mParamMap.get( SpeechConstant.SAMPLE_RATE );
			final String tag = rate.equals("16000") ? "16k" : "8k";
			String curPath = System.getProperty("user.dir");
			DebugLog.Log( "Current path="+curPath );
			String resPath = ResourceUtil.generateResourcePath( curPath+"/asr/common.jet" )
					+ ";" + ResourceUtil.generateResourcePath( curPath+"/asr/src_"+tag+".jet" );
			System.out.println( "resPath="+resPath );
			mIat.setParameter( ResourceUtil.ASR_RES_PATH, resPath );
		}// end of if is TYPE_LOCAL
		
	}// end of function setting
}
