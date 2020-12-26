package basic;

public class ReporterChoice {
	
	public static void main(String[] args) {
		String[][] members = {
			{"유동준", "조아라", "정수영", "천의주", "김선미"},
			{"하상욱", "제갈수진", "변소윤", "홍성우"},
			{"김판기", "강대윤", "김영진", "심규열"},
			{"이명석", "김다은", "김성주", "지승철"},
			{"손대성", "김해민", "이종문", "홍준택"},
			{"김다솜", "송예진", "장소이", "박철용"}
		};
		String[] reporters = new String[members.length];
		
		for(int i=0; i<members.length; i++){
			int index = (int)(Math.random() * members[i].length);
			reporters[i] = members[i][index]; 
		}
		
		System.out.println("....발 표 자....");
		for(int i=0; i<reporters.length; i++){
			System.out.println((i+1) + "조 : " + reporters[i]);
		}
	}

}
