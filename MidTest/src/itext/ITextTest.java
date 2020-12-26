package itext;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class ITextTest {
	public static void main(String[] args) {

		Document doc = new Document();
		
		try {
			PdfWriter.getInstance(doc, new FileOutputStream("test.pdf"));
			System.out.println("�뙆�씪�씠 �깮�꽦�릺�뿀�뒿�땲�떎.");
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("pdf�뙆�씪 �깮�꽦 �떎�뙣");
			e.printStackTrace();
		}
		
		
		doc.open();//pdf �뿴湲�
		
		//pdf�뙆�씪 �궡�슜 �옉�꽦
		try {
			//�삁�빟�씪�옄瑜� �쁽�옱�떆媛꾩쑝濡� �꽕�젙�븯湲� �쐞�븳 Date媛앹껜
			Date date = new Date();
			
			
			//PDF�궡�슜 �깮�꽦
			
			//�궗吏꾪뙆�씪 �깮�꽦
			String imageFile = "c:/soo/d_other/도라이몽.jpg";	//�씠誘몄� �뙆�씪寃쎈줈 吏��젙
			
			
			//image瑜� 泥섎━�븯�뒗 getInstance
			Image data = Image.getInstance(imageFile);
			
			//�씠誘몄� �겕湲� 吏��젙
			data.scaleAbsolute(170, 100);
			
			
			//�씠誘몄� �젙�젹
			data.setAlignment(Paragraph.ALIGN_CENTER);
			
			//�씠吏�誘� 異붽�
			doc.add(data);
			
			//Text �깮�꽦(�궫�엯諛⑸쾿 1)
			Paragraph title  = new Paragraph("�삁留ㅼ쁺�솕 : ");
			doc.add(title);
			
			
			
			//�삁留ㅼ쁺�솕 �궗吏� �꽔湲�
			String posterPath = "c:/soo/d_other/도라이몽.jpg";
			
			Image poster = Image.getInstance(posterPath);
			poster.scaleAbsolute(200, 300);
			doc.add(poster);
			
			
			
			//�삁�빟�씪�떆(�쁽�옱�떆媛� �엯�젰)
			//�엯�젰諛⑸쾿 2
			doc.add(new Paragraph("�삁�빟�씪�떆 : "+date));	
			

			
		}catch(DocumentException e){
			System.out.println("�궡�슜 �옉�꽦 �떎�뙣");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		doc.close();
		

	}
	
	
}
