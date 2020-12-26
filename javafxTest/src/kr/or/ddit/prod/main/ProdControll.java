package kr.or.ddit.prod.main;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import kr.or.ddit.prod.vo.LProdVo;

public class ProdControll {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private AnchorPane a_pane;

	@FXML
	private ComboBox<?> category_nm;

	@FXML
	private ComboBox<?> prod_nm;

	@FXML
	private TableView<?> tableView;

	@FXML
	private TableColumn<?, ?> idCol;

	@FXML
	private TableColumn<?, ?> nameCol;

	@FXML
	private TableColumn<?, ?> lguCol;

	@FXML
	private TableColumn<?, ?> buyerCol;

	@FXML
	private TableColumn<?, ?> costCol;

	@FXML
	private TableColumn<?, ?> priceCol;

	@FXML
	private TableColumn<?, ?> saleCol;

	@FXML
	private TableColumn<?, ?> outlineCol;

	@FXML
	private TableColumn<?, ?> detailCol;

	
	
	
	
	
	
	@FXML
	void initialize() {

		/*
        assert a_pane != null : "fx:id=\"a_pane\" was not injected: check your FXML file 'ProdTest.fxml'.";
        assert category_nm != null : "fx:id=\"category_nm\" was not injected: check your FXML file 'ProdTest.fxml'.";
        assert prod_nm != null : "fx:id=\"prod_nm\" was not injected: check your FXML file 'ProdTest.fxml'.";
        assert tableView != null : "fx:id=\"tableView\" was not injected: check your FXML file 'ProdTest.fxml'.";
        assert idCol != null : "fx:id=\"idCol\" was not injected: check your FXML file 'ProdTest.fxml'.";
        assert nameCol != null : "fx:id=\"nameCol\" was not injected: check your FXML file 'ProdTest.fxml'.";
        assert lguCol != null : "fx:id=\"lguCol\" was not injected: check your FXML file 'ProdTest.fxml'.";
        assert buyerCol != null : "fx:id=\"buyerCol\" was not injected: check your FXML file 'ProdTest.fxml'.";
        assert costCol != null : "fx:id=\"costCol\" was not injected: check your FXML file 'ProdTest.fxml'.";
        assert priceCol != null : "fx:id=\"priceCol\" was not injected: check your FXML file 'ProdTest.fxml'.";
        assert saleCol != null : "fx:id=\"saleCol\" was not injected: check your FXML file 'ProdTest.fxml'.";
        assert outlineCol != null : "fx:id=\"outlineCol\" was not injected: check your FXML file 'ProdTest.fxml'.";
        assert detailCol != null : "fx:id=\"detailCol\" was not injected: check your FXML file 'ProdTest.fxml'.";
		 */
		
		//콤보박스 데이터 초기화
		
		
	}
}
