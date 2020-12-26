package mailTest;

import java.io.File;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class javaMail {
	public static void main(String[] args) {
		sendNaver();
	}

	public static void sendNaver(){
		final String user = "so0487@naver.com"; // 발신인의 naver 계정
		final String password = "so6725so6725*"; // 패스워드
		final String[] sendTo = {"so04870487@hanmail.net"};
		
		Properties prop = new Properties(); //프로퍼티스 객체 생성
		//prop에 메일을 보내기 위한 기본 설정 값을 입력
        prop.put("mail.smtp.host", "smtp.naver.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
		
		//naver 계정 인증
		Authenticator auth = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		};
		
		//세션객체 생성
		Session session = Session.getDefaultInstance(prop, auth);
		
		//세션객체를 가지고 MimeMessage 객체 생성
		Message msg = new MimeMessage(session);
		InternetAddress[] addTo = new InternetAddress[sendTo.length]; //단체 메일을 보내기 위해 수신자의 이메일 주소를 저장할 InternetAddress형 배열
		Multipart multi = new MimeMultipart(); //mbody를 추가할 Multipart
		
		try {
			msg.setSubject("메일 제목 입니다."); //메일 제목 설정
			msg.setFrom(new InternetAddress(user)); //발신인 이메일 주소 set
			//단체 메일 보내기
			for (int i = 0; i < sendTo.length; i++) {
				addTo[i] = new InternetAddress(sendTo[i]); //sendTo배열의 주소를 InternetAddress 객체로 addTo배열에 넣어준다
			}
			msg.setRecipients(Message.RecipientType.TO, addTo);
		} catch (AddressException e) {
			System.out.println("이메일 주소를 확인해주세요.");
			e.printStackTrace();
			return;
		} catch (MessagingException e) {
			System.out.println("메시지를 제대로 입력해주세요.");
			e.printStackTrace();
			return;
		}
		
		BodyPart mbody = new MimeBodyPart(); //메시지 몸체 생성
		try {
			mbody.setText("메일의 내용입니다.");
			multi.addBodyPart(mbody); //Multipart에 내용 추가
		} catch (MessagingException e) {
			System.out.println("메일의 내용을 제대로 입력해주세요.");
			e.printStackTrace();
			return;
		}
		
		mbody = new MimeBodyPart(); //파일을 첨부할 mbody
		File file = new File("D:/D_Other/log4j.pdf"); //첨부 할 파일 경로
		FileDataSource fds = new FileDataSource(file); //file를 이용해 FileDataSource(파일을 캡슐화 해준다) 객체 생성
		
		try {
			mbody.setDataHandler(new DataHandler(fds));
			mbody.setFileName(file.getName()); //파일 이름
			multi.addBodyPart(mbody);
		} catch (MessagingException e) {
			System.out.println("첨부된 파일을 확인하세요.");
			e.printStackTrace();
			return;
		}
		
		try {
			msg.setContent(multi); //Message객체에 multi 내용을 set
			Transport.send(msg);
		} catch (MessagingException e) {
			System.out.println("오류가 발생했습니다.");
			e.printStackTrace();
			return;
		}
		
		System.out.println("naver mail이 성공적으로 발송되었습니다.");
	}
}

