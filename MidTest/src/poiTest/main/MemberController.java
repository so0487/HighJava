package poiTest.main;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import poiTest.service.IMemberService;
import poiTest.service.MemberServiceImpl;
import poiTest.vo.MemberVO;

public class MemberController {
	private Scanner scan = new Scanner(System.in);
	
	private IMemberService service;  // service媛앹껜媛� ���옣�맆 蹂��닔 �꽑�뼵
	
	// �깮�꽦�옄
	public MemberController() {
		//service = new MemberServiceImpl();
		service = MemberServiceImpl.getInstance();
	}
	
	
	// 硫붾돱瑜� 異쒕젰�븯怨� �꽑�깮�븳 �옉�뾽 踰덊샇瑜� 諛섑솚�븯�뒗 硫붿꽌�뱶
	public int displayMenu(){
		System.out.println();
		System.out.println("   -- �옉�뾽 �꽑�깮 --");
		System.out.println(" 1. �옄猷� 異붽�");
		System.out.println(" 2. �옄猷� �궘�젣");
		System.out.println(" 3. �옄猷� �닔�젙");
		System.out.println(" 4. �쟾泥� �옄猷� 異쒕젰");
		System.out.println(" 5. �옄猷� �닔�젙2");
		System.out.println(" 6. �뿊���뙆�씪 �궡蹂대궡湲�");
		System.out.println(" 7. �뿊�� �씫湲�");
		System.out.println(" 0. �옉�뾽 �걹.");
		System.out.println(" ---------------------");
		System.out.print("�썝�븯�뒗 �옉�뾽 �꽑�깮 >> ");
		int num = scan.nextInt();
		return num;
	}
	
	// �옉�뾽�쓣 �떆�옉�븯�뒗 硫붿꽌�뱶
	public void startMember(){
		while(true){
			int choice = displayMenu();
			switch(choice){
				case 1 : insertMember();	// 異붽�
					break;
				case 2 : deleteMember();	// �궘�젣
					break;
				case 3 : updateMember();	// �닔�젙
					break;
				case 4 : displayMember();	// �쟾泥� 異쒕젰
					break;
				case 5 : updateMember2();	// �닔�젙2
					break;
				case 6 : exportPoi();	//�뿊���뙆�씪 �궡蹂대궡湲�
				break;
				case 7 : excellRead();	//�뿊���씫湲�
				break;
				case 0 :	// �옉�뾽 �걹.
					System.out.println("�옉�뾽�쓣 留덉묩�땲�떎.");
					return;
				default : 
					System.out.println("踰덊샇瑜� �옒紐살엯�젰�뻽�뒿�땲�떎.");
					System.out.println("�떎�떆 �엯�젰�븯�꽭�슂.");	
			}
			
		}
	}
	
	// �쉶�썝 �젙蹂� �닔�젙2
	public void updateMember2(){
		System.out.println();
		System.out.println("�닔�젙�븷 �쉶�썝 �젙蹂대�� �엯�젰�븯�꽭�슂.");
		System.out.print("�쉶�썝ID >> ");
		String memId = scan.next();
		
		int count = service.getMemberCount(memId);
		if(count==0){  // �뾾�뒗 �쉶�썝�씠硫�...
			System.out.println(memId + "�뒗(��) �뾾�뒗 �쉶�썝ID�엯�땲�떎.");
			System.out.println("�닔�젙 �옉�뾽�쓣 醫낅즺�빀�땲�떎.");
			return;
		}
		
		int num;
		String updateFiled = null;
		String updateStr = null;
		do{
			System.out.println();
			System.out.println("�닔�젙�븷 �빆紐⑹쓣 �꽑�깮�븯�꽭�슂.");
			System.out.println("1.�쉶�썝�씠由�  2.�쟾�솕踰덊샇  3.�쉶�썝二쇱냼");
			System.out.println("---------------------------------");
			System.out.print(" �닔�젙�빆紐� �엯�젰 >> ");
			num =scan.nextInt();
			
			switch(num){
				case 1 : 
					updateFiled = "mem_name"; 
					updateStr ="�쉶�썝�씠由�"; break;
				case 2 : 
					updateFiled = "mem_tel"; 
					updateStr ="�쟾�솕踰덊샇"; break;
				case 3 : 
					updateFiled = "mem_addr"; 
					updateStr ="�쉶�썝二쇱냼"; break;
				default : 
					System.out.println("�닔�젙 �빆紐⑹쓣 �옒紐� �꽑�깮�뻽�뒿�땲�떎.");
					System.out.println("�떎�떆 �꽑�깮�븯�꽭�슂.");
			}
			
		}while(num<1 || num>3);
		
		System.out.println();
		scan.nextLine(); // �엯�젰 踰꾪띁 鍮꾩슦湲�
		System.out.print("�깉濡쒖슫 " + updateStr + " >> ");
		String updateData = scan.nextLine();
		
		// �닔�젙�븷 �젙蹂대�� Map�뿉 異붽��븳�떎.
		// key媛� �젙蹂� =>  �쉶�썝ID(memid), �닔�젙�븷 而щ읆(field), �닔�젙�븷�뜲�씠�꽣(data)
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("memid", memId);
		paramMap.put("field", updateFiled);
		paramMap.put("data", updateData);
		
		
		int cnt = service.updateMember2(paramMap);
		
		if(cnt>0){
			System.out.println("�닔�젙 �옉�뾽 �꽦怨�!!!");
		}else{
			System.out.println("�닔�젙 �옉�뾽 �떎�뙣~~~");
		}
		
		
	}
	
	// �쟾泥� �쉶�썝 異쒕젰
	public void displayMember(){
		System.out.println();
		
		List<MemberVO> memList = service.getAllMemberList();
		
		System.out.println("------------------------------------");
		System.out.println(" ID   �씠由�    �쟾�솕踰덊샇     二쇱냼");
		System.out.println("------------------------------------");
		if(memList==null || memList.size()==0){
			System.out.println("�벑濡앸맂 �쉶�썝�젙蹂닿� �븯�굹�룄 �뾾�뒿�땲�떎.");
		}else{
			for(MemberVO memVo : memList){
				String memId = memVo.getMem_id();
				String memName = memVo.getMem_name();
				String memTel = memVo.getMem_tel();
				String memAddr = memVo.getMem_addr();
				
				System.out.println(memId + "   " + memName + "   " 
							+ memTel + "   " + memAddr );
			}
		}
		System.out.println("------------------------------------");
			
		
	}
	
	
	// �쉶�썝 �닔�젙
	public void updateMember(){
		System.out.println();
		System.out.println("�닔�젙�븷 �쉶�썝 �젙蹂대�� �엯�젰�븯�꽭�슂.");
		System.out.print("�쉶�썝ID >> ");
		String memId = scan.next();
		
		int count = service.getMemberCount(memId);
		if(count==0){  // �뾾�뒗 �쉶�썝�씠硫�...
			System.out.println(memId + "�뒗(��) �뾾�뒗 �쉶�썝ID�엯�땲�떎.");
			System.out.println("�닔�젙 �옉�뾽�쓣 醫낅즺�빀�땲�떎.");
			return;
		}
		
		System.out.print("�깉濡쒖슫 �쉶�썝�씠由� >> ");
		String memName = scan.next();
		
		System.out.print("�깉濡쒖슫 �쟾�솕踰덊샇 >> ");
		String memTel = scan.next();
		
		scan.nextLine();  // �엯�젰踰꾪띁 鍮꾩슦湲�
		System.out.print("�깉濡쒖슫 �쉶�썝二쇱냼 >> ");
		String memAddr = scan.nextLine();
		
		// �엯�젰諛쏆� �닔�젙�븷 �뜲�씠�꽣瑜� MemberVO媛앹껜�뿉 ���옣�븳�떎.
		MemberVO memVo = new MemberVO();
		memVo.setMem_id(memId);
		memVo.setMem_name(memName);
		memVo.setMem_tel(memTel);
		memVo.setMem_addr(memAddr);
		
		int cnt = service.updateMember(memVo);
		
		if(cnt>0){
			System.out.println(memId + "�쉶�썝 �젙蹂� �닔�젙 �셿猷�!!");
		}else{
			System.out.println(memId + "�쉶�썝 �젙蹂� �닔�젙 �떎�뙣~~~");
		}
			
	}
	
	// �쉶�썝 異붽�
	public void insertMember(){
		System.out.println();
		System.out.println("異붽��븷 �쉶�썝 �젙蹂대�� �엯�젰�븯�꽭�슂.");
		int count = 0;
		String memId = null;   // �쉶�썝ID媛� ���옣�맆 蹂��닔
		do{
			System.out.print("�쉶�썝ID >> ");
			memId = scan.next();
			
			count = service.getMemberCount(memId);
			if(count>0){
				System.out.println(memId + "��(�뒗) �씠誘� �벑濡앸맂 �쉶�썝ID�엯�땲�떎.");
				System.out.println("�떎瑜� �쉶�썝ID瑜� �엯�젰�븯�꽭�슂.");
				System.out.println();
			}
				
		}while(count>0);
		
		System.out.print("�쉶�썝�씠由� >> ");
		String memName = scan.next();
		
		System.out.print("�쟾�솕踰덊샇 >> ");
		String memTel = scan.next();
		
		scan.nextLine();  // �엯�젰 踰꾪띁 鍮꾩슦湲�
		System.out.print("�쉶�썝二쇱냼 >> ");
		String memAddr = scan.nextLine();
		
		// �엯�젰諛쏆� �뜲�씠�꽣瑜� MemberVO媛앹껜�뿉 �떞�뒗�떎.
		MemberVO memVo = new MemberVO();
		
		memVo.setMem_id(memId);
		memVo.setMem_name(memName);
		memVo.setMem_tel(memTel);
		memVo.setMem_addr(memAddr);
		
		// �꽌鍮꾩뒪�쓽 insert�븯�뒗 硫붿꽌�뱶瑜� �샇異쒗븳�떎.
		// �씠�븣 insert�븷 �뜲�씠�꽣媛� ���옣�맂 MemberVO媛앹껜瑜� 蹂대궡以��떎.
		int cnt = service.insertMember(memVo);
		
		if(cnt>0){
			System.out.println("�쉶�썝 異붽� �꽦怨�!!");
		}else{
			System.out.println("�쉶�썝 異붽� �떎�뙣!!");
		}
		
	}
	
	// �쉶�썝 �궘�젣
	public void deleteMember(){
		System.out.println();
		System.out.println("�궘�젣�븷 �쉶�썝 �젙蹂대�� �엯�젰�븯�꽭�슂.");
		System.out.print("�쉶�썝ID >> ");
		String memId = scan.next();
		
		int cnt = service.deleteMember(memId);
		
		if(cnt>0){
			System.out.println("�쉶�썝ID媛� " + memId + "�씤 �쉶�썝 �젙蹂� �궘�젣 �꽦怨�!!");
		}else{
			System.out.println(memId + "�쉶�썝�� �뾾�뒗 �쉶�썝�씠嫄곕굹 �궘�젣�뿉 �떎�뙣�뻽�뒿�땲�떎.");
		}
	}
	
	
	
	
	
	
	
	//�뿊���뙆�씪 �궡蹂대궡湲�
	private void exportPoi() {
		System.out.println("�뿊�� �궡蹂대궡湲�");

		List<MemberVO> list = new ArrayList<>();


		//泥ロ뻾(header) : �빐�뜑�뱾�쓽 媛믪쓣 諛곗뿴濡� �떞湲�
		String [] meminfo = new String[]{"mem_id", "mem_name", "mem_tel", "mem_addr"};

		//member�쓽 �쟾泥� �젙蹂대�� list�뿉 �떞湲�
		list = service.getAllMemberList();


		//�썙�겕遺� �깮�꽦
		XSSFWorkbook workbook = new XSSFWorkbook();

		//�떆�듃 �깮�꽦
		XSSFSheet sheet = workbook.createSheet();

		//�뻾 �깮�꽦
		XSSFRow row = sheet.createRow(0);

		//�� �깮�꽦
		XSSFCell cell;




		//�빐�뜑 �븘�옒 meminfo�젙蹂닿갗�닔留뚰겮 row()�깮�꽦
		for(int i=0;i<meminfo.length;i++) {
			cell = row.createCell(i);
			cell.setCellValue(meminfo[i]);
		}



		//�쉶�썝�닔 留뚰겮 諛섎났�븯�뿬 row, ���쓣 留뚮뱾怨� 媛믪쓣 �꽔湲�
		for(int i = 0; i<list.size();i++) {

			//row留뚮뱾湲�
			row = sheet.createRow(i+1);

			//row�뿉 �� �깮�꽦�븯怨� 媛믪쓣 �꽔湲�
			for(int j=0;j<meminfo.length;j++) {
				switch (j) {
				case 0:	//memId
					cell = row.createCell(0);
					cell.setCellValue(list.get(i).getMem_id());
					break;


				case 1:	//memName
					cell = row.createCell(1);
					cell.setCellValue(list.get(i).getMem_name());
					break;


				case 2:	//memTel
					cell = row.createCell(2);
					cell.setCellValue(list.get(i).getMem_tel());
					break;


				case 3:	//memAddr
					cell = row.createCell(3);
					cell.setCellValue(list.get(i).getMem_addr());
					break;


				default:
					break;
				}
			}
		}


		//File�깮�꽦
		File excelTest = new File("c:/soo/D_Other/testWrite.xlsx");
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;



		try {
			fos = new FileOutputStream(excelTest);
			bos = new BufferedOutputStream(fos);


			workbook.write(bos);
			System.out.println(excelTest+" �뙆�씪 �깮�꽦 �꽦怨�");
		} catch (IOException e) {
			System.out.println("�뿊�� �뙆�씪 �깮�꽦 �떎�뙣");
			e.printStackTrace();
		}finally {
			if(bos!=null) {
				try {
					bos.close();
				} catch (IOException e2) {
					// TODO: handle exception
				}
			}
		}



	}





	//�뿊�� �뙆�씪 �씫湲�
	private void excellRead() {
		System.out.println("�뿊�� �씫湲�");
		MemberVO mvo;
		List<MemberVO> memberVo = new ArrayList<>();



		//�뙆�씪�쓽 寃쎈줈�씠由꾩쓣 String 蹂��닔�뿉 �꽑�뼵
		String filePath = "c:/soo/D_Other/testWrite.xlsx";


		try {
			Workbook workBook = WorkbookFactory.create(new File(filePath));
			Sheet sheet = null;
			Row row = null;
			Cell cell = null;



			//�썙�겕遺곸뿉 �엳�뒗 Sheet媛��닔
			int sheetNum = workBook.getNumberOfSheets();


			//諛섎났臾몄쓣 �씠�슜�븯�뿬 sheet媛��닔留뚰겮 sheet�깮�꽦
			for(int k = 0; k<sheetNum; k++) {
				sheet = workBook.getSheetAt(k);


				//�떆�듃�쓽 row媛��닔 援ы븯湲�
				int rows = sheet.getPhysicalNumberOfRows(); 



				//sheet�쓽 row媛��닔留뚰겮 row�깮�꽦
				for(int r = 1; r<rows;r++) {
					mvo = new MemberVO();
					row = sheet.getRow(r);


					//�뻾�뿉 ���븳 cell�쓽 珥� 媛��닔 援ы븯湲�
					int cells = row.getPhysicalNumberOfCells();

					//row�쓽 cell留뚰겮 cell�깮�꽦
					for(int c =0; c<cells; c++) {
						cell = row.getCell(c);


						//cell�씠 null�씠硫� 怨꾩냽�빐�꽌 �옉�뾽 吏꾪뻾
						if(cell==null) {
							continue;
						}


						//���엯�쓣 �꽔�뼱以� String 蹂��닔 �꽑�뼵
						String value = "";


						//�뻾�뿉 ���븳 ���쓣 �븯�굹�뵫 異붿텧�븯�뿬 �� ���엯�뿉 �뵲�씪 泥섎━�븯�뿬 value�뿉 ���옣
						switch (cell.getCellType()) {
						case Cell.CELL_TYPE_FORMULA:
							value = cell.getCellFormula();
							break;
						case Cell.CELL_TYPE_NUMERIC:
							value = cell.getNumericCellValue()+"";
							break;
						case Cell.CELL_TYPE_STRING:
							value = cell.getStringCellValue();
							break;
						case Cell.CELL_TYPE_BLANK:
							value = cell.getBooleanCellValue()+"";
							break;
						case Cell.CELL_TYPE_ERROR:
							value = cell.getErrorCellValue()+"";
							break;

						default:
							value = new String();
							break;
						}




						//���엯�쑀�삎�뿉 留욊쾶 MemberVO�뿉 �꽔�뼱以�
						switch (c) {
						case 0:
							mvo.setMem_id(value);
							break;
						case 1:
							mvo.setMem_name(value);
							break;
						case 2:
							mvo.setMem_tel(value);
							break;
						case 3:
							mvo.setMem_addr(value);
							break;

						default:
							break;
						}
					}

					memberVo.add(mvo);

				}

			}

		} catch (Exception e) {
			System.out.println("�뙆�씪 �씪湲� �삤瑜�");
			e.printStackTrace();
		}

		//異쒕젰
		System.out.println("mem_id\tmem_name\t mem_tel\t mem_addr");

		for(MemberVO mv : memberVo) {
			System.out.println(mv.getMem_id()+"\t"+mv.getMem_name()+"\t\t"+mv.getMem_tel()+"\t"+mv.getMem_addr());
		}

	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		new MemberController().startMember();
	}

}
